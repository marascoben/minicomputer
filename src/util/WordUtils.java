package util;

import core.IndexRegister;

public class WordUtils {

    /**
     * Given a 16-bit word, return the data stored in bits 6-7, where bit 0 is the
     * most significant bit.
     * 
     * @param word The word to read from.
     * @return The data stored in bits 6-7.
     */
    public static byte getGPR(char word) {
        // Left shift by 6 bits to get rid of the opcode, then right shift by 14 bits to
        // get rid of the last 14 bits
        return (byte) ((word << 6) >> 14);
    }

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
     * Given a 16-bit word, return the data stored in bits 11-15, where bit 0 is the
     * most significant bit.
     * 
     * @param word The word to read from.
     * @return The data stored in bits 11-15.
     */
    public static byte getAddress(char word) {
        // Left shift by 11 bits to remove the opcode, GPR, IXR, and indirect addressing
        // bit, then right shift by 11 bits to get rid of the last 11 bits.
        return (byte) ((word << 11) >> 11);
    }

}