package components;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Logger;

public class Computer {

    private static final Logger LOGGER = Logger.getLogger(Computer.class.getName());

    // The processor object of the minicomputer
    public Processor processor;

    // The memory object of the minicomputer
    public Memory memory;

    // The ROM file to load into memory
    public File rom;

    public Computer() {
        memory = new Memory();
        processor = new Processor(memory);

        LOGGER.info("Minicomputer started");
    }

    public void reset() {
        memory = new Memory();
        processor = new Processor(memory);

        if (rom != null) {
            loadROM(rom);
        }

        LOGGER.info("Resetting minicomputer");
    }

    public void runInstruction(char word) {
        processor.execute(word);
    };

    /**
     * Loads a ROM file into the memory, this will overwrite any existing memory and
     * will not throw an exception if the file is invalid.
     * 
     * @param file The ROM file to load.
     */
    public void loadROM(File file) {
        if (rom == null) {
            rom = file;
        }

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));

            LOGGER.info("Loading ROM file " + file.getName());

            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(" ");

                char address = (char) Integer.parseInt(parts[0], 16);
                char data = (char) Integer.parseInt(parts[1], 16);

                LOGGER.info("\u2523 Writing " + String.format("0x%08X", (short) data) + " to memory location "
                        + String.format("0x%08X", (short) address));

                memory.privilegedWrite(address, data);
            }

            br.close();
            LOGGER.info("\u2517 ROM file " + file.getName() + " finished loading");

        } catch (IOException e) {
            LOGGER.warning("Failed to load ROM file " + file.getName() + ": " + e.getMessage());
        }
    }

}
