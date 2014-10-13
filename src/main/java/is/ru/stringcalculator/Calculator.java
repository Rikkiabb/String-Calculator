package is.ru.stringcalculator;
public class Calculator{
	private static final String splitString = "(,)|(\n)";

	public static int add(String text){
		if(text.equals(""))	return 0;
		else if(text.startsWith("//")){
			String newText = text.substring(4);
			String thisDelimiter = String.valueOf(text.charAt(2));
			String textWithComma = newText.replaceAll(thisDelimiter, ",");
			return sum(splitNumbers(textWithComma));
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
		for(String number : numbers){
			total += toInt(number);
		}
		return total;
	}

	private static boolean delimiter(String numbers){
		return (numbers.contains(",") || numbers.contains("\n"));
	}
}
