package tutorial_000.languageNewFeatures;

public class _000_PreviewFeatures {
	/*
	 * The JDK gained two mechanisms to expose new "in development" functionality :
	 * - incubator modules for APIs.
	 * - preview features for language and JVM changes.
	 * 
	 * Both mechanisms work in a similar way :
	 * - They allow easy experimentation with new and possibly unstable features.
	 * - They prevent accidental dependencies on them by requiring command line flags during compilation and execution.
	 * 
	 * The only incubator module was the reactive HTTP/2 client in Java 9 and 10 (finally include in jdk 11), and the only preview feature are new switch expressions 
	 * in Java 12. 
	 * 
	 * To activate preview features, we need to use the flag --enable-preview (add it to compiler and JVM commands).
	 * - For example to use it in Maven :
			<plugin>
			    <artifactId>maven-compiler-plugin</artifactId>
			    <configuration>
			        <compilerArgs>
			            --enable-preview
			        </compilerArgs>
			    </configuration>
			</plugin>
			<plugin>
			    <artifactId>maven-surefire-plugin</artifactId>
			    <configuration>
			        <argLine>--enable-preview</argLine>
			    </configuration>
			</plugin>
	 *
	 * BEWARE : remember that a preview feature may change in future releases. Until it’s stabilized and definitively included in jdk, it would be better to avoid
	 * using this kind of functionality too much of our internal code and never publish code that uses it.
	 */
}
