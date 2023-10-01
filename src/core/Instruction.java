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
    public static Instruction fromWord(char word) {
        byte opcode = (byte) (word >> 10);
        for (Instruction instruction : Instruction.values()) {
            if (instruction.opcode == opcode) {
                return instruction;
            }
        }

        // If attempting to read an invalid instruction, halt the program
        return HLT;
    }

    /**
     * Given a 16-bit word, return whether or not the indirect addressing bit is set
     * in bit 10, where bit 0 is the most significant bit.
     * 
     * @param word The word to read from.
     * @return Whether or not the indirect addressing bit is set.
     */
    public static boolean isIndirectAddressing(char word) {
        // Left shift by 10 bits to get rid of the opcode, index register, and GPR, then
        // right shift by 15 bits to get rid of the last 15 bits
        return ((word << 10) >> 15) == 1;
    }

    /**
     * Given a 16-bit word, return the data stored in bits 11-15, where bit 0 is the
     * most significant bit.
     * 
     * @param word The word to read from.
     * @return The data stored in bits 11-15.
     */
    public static char getAddress(char word) {
        // Left shift by 11 bits to remove the opcode, GPR, IXR, and indirect addressing
        // bit, then right shift by 11 bits to get rid of the last 11 bits.
        return (char) ((word << 11) >> 11);
    }
}
