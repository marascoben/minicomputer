JAVAC = javac
JAR = jar
JARFLAGS = cfe

BINDIR = bin

JARFILE = Simple.jar

# Java source files
SOURCES = src/ui/Int16Display.java \
		  src/Main.java
		
# Generate corresponding class file paths
CLASSES := $(patsubst src/%.java, $(BINDIR)/%.class, $(SOURCES))

all: $(SOURCES)
	$(JAVAC) -d bin $(SOURCES)
	$(JAR) $(JARFLAGS) $(JARFILE) Main -C $(BINDIR) .

# Clean up the compiled class files and JAR output
.PHONY: clean
	rm -rf $(BINDIR) $(OUTPUT)
