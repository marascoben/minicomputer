package components.cpu;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import components.Memory;
import core.GeneralRegister;
import core.IndexRegister;
import core.Instruction;
import core.Opcode;
import core.func.ArithmeticFunction;
import util.WordUtils;
import ui.listeners.HardwareListener;

public class Processor {

    private static final Logger LOGGER = Logger.getLogger(Processor.class.getName());

    // General Purpose Registers
    public char R0 = 0, R1 = 0, R2 = 0, R3 = 0;

    // Index Registers
    public char X1 = 0, X2 = 0, X3 = 0;

    // Memory Address, Memory Buffer Registers
    public char MAR = 0, MBR = 0;

    // Program Counter
    public char PC = 0;

    // Instruction Register
    private char IR = 0;

    // Memory Fault Register
    private byte MFR = 0;

    // Condition Code
    private byte CC = 0;

    // Memory Object
    private Memory memory;

    // Whether or not the minicomputer is halted
    private boolean halted = false;

    // Whether or not to increment the PC after executing an instruction
    private boolean incrementPC = true;

    // List of listeners
    private List<HardwareListener> listeners = new ArrayList<>();

    public Processor(Memory memory) {
        this.memory = memory;
    }

    /**
     * Attaches a HardwareListener to the processor.
     * 
     * @param listener The listener to add.
     */
    public void addListener(HardwareListener listener) {
        listeners.add(listener);
    }

    public void run() {
        halted = false;

        while (!halted) {
            step();
        }
    }

    public void step() {
        incrementPC = true;

        IR = memory.read(PC);

        execute(new Instruction(IR));

        if (incrementPC)
            PC++;

        notifyListeners();
    }

    public void execute(Instruction i) {
        switch (i.getOpcode()) {
            case AIR:
                // Add Immediate to Register;
                setValue(i.getGPR(), (char value) -> {
                    if (i.getImmed() == 0)
                        return value;

                    if (value == 0)
                        return i.getImmed();

                    return ALU.add(value, i.getImmed());
                });
                break;
            case AMR:
                // Add Memory to Register
                setValue(i.getGPR(), (char value) -> {
                    if(value == 0){
                        return memory.read(effectiveAddress(i.word));
                    } else {
                        return ALU.add(value, memory.read(effectiveAddress(i.word)));
                    }
                });
                break;
            case AND:
                break;
            case CHK:
                break;
            case DVD:
                break;
            case HLT:
                halt();
                break;
            case IN:
                break;
            case JCC:
                break;
            case JGE:
                if (getValue(i.getGPR()) >= 0) {
                    this.PC = effectiveAddress(i.word);
                    skipIncrement();
                }
                break;
            case JMA:
                // Jump to Address
                this.PC = effectiveAddress(i.word);
                skipIncrement();
                break;
            case JNE:
                // Jump if Not Equal
                if (getValue(GeneralRegister.fromWord(i.word)) != 0) {
                    this.PC = effectiveAddress(i.word);
                    skipIncrement();
                }
                break;
            case JSR:
                // Jump to Subroutine
                this.R3 = this.PC++;
                this.PC = effectiveAddress(i.word);
                skipIncrement();
                break;
            case JZ:
                // Jump if Zero
                if (getValue(GeneralRegister.fromWord(i.word)) == 0) {
                    this.PC = effectiveAddress(i.word);
                    skipIncrement();
                }
                break;
            case LDA:
                // Load Register with Address
                setValue(i.getGPR(), effectiveAddress(i.word));
                break;
            case LDR:
                // Load Register from Memory
                setValue(i.getGPR(), memory.read(effectiveAddress(i.word)));
                break;
            case LDX:
                loadIndexFromMemory(i.getIXR(), effectiveAddress(i.word));
                break;
            case MLT:
                break;
            case NOT:
                break;
            case ORR:
                break;
            case OUT:
                break;
            case RFS:
                break;
            case RRC:
                break;
            case SIR:
                // Subtract Immediate from Register
                setValue(i.getGPR(), (char value) -> {
                    if (i.getImmed() == 0)
                        return value;

                    if (value == 0)
                        return i.getImmed();

                    return ALU.subtract(value, i.getImmed());
                });
                break;
            case SMR:
                break;
            case SOB:
                break;
            case SRC:
                break;
            case STR:
                storeToMemory(i.getGPR(), effectiveAddress(i.word));
                break;
            case STX:
                storeIndexToMemory(i.getIXR(), effectiveAddress(i.word));
                break;
            case TRP:
                halt();
                break;
            case TRR:
                break;
            default:
                break;

        }
    }

