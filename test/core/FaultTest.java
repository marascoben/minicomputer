package core;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class FaultTest {

    @Test
    public void isSet_IllegalMemoryReserved() {
        byte cc = 0b0001;

        assertTrue("ILLEGAL_MEMORY_RESERVED fault should be set", Fault.isSet(Fault.ILLEGAL_MEMORY_RESERVED, cc));
    }

    @Test
    public void isSet_IllegalTrap() {
        byte cc = 0b0010;

        assertTrue("ILLEGAL_TRAP fault should be set", Fault.isSet(Fault.ILLEGAL_TRAP, cc));
    }

    @Test
    public void isSet_IllegalOpcode() {
        byte cc = 0b0100;

        assertTrue("ILLEGAL_OPCODE fault should be set", Fault.isSet(Fault.ILLEGAL_OPCODE, cc));
    }

    @Test
    public void isSet_IllegalMemoryAddress() {
        byte cc = 0b1000;

        assertTrue("ILLEGAL_MEMORY_ADDRESS fault should be set", Fault.isSet(Fault.ILLEGAL_MEMORY_ADDRESS, cc));
    }

}
