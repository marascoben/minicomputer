JAVAC = javac
JAR = jar
JARFLAGS = cfe

BINDIR = bin

JARFILE = Simple.jar

# Java source files
SOURCES = src/ui/FrontPanel.java \
		  src/ui/Indicator.java \
		  src/ui/IndicatorGroup.java \
		  src/ui/IndicatorPanel.java \
		  src/components/Computer.java \
		  src/components/Memory.java \
		  src/components/Processor.java \
		  src/core/Instruction.java \
		  src/core/GeneralRegister.java \
		  src/core/IndexRegister.java \
		  src/util/LogFormat.java \
		  src/util/WordUtils.java \
		  src/Main.java

all: program
	
program:
	$(JAVAC) -d bin $(SOURCES)
	$(JAR) $(JARFLAGS) $(JARFILE) Main -C $(BINDIR) .

run:
	java -jar $(JARFILE)

# Clean up the compiled class files and JAR output
clean:
	rm -rf $(BINDIR) $(JARFILE)
