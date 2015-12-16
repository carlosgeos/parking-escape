JC = javac
JRE = java

FILE = Escaper
DEPS = *.java
IN = input.txt

all: $(FILE).class execute

$(FILE).class: $(FILE).java $(DEPS)
	javac $<

execute: $(FILE).class
	$(JRE) $(FILE) $(IN)

tex: rapport.tex
	latexmk -pdf --output-directory=tex_files $< && evince tex_files/rapport.pdf
