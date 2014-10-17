package is.ru.stringcalculator;
public class Calculator{
	private static final String splitString = "(,)|(\n)";

	private static final String newDelimiter = "//";

	public static int add(String text){
		if(text.equals(""))	return 0;
		else if(text.startsWith(newDelimiter)){
			String formattedString = formatString(text);
			return sum(splitNumbers(formattedString));
		}
		else if(delimiter(text)){
			return sum(splitNumbers(text));
		}
		else	return 1;
	}

	private static int toInt(String number){
		return Integer.parseInt(number);
	}

	private static String[] splitNumbers(String numbers){
		return numbers.split(splitString);
	}

	private static int sum(String[] numbers){
		int total = 0;
		int counterOfNegatives = 0;
		String errorString = "Negatives not allowed: ";
		for(String number : numbers){
			if(isNegative(toInt(number))){
				counterOfNegatives++;
				if(firstNegativeNumber(counterOfNegatives)){
					errorString += number;
				}
				else{
					errorString += ",";
					errorString += number;
				}
			}
			if(numberInRightRange(toInt(number))){
				total += toInt(number);
			}
		}
		if(areThereAnyNegativeNumbers(counterOfNegatives)){
			throwException(errorString);
		}
		return total;
	}

	private static boolean delimiter(String numbers){
		return (numbers.contains(",") || numbers.contains("\n"));
	}

	private static String formatString(String numbers){
		if(manyDelimiters(numbers)){
			String textWithCommas = numbers;
			String returnString = "";
			while(manyDelimiters(numbers)){ 
				int beginningOfDelimiter = (numbers.indexOf("[") + 1);
				int endOfDelimiter = numbers.indexOf("]");
				int startOfNumbers = numbers.indexOf("\n" + 1);
				String numbersWithoutSign = numbers.substring(startOfNumbers);
				String currentDelimiter = String.valueOf(numbers.substring(beginningOfDelimiter, endOfDelimiter));
				String[] changeDelimiter = currentDelimiter.split("");
				for(int i = 1; i < changeDelimiter.length; i++){
					changeDelimiter[i]  = "\\" + changeDelimiter[i];
				}
				String delimitersWithEscape = "";
				for(int i = 1; i < changeDelimiter.length; i++){
					delimitersWithEscape += changeDelimiter[i];
				}

				textWithCommas = numbersWithoutSign.replaceAll(delimitersWithEscape, ",");
				numbers  = numbers.substring(endOfDelimiter + 1, startOfNumbers) + textWithCommas;
			}
			textWithCommas = textWithCommas.replaceFirst("\n", "");
			return textWithCommas;
		}
		else{
			String numbersWithoutSign = numbers.substring(4);
			String currentDelimiter = String.valueOf(numbers.charAt(2));
			String textWithCommas = numbersWithoutSign.replaceAll(currentDelimiter, ",");
			return textWithCommas;
		}
	}

	private static boolean isNegative(int number){
		return number < 0;
	}

	private static boolean areThereAnyNegativeNumbers(int number){
		return number > 0;
	}

	private static void throwException(String error){
		throw new RuntimeException(error);
	}

	private static boolean firstNegativeNumber(int number){
		return number == 1;
	}

	private static boolean numberInRightRange(int number){
		return number <= 1000;
	}

	private static boolean manyDelimiters(String numbers){
		return numbers.contains("[");
	}
}
