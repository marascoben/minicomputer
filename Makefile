JAVAC = javac
JAR = jar
JARFLAGS = cfe

BINDIR = bin

JARFILE = minicomputer.jar

# Java source files
SOURCES = src/components/Computer.java \
		  src/components/Memory.java \
		  src/components/ROM.java \
		  src/components/cpu/ALU.java \
		  src/components/cpu/Processor.java \
		  src/components/io/Device.java \
		  src/components/io/IOBus.java \
		  src/config/Config.java \
		  src/core/Condition.java \
		  src/core/Fault.java \
		  src/core/GeneralRegister.java \
		  src/core/IndexRegister.java \
		  src/core/Instruction.java \
		  src/core/Opcode.java \
		  src/core/func/ArithmeticFunction.java \
		  src/core/func/IOFunction.java \
		  src/core/func/LoadFunction.java \
		  src/core/func/LogicFunction.java \
		  src/core/func/StoreFunction.java \
		  src/core/func/TransferFunction.java \
		  src/ui/components/FrontPanelMenu.java \
		  src/ui/components/GroupPanel.java \
		  src/ui/components/Indicator.java \
		  src/ui/components/IndicatorGroup.java \
		  src/ui/components/RegisterTextField.java \
		  src/ui/listeners/HardwareListener.java \
		  src/ui/panels/registers/GeneralRegisterPanel.java \
		  src/ui/panels/registers/IndexRegisterPanel.java \
		  src/ui/panels/registers/MiscRegisterPanel.java \
		  src/ui/panels/ControlPanel.java \
		  src/ui/panels/IndicatorPanel.java \
		  src/ui/panels/InputPanel.java \
		  src/ui/peripheral/Console.java \
		  src/ui/peripheral/Keyboard.java \
		  src/ui/windows/ConsoleWindow.java \
		  src/ui/Colors.java \
		  src/ui/FrontPanel.java \
		  src/util/FormatUtils.java \
		  src/util/LogFormat.java \
		  src/util/ScreenUtil.java \
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
