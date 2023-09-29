package components;

public class Processor {

    // General Purpose Registers
    private char R0 = 0, R1 = 0, R2 = 0, R3 = 0;

    // Index Registers
    private char X1 = 0, X2 = 0, X3 = 0;

    // Memory Address Register, Memory Buffer Register
    private char MAR = 0, MBR = 0;

    // Instruction Register
    private char IR = 0;

    // Program Counter
    private char PC = 0;

    // Condition Code
    private byte CC = 0;

    public Processor() {

    }
}
