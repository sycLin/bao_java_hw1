all:
	if ! [ -d "bin" ]; then mkdir bin; fi
	javac -d bin/ src/*.java
run1:
	java -cp bin/ HugeInteger
run2:
	java -cp bin/ Rational
clean:
	rm -rf bin/
