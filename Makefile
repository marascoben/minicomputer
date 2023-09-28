JAVAC = javac
JAR = jar
JARFLAGS = cfe

BINDIR = bin

JARFILE = Simple.jar

# Java source files
SOURCES = src/ui/BitDisplay.java \
		  src/ui/MainWindow.java \
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
