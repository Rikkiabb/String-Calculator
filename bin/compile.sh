X!/bin/bash
if [ ! -d classes ]Â; then
	mkdir classes
fi
javac src/main/java/is/ru/stringcalculator/*.java -d classes
