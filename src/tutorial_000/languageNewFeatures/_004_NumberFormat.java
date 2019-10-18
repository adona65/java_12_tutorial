package tutorial_000.languageNewFeatures;

import java.text.NumberFormat;
import java.util.Locale;

public class _004_NumberFormat {

	public static void main(String[] args) {
		/*
		 * Java 12 introduce new usefull java.text.CompactNumberForma class that allow us to format numbers to compact and short formats. We will see the
		 * differences in formating number by either changing local (for US, UK and DE) and number size (tens, hundreds, thousands  and so on...). All examples
		 * are set with maximum fraction digits to 1.
		 */
		// Example for formating "tens" numbers.
		System.out.println("Will display numbers formating for with tens example (original number : 18)");
		compactNumberFormatting(18);
		
		System.out.println("=============================================");
		// Example for formating "hundreds" numbers.
		System.out.println("Will display numbers formating for with hundreds example (original number : 138)");
		compactNumberFormatting(138);
		
		System.out.println("=============================================");
		// Example for formating "thousands" numbers.
		System.out.println("Will display numbers formating for with thousands example (original number : 1_254)");
		compactNumberFormatting(1_254);
		
		System.out.println("=============================================");
		// Example for formating "millions" numbers.
		System.out.println("Will display numbers formating for with millions example (original number : 1_235_457)");
		compactNumberFormatting(1_235_457);
	}

	/**
	 * Format given number in different styles :
	 * - Default
	 * - US short/long 
	 * - FR short/long 
	 * - DE short/long 
	 */
	private static void compactNumberFormatting(final long numberToFormat) {  
		final NumberFormat numberFormatDefault = NumberFormat.getCompactNumberInstance();  
		// Formating in US style
		final NumberFormat numberFormatUsLong = NumberFormat.getCompactNumberInstance(Locale.US, NumberFormat.Style.LONG);  
		final NumberFormat numberFormatUsShort = NumberFormat.getCompactNumberInstance(Locale.US, NumberFormat.Style.SHORT);  
		// Formating in FR style
		final NumberFormat numberFormatFrLong = NumberFormat.getCompactNumberInstance(Locale.FRANCE, NumberFormat.Style.LONG); 
		final NumberFormat numberFormatFrShort = NumberFormat.getCompactNumberInstance(Locale.FRANCE, NumberFormat.Style.SHORT);  
		// Formating in DE style
		final NumberFormat numberFormatGrLong = NumberFormat.getCompactNumberInstance(Locale.GERMANY, NumberFormat.Style.LONG);  
		final NumberFormat numberFormatGrShort = NumberFormat.getCompactNumberInstance(Locale.GERMANY, NumberFormat.Style.SHORT);  
		
		// Set maximum frationc digits for all previous styles to 1.
		numberFormatDefault.setMaximumFractionDigits(1);
		numberFormatUsLong.setMaximumFractionDigits(1);
		numberFormatUsShort.setMaximumFractionDigits(1);
		numberFormatFrLong.setMaximumFractionDigits(1);
		numberFormatFrShort.setMaximumFractionDigits(1);
		numberFormatGrLong.setMaximumFractionDigits(1);
		numberFormatGrShort.setMaximumFractionDigits(1);
	  
	    System.out.println("Default:  " + numberFormatDefault.format(numberToFormat));  
	    System.out.println("US Long style:  " + numberFormatUsLong.format(numberToFormat));  
	    System.out.println("US Short style:  " + numberFormatUsShort.format(numberToFormat));  
	    System.out.println("FR Long style:  " + numberFormatFrLong.format(numberToFormat));  
	    System.out.println("FR Short style: " + numberFormatFrShort.format(numberToFormat));  
	    System.out.println("DE Long style:  " + numberFormatGrLong.format(numberToFormat));  
	    System.out.println("DE Short style: " + numberFormatGrShort.format(numberToFormat));  
	}  
}
