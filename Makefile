JC = javac
JRE = java

FILE = Escaper
DEPS = *.java

all: $(FILE).class execute

$(FILE).class: $(FILE).java $(DEPS)
	javac $<

execute: $(FILE).class
	$(JRE) $(FILE)
