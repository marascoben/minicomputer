package core;

public enum GeneralRegister {
    GPR0((byte) 0b00), // General Purpose Register 1
    GPR1((byte) 0b01), // General Purpose Register 2
    GPR2((byte) 0b10), // General Purpose Register 3
    GPR3((byte) 0b11); // General Purpose Register 4

    public final byte id;

    private GeneralRegister(byte id) {
        this.id = id;
    }
}
