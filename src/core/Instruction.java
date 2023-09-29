package core;

public enum Instruction {
    HLT((byte) 0xb00000), // HALT
    LDR((byte) 0b000001), // Load Register
    STR((byte) 0b000010), // Store Register
    LDA((byte) 0b000011), // Load Register with Address
    LDX((byte) 0b100001), // Load Index Register from Memory
    STX((byte) 0b100010), // Store Index Register to Memory
    TRP((byte) 0b011000); // TRAP

    // The opcode of the instruction
    public final byte opcode;

    private Instruction(byte opcode) {
        this.opcode = opcode;
    }
    
    /**
     * Given a word, read the first 6 bits and return the corresponding instruction.
     * 
     * @param word The word to read from.
     * @return The instruction stored in the first 6 bits of the word.
     */
    public Instruction fromWord(char word) {
        byte opcode = (byte) (word >> 10);
        for (Instruction instruction : Instruction.values()) {
            if (instruction.opcode == opcode) {
                return instruction;
            }
        }

        // If attempting to read an invalid instruction, halt the program
        return HLT;
    }
}
