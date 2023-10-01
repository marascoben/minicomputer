package components;

public class Computer {

    public Processor processor;

    public Memory memory;

    public Computer() {
        memory = new Memory();
        processor = new Processor(memory);
    }

}
