package components;

import java.util.Map;

public class Memory {

    // The available memory size
    public static final int MEMORY_SIZE = 2048;

    // Memory region
    private Map<Character, Character> region;

    public Memory() {
        region = new java.util.HashMap<Character, Character>();
    }

    public char Read(char address) {
        if (address < 0 || address >= MEMORY_SIZE) {
            throw new IllegalArgumentException("Invalid memory address");
        }

        return region.getOrDefault(address, (char) 0);
    }

    public void Write(char address, char data) {
        if (address < 0 || address >= MEMORY_SIZE) {
            throw new IllegalArgumentException("Invalid memory address");
        }

        region.put(address, data);
    }
}
