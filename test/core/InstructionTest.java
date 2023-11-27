package core;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class InstructionTest {

    @Test
    public void InvalidInstruction_ShouldHalt() {
        char instruction = 0b1111110000000000;

        Opcode result = Opcode.fromWord(instruction);

        assertEquals("Invalid opcode produces halt", Opcode.HLT, result);
    }

}
