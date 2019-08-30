package tutorial_000.languageNewFeatures;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Objects.requireNonNull;

import java.util.Optional;

public class _002_TeeingCollectors {
	public static void main(String args[]) {
		/*
		 * Sometimes, we need to retrieve tow pieces of informations from a Stream. Before Java 12, it might be not very comfortable to do that in some case.
		 * Teeing collectors was introduced to fix that.
		 * 
		 * For example, let say we have a stream of Integer and we want retrieve the smallest and greatest elements and store them in a "Range" object. Prior to
		 * Java 12, we might have done something like what follow :
		 */
		Range<Integer> priorJava12Range = Stream
			    .of(2, 8, 1, 5, 7)
			    .reduce(
			    	// The object we will use to store the "reduce" results.
			        Range.of(Integer.MAX_VALUE, Integer.MIN_VALUE),
			        // Merge a range with the next number of the Stream if rules are filled.
			        (_range, number) -> {
			            int newMin = Math.min(number, _range.min());
			            int newMax = Math.max(number, _range.max());
			            return Range.of(newMin, newMax);
			        },
			        // Merge two ranges.
			        (_range1, _range2) -> {
			            int newMin = Math.min(_range1.min(), _range2.min());
			            int newMax = Math.max(_range1.max(), _range2.max());
			            return Range.of(newMin, newMax);
			    });
		
		System.out.println("Prio java 12 min => " + priorJava12Range.min());
		System.out.println("Prio java 12 max => " + priorJava12Range.max());
		
		System.out.println("---------------------------------------------");
		/*
		 * The problem of the "prior to Java 12" way to do is that we had to wrote a lot of code, but it already exists Collectors methods minBy() and maxBy() that perform
		 * exactly what we want. The Java 12's teeing collector allow us to use this minBy() and maxBy() methods in an easy way. Just like Unix’ tee command, it forwards each 
		 * element the stream passes to it to the two specified collectors (in our case minBy() and maxBy()). Once the stream is exhausted, it combines the two results into a 
		 * single instance with the third argument we specify, a function.
		 * 
		 * This way of doing reduce our code to the following example :
		 */

		Range<Integer> java12Range = Stream
		    .of(2, 8, 1, 5, 7)
		    .collect(Collectors.teeing(
		        // The two following collectors produce Optional<Integer>
		        Collectors.minBy(Integer::compareTo),
		        Collectors.maxBy(Integer::compareTo),
		        // Get two Optional<Integer> (thus created from the previous collectors), and return an Optional<Range<Integer>>.
		        Range::ofOptional)
		    )
		    // Return a Range<Integer> if present (returned by collect()), otherwise throws an IllegalStateException.
		    .orElseThrow(() -> new IllegalStateException("Non-empty stream was empty."));
		
		System.out.println("Java 12 min => " + java12Range.min());
		System.out.println("Java 12 max => " + java12Range.max());
		
		System.out.println("---------------------------------------------");
		/*
		 * Following, one last example for well understanding. It create a custom "Statistics" object using two different collectors :
		 */
		Statistics stats = Stream
				.of(1, 2, 4, 5)
				.collect(
					Collectors.teeing(
						Collectors.summingInt(i -> i),
						Collectors.averagingInt(i -> i),
						Statistics::of
					)
				);
		
		System.out.println("Java 12 sum => " + stats.sum());
		System.out.println("Java 12 average => " + stats.average());
	}
	
	public static class Statistics {

		private final int sum;
		private final double average;

		private Statistics(int sum, double average) {
			this.sum = sum;
			this.average = average;
		}

		public static Statistics of(int sum, double average) {
			return new Statistics(sum, average);
		}

		public int sum() {
			return sum;
		}

		public double average() {
			return average;
		}
	}
	
	public static class Range<T> {

		private final T min;
		private final T max;

		private Range(T min, T max) {
			this.min = requireNonNull(min);
			this.max = requireNonNull(max);
		}

		public static <T> Range<T> of(T min, T max) {
			return new Range<T>(min, max);
		}
		
		public static <T> Optional<Range<T>> ofOptional(Optional<T> min, Optional<T> max) {
			if (min.isEmpty() || max.isEmpty())
				return Optional.empty();
			return Optional.of(new Range<T>(min.get(), max.get()));
		}

		public T min() {
			return min;
		}

		public T max() {
			return max;
		}
	}
}
