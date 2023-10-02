package components;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Computer {

    public Processor processor;

    public Memory memory;

    public Computer() {
        memory = new Memory();
        processor = new Processor(memory);
    }

    /**
     * Loads a ROM file into the memory, this will overwrite any existing memory and
     * will not throw an exception if the file is invalid.
     * 
     * @param file The ROM file to load.
     */
    public void loadROM(File file) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));

            String line;

            while ((line = br.readLine()) != null) {
                String[] parts = line.split(" ");

                char address = (char) Integer.parseInt(parts[0], 16);
                char data = (char) Integer.parseInt(parts[1], 16);

                memory.privilegedWrite(address, data);
            }

        } catch (IOException e) {
            // TODO: Logging and better overhead exception handling
            e.printStackTrace();
        }
    }

}
