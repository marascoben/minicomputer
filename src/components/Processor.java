package components;

public class Processor {

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

    public Processor(Memory memory) {
        this.memory = memory;
    }

    /**
     * Resets the processor to its initial state.
     */
    public void reset() {
        R0 = R1 = R2 = R3 = 0;
        X1 = X2 = X3 = 0;
        MAR = MBR = 0;
        PC = 0;
        IR = 0;
        MFR = 0;
        CC = 0;
    }

    public char getIR() {
        return IR;
    }

    public byte getMFR() {
        return MFR;
    }

    public byte getCC() {
        return CC;
    }
}
