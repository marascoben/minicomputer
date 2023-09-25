JAVAC = javac
JAR = jar
JARFLAGS = cfe

# Directory storing source java files
SOURCE = src

# Directory storing built class files
BINDIR = bin

# Output jarfile name
JARFILE = Simple

# Find all Java source files in the 'src' directory
SOURCES := $(wildcard $(SOURCE)/*.java)

# Generate corresponding class file paths
CLASSES := $(patsubst $(SOURCE)/%.java, $(BINDIR)/%.class, $(SOURCES))

# Compile the Java source files into class files
$(BINDIR)/%.class: $(SOURCE)/%.java
	@mkdir -p $(BINDIR)
	$(JAVAC) -d $(BINDIR) $<

# Create the JAR file with the compiled classes
$(JARFILE): $(CLASSES)
	$(JAR) $(JARFLAGS) $(JARFILE).jar Main -C $(BINDIR) .

.PHONY: run
run:
	java -jar $(JARFILE).jar

# Clean the compiled files and JAR
.PHONY: clean
clean:
	rm -rf $(BINDIR) $(JARFILE)
