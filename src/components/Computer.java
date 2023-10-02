package components;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Logger;

public class Computer {

    private static final Logger LOGGER = Logger.getLogger(Computer.class.getName());

    public Processor processor;

    public Memory memory;

    public File rom;

    public Computer() {
        memory = new Memory();
        processor = new Processor(memory);

        LOGGER.info("Minicomputer started");
    }

    public Computer(File rom) {
        memory = new Memory();
        processor = new Processor(memory);
        this.rom = rom;

        loadROM(rom);

        LOGGER.info("Minicomputer started after loading ROM file");
    }

    public void reset() {
        memory = new Memory();
        processor = new Processor(memory);

        if (rom != null) {
            loadROM(rom);
        }

        LOGGER.info("Minicomputer reset");
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

                LOGGER.info("Loading memory location" + String.format("0x%08X", (short) address) + " with value "
                        + String.format("0x%08X", (short) data));

                memory.privilegedWrite(address, data);
            }

            br.close();

        } catch (IOException e) {
            LOGGER.warning("Failed to load ROM file " + file.getName() + ": " + e.getMessage());
        }
    }

}
