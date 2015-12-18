JC = javac
JRE = java

FILE = Escaper
DEPS = *.java
IN = input.txt
IN2 = input2.txt

all: $(FILE).class execute

$(FILE).class: $(FILE).java $(DEPS)
	$(JC) $<

execute: $(FILE).class
	$(JRE) $(FILE) $(IN) | tee output.txt

execute_hard: $(FILE).class
	$(JRE) $(FILE) $(IN2) | tee output2.txt

tex: rapport.tex
	latexmk -xelatex --output-directory=tex_files $< && evince tex_files/rapport.pdf