    /**
     * Given a 16-bit word, return the effective address from the word.
     * 
     * @param word The word to read from.
     * @return The effective address from the word.
     */
    public char effectiveAddress(char word) {
        IndexRegister ix = IndexRegister.fromWord(word);

        if (Opcode.isIndirectAddressing(word)) {
            // Indirect addressing but NO indexing
            LOGGER.fine("Computing effective address with indirect addressing");
            switch (ix) {
                case IX1:
                    return memory.read((char) (X1 + WordUtils.getAddress(word)));
                case IX2:
                    return memory.read((char) (X2 + WordUtils.getAddress(word)));
                case IX3:
                    return memory.read((char) (X3 + WordUtils.getAddress(word)));
                default:
                    return memory.read((char) WordUtils.getAddress(word));
            }
        } else {
            // NO indirect addressing
            LOGGER.fine("Computing effective address without indirect addressing");
            switch (ix) {
                case IX1:
                    return (char) (X1 + WordUtils.getAddress(word));
                case IX2:
                    return (char) (X2 + WordUtils.getAddress(word));
                case IX3:
                    return (char) (X3 + WordUtils.getAddress(word));
                default:
                    return (char) WordUtils.getAddress(word);
            }
        }
    }

    public char getIR() {
        return IR;
    }

    public char getMFR() {
        return (char) MFR;
    }

    public char getCC() {
        return (char) CC;
    }

    public boolean isHalted() {
        return halted;
    }

    protected void halt() {
        LOGGER.info("Halting minicomputer");
        halted = true;
    }

    /**
     * Load the contents of the address into the specified general purpose register
     * 
     * @param r       The register to load the value into
     * @param address The address to load the value from
     */
    protected void loadFromMemory(GeneralRegister r, char address) {
        LOGGER.info("Loading to register " + r + " from address " + String.format("0x%08X", (short) address));

        switch (r) {
            case GPR0:
                R0 = memory.read(address);
                break;
            case GPR1:
                R1 = memory.read(address);
                break;
            case GPR2:
                R2 = memory.read(address);
                break;
            case GPR3:
                R3 = memory.read(address);
                break;
        }
    }

    /**
     * Store the contents of the specified general purpose register into the
     * address.
     * 
     * @param r       The register to store the value from.
     * @param address The address to store the value to.
     */
    protected void storeToMemory(GeneralRegister r, char address) {
        LOGGER.info("Storing value from register " + r + " to address " + String.format("0x%08X", (short) address));

        switch (r) {
            case GPR0:
                memory.write(address, R0);
                break;
            case GPR1:
                memory.write(address, R1);
                break;
            case GPR2:
                memory.write(address, R2);
                break;
            case GPR3:
                memory.write(address, R3);
                break;
        }
    }

    /**
     * Load the contents of the address into the specified index register.
     * 
     * @param ix      The index register to load the value into.
     * @param address The address to load the value from.
     */
    protected void loadIndexFromMemory(IndexRegister ix, char address) {
        LOGGER.info("Loading to index register " + ix + " from address " + String.format("0x%08X", (short) address));

        switch (ix) {
            case IX1:
                X1 = memory.read(address);
                break;
            case IX2:
                X2 = memory.read(address);
                break;
            case IX3:
                X3 = memory.read(address);
                break;
            default:
                break;
        }
    }

    /**
     * Store the contents of the specified index register into the address.
     * 
     * @param ix      The index register to store the value from.
     * @param address The address to store the value to.
     */
    protected void storeIndexToMemory(IndexRegister ix, char address) {
        LOGGER.info(
                "Storing value from index register " + ix + " to address " + String.format("0x%08X", (short) address));

        switch (ix) {
            case IX1:
                memory.write(address, X1);
                break;
            case IX2:
                memory.write(address, X2);
                break;
            case IX3:
                memory.write(address, X3);
                break;
            default:
                break;
        }
    }

    /**
     * Returns the current value of the specified general purpose register.
     * 
     * @param r The register to read from.
     * @return The value of the register.
     */
    private char getValue(GeneralRegister r) {
        switch (r) {
            case GPR0:
                return R0;
            case GPR1:
                return R1;
            case GPR2:
                return R2;
            case GPR3:
                return R3;
            default:
                LOGGER.severe("Invalid register " + r);
                return 0;
        }
    }

    private void setValue(GeneralRegister r, ArithmeticFunction f) {
        char value = f.evaluate(getValue(r));
        setValue(r, value);
    }

    /**
     * Sets the value of the specified general purpose register.
     * 
     * @param r     The register to set the value of.
     * @param value The value to set the register to.
     */
    private void setValue(GeneralRegister r, char value) {
        switch (r) {
            case GPR0:
                R0 = value;
                break;
            case GPR1:
                R1 = value;
                break;
            case GPR2:
                R2 = value;
                break;
            case GPR3:
                R3 = value;
                break;
            default:
                LOGGER.severe("Invalid register " + r);
                break;
        }
    }

    /**
     * Causes the processor to skip incrementing the program counter after the
     * execution of the current instruction.
     */
    private void skipIncrement() {
        incrementPC = false;
    }

    private void notifyListeners() {
        for (HardwareListener listener : listeners) {
            listener.onUpdate();
        }
    }

    private void logInstruction(char word) {
        GeneralRegister r = GeneralRegister.fromWord(word);
        IndexRegister ix = IndexRegister.fromWord(word);
        char address = effectiveAddress(word);

        LOGGER.info("Running instruction: " + Opcode.fromWord(word) + " " + r + " " + ix + " "
                + String.format("0x%08X", (short) address));
    }
}
