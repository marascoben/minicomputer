package util;

public class WordUtils {

    /**
     * Given a 16-bit word, return the data stored in bits 8-9, where bit 0 is the
     * most significant bit.
     * 
     * @param word The word to read from.
     * @return The data stored in bits 8-9.
     */
    public static byte getIX(char word) {
        // Left shift by 8 bits to get rid of the opcode and GPR, then right shift by 14
        // bits to
        // get rid of the last 14 bits
        return (byte) ((word << 8) >> 14);
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
     * Given a 16-bit word, return the data stored in bits 0-4, where bit 0 is the least significant bit.
     * 
     * @param word The word to read from.
     * @return The data stored in bits 11-15.
     */
    public static byte getAddress(char word) {
        
        // Right shift by 11 bits to get rid of the opcode, index register, and GPR
        return (byte) (word & 0b0000000000011111);
    }

}
