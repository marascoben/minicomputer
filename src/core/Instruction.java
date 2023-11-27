package core;

public class Instruction {

    public char word;

    public Instruction(char word) {
        this.word = word;
    }

    /**
     * Returns the opcode stored in the instruction
     * 
     * @return The stored opcode
     */
    public Opcode getOpcode() {
        return Opcode.fromWord(word);
    }

    /**
     * Returns the address value stored in the instruction
     * 
     * @return The stored value
     */
    public char getAddress() {
        return (char) (word & 0b0000000000011111);
    }

    /**
     * Returns the immediate value stored in the instruction,
     * this is functionally the same as getAddress()
     * 
     * @return The stored value
     */
    public char getImmed() {
        return getAddress();
    }

    /**
     * Gets the general register indicated in the R field
     * 
     * @return The register
     */
    public GeneralRegister getGPR() {
        return GeneralRegister.fromWord(word);
    }

    /**
     * Gets the index register indicated in the IX field
     * 
     * @return The register
     */
    public IndexRegister getIXR() {
        return IndexRegister.fromWord(word);
    }

    /**
     * Returns if the indirect addressing bit is set
     * 
     * @return True if set, false otherwise
     */
    public boolean isIndirectAddressing() {
        return ((word << 10) >> 15) == 1;
    }

    /**
     * Gets the general register indicated in the RX field
     * 
     * @return The register
     */
    public GeneralRegister getRX() {
        return GeneralRegister.fromId((byte) ((word >> 8) & 0b11));
    }

    /**
     * Gets the general register indicated in the RY field
     * 
     * @return The register
     */
    public GeneralRegister getRY() {
        return GeneralRegister.fromId((byte) ((word >> 6) & 0b11));
    }
}
