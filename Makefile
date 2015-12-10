all: main.class execute

main.class: main.java
	javac $<

execute: main.class
	java main
