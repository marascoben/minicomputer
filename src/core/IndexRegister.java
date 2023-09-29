package core;

public enum IndexRegister {
    IX1((byte) 0b00), // Index Register 1
    IX2((byte) 0b01), // Index Register 2
    IX3((byte) 0b10); // Index Register 3

    public final byte id;

    private IndexRegister(byte id) {
        this.id = id;
    }
}
