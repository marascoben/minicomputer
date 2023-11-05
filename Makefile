JAVAC = javac
JAR = jar
JARFLAGS = cfe

BINDIR = bin

JARFILE = minicomputer.jar

# Java source files
SOURCES = src/components/Computer.java \
		  src/components/Memory.java \
		  src/components/Processor.java \
		  src/core/GeneralRegister.java \
		  src/core/IndexRegister.java \
		  src/core/Instruction.java \
		  src/config/Config.java \
		  src/ui/components/GroupPanel.java \
		  src/ui/components/Indicator.java \
		  src/ui/components/IndicatorGroup.java \
		  src/ui/panels/registers/GeneralRegisterPanel.java \
		  src/ui/panels/registers/IndexRegisterPanel.java \
		  src/ui/panels/registers/MiscRegisterPanel.java \
		  src/ui/panels/ControlPanel.java \
		  src/ui/panels/IndicatorPanel.java \
		  src/ui/panels/RegisterPanel.java \
		  src/ui/Colors.java \
		  src/ui/FrontPanel.java \
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
