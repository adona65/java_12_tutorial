package tutorial_000.languageNewFeatures;

public class _003_NewStringTransformations {
	public static void main(String args[]) {
		/*
		 * The first new things for working with strings in java 12 is about indentation. String include a new indent() method that allow to easily fix or remove
		 * indentation without having to do it "manually" with spaces.
		 * 
		 * Let's have a first example with a string with space indentation. If we wan't to simply remove this indentation we can do that :
		 */
		String html = ""
				+ "  <body>\n" // 2 spaces
				+ "    <h1>Header</h1>\n" // 4 spaces
				+ "  </body>"; // 2 spaces
		
		System.out.println("Left string unchange");
		System.out.println(html.indent(0));
		System.out.println("---------------------------------------------");
		System.out.println("Add 2 blank to each line");
		System.out.println(html.indent(2));
		System.out.println("---------------------------------------------");
		System.out.println("Add 4 blank to each line");
		System.out.println(html.indent(4));
		System.out.println("---------------------------------------------");
		System.out.println("Remove 2 blank from each line");
		System.out.println(html.indent(-2));
		System.out.println("---------------------------------------------");
		System.out.println("Remove 4 blank from each line");
		System.out.println(html.indent(-4));
		
		System.out.println("=============================================");
		/*
		 * One new thing is the transform() method that accept a Function (FunctionalInterface) to apply a custom modification to a string (it accept a particular String 
		 * instance as input and returns output returned by that function) :
		 */
		var result = "foo".transform(input -> input + " bar");
		System.out.println(result); // foo bar
		System.out.println("---------------------------------------------");
		// We can also chain these if we really feel like we need to:
		var result2 = "foo".transform(input -> input + " bar")
				  			.transform(String::toUpperCase);
		System.out.println(result2); // FOO BAR
	}
	
	
}
