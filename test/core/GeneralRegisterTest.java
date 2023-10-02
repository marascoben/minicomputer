package core;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class GeneralRegisterTest {

    @Test
    public void getRegister_ShouldReturnR0() {
        char word = 0b0000000000000000;
        GeneralRegister result = GeneralRegister.fromWord(word);
        assertEquals("Should return R0", GeneralRegister.GPR0, result);
    }

    @Test
    public void getRegister_ShouldReturnR1() {
        char word = 0b0000000100000000;
        GeneralRegister result = GeneralRegister.fromWord(word);
        assertEquals("Should return R1", GeneralRegister.GPR1, result);
    }

    @Test
    public void getRegister_ShouldReturnR2() {
        char word = 0b0000001000000000;
        GeneralRegister result = GeneralRegister.fromWord(word);
        assertEquals("Should return R2", GeneralRegister.GPR2, result);
    }

    @Test
    public void getRegister_ShouldReturnR3() {
        char word = 0b0000001100000000;
        GeneralRegister result = GeneralRegister.fromWord(word);
        assertEquals("Should return R3", GeneralRegister.GPR3, result);
    }
}
