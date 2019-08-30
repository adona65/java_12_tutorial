package tutorial_000.languageNewFeatures;

import java.io.FileNotFoundException;
import java.io.UncheckedIOException;

public class _001_SwitchExpressions {
	/*
	 * BEWARE : Remember that this is a preview feature (and so subject to changes/delete in future releases).
	 */
	
	public static void main(String args[]) {
		/*
		 * One of the main new changes in the switch is it's now no longer just a statement (which directs where computation goes, like if), but an expression 
		 * (which is itself computed to a result, like the conditional/ternary operator "... ? ... : ..."). It now get a lambda-like syntax instead of classicals
		 * break/return couples.
		 * 
		 * The main use case will be to assign the computed value to a variable (see assignSwitch()) :
		 */
		assignSwitch(switchOptions.TWO);
		System.out.println("---------------------------------------------");
		try {
			assignSwitch(switchOptions.EXCEPTION);
		} catch (Exception e) {
			System.out.println("Switch right side exception => " + e.getMessage());
		}
		System.out.println("---------------------------------------------");
		assignSwitch(switchOptions.BLOCK);
		System.out.println("=============================================");
		unknownTypeAssignSwitch(switchOptions.ONE);
		System.out.println("=============================================");
		unknownTypeAssignSwitch(switchOptions.THREE);
		System.out.println("=============================================");
		unknownTypeAssignSwitch(switchOptions.EXCEPTION);
		System.out.println("=============================================");
		unknownTypeAssignSwitch(switchOptions.BLOCK);
	}
	
	/**
	 * Following method contain some new switch features (see comments).
	 */
	@SuppressWarnings("preview")
	private static void assignSwitch(switchOptions argument) {
		String switchResult = switch (argument) {
			// Multiple case labels (ONE, TWO).
		    case ONE, TWO -> "first case";
		    // No fall-through from one case to the next. There is no break, but if the code enter "ONE", it won't enter "THREE" or "FOUR" cases.
		    case THREE -> "second case";
		    // Right-hand side can be expression, exception (both demo'ed here), or block (see `block`)
		    case EXCEPTION -> throw new UncheckedIOException("Dummy exception.", new FileNotFoundException());
		    // Right-hand side can be block of code.
		    case BLOCK -> {
		    	System.out.println("Inside the block");
		    	// Return of the block is perform with "break" (use "return" would perform return of the method, not the block !).
		    	break "block";
		    }
		    // The compiler checks exhaustiveness. It see that all "switchOptions" enum options are present in the switch statement. So, there is no 
		    // need of a default statement.
		};
		
		System.out.println(switchResult);
	}
	
	/**
	 * Following method show that we also may use new switch to to define variable even if the final type is unknown (thanks to "var" keyword).
	 */
	@SuppressWarnings("preview")
	private static void unknownTypeAssignSwitch(switchOptions argument) {
		var switchResult = switch (argument) {
		    case ONE, TWO -> "first case";
		    case THREE -> 1;
		    case EXCEPTION -> new UncheckedIOException("Dummy exception.", new FileNotFoundException());
		    case BLOCK -> {
		    	System.out.println("Inside the block");
		    	break Boolean.valueOf(true);
		    }
		};
		
		System.out.println("unknownTypeAssignSwitch result is of type => " + switchResult.getClass());
	}
	
	enum switchOptions {
		ONE, TWO, THREE, EXCEPTION, BLOCK;
	}
	
}
