package util;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class WordUtilsTest {
    @Test
    public void isIndirectAddressing_ShouldBeTrue() {
        char word = 0b0000000000100000;

        boolean result = WordUtils.isIndirectAddressing(word);

        assertEquals("Indirect addressing bit should be set", true, result);
    }

    @Test
    public void isIndirectAddressing_ShouldBeFalse() {
        char word = 0b0000000000000000;

        boolean result = WordUtils.isIndirectAddressing(word);

        assertEquals("Indirect addressing bit should not be set", false, result);
    }
}
