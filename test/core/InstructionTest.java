package core;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class InstructionTest {

    // LDR 3,0,31
    char test1 = 0b0000011100011111;

    // GPR 0
    char GPR0 = 0b0000000000000000;

    // GPR 1
    char GPR1 = 0b0000000100000000;

    // GPR 2
    char GPR2 = 0b0000001000000000;

    // GPR 3
    char GPR3 = 0b0000001100000000;

    // Address 1
    char address1 = 0b0000000000000001;

    @Test
    public void getGPR_ShouldReturnGPR0() {
        Instruction i = new Instruction(GPR0);

        assertEquals("GPR0 should be returned", GeneralRegister.GPR0, i.getGPR());
    }

    @Test
    public void getGPR_ShouldReturnGPR1() {
        Instruction i = new Instruction(GPR1);

        assertEquals("GPR1 should be returned", GeneralRegister.GPR1, i.getGPR());
    }

    @Test
    public void getGPR_ShouldReturnGPR2() {
        Instruction i = new Instruction(GPR2);

        assertEquals("GPR2 should be returned", GeneralRegister.GPR2, i.getGPR());
    }

    @Test
    public void getGPR_ShouldReturnGPR3() {
        Instruction i = new Instruction(GPR3);

        assertEquals("GPR3 should be returned", GeneralRegister.GPR3, i.getGPR());
    }

    @Test
    public void getAddress_ShouldReturnAddress1() {
        Instruction i = new Instruction(address1);

        assertEquals("Address 1 should be returned", address1, i.getAddress());
    }

    @Test
    public void getOpcode_ShouldReturnLDR() {
        Instruction i = new Instruction(test1);

        assertEquals("LDR instruction should return correct opcode", Opcode.LDR, i.getOpcode());
    }
}
