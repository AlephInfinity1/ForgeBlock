package alephinfinity1.forgeblock.misc;

/**
 * A utility class for calculating ranges.
 *
 * @param <T> The type of data in the range.
 */
public class Interval<T extends Comparable<T>> {

	/**
	 * The minimum value of the interval.
	 */
	protected T min;
	
	/**
	 * The maximum value of the interval.
	 */
	protected T max;
	
	/**
	 * Whether it is acceptable for the value to equal the minimum.
	 */
	protected boolean minInclusive;
	
	/**
	 * Whether it is acceptable for the value to equal the maximum.
	 */
	protected boolean maxInclusive;
	
	/**
	 * The type of interval this is. Used only for formatting reasons.
	 */
	protected IntervalType type;
	
	/**
	 * Creates a new closed interval between min and max. <br>
	 * By default, it is acceptable for the value to be equal to both extremes.
	 * @param min The minimum value
	 * @param max The maximum value
	 */
	public Interval(T min, T max) {
		this.min = min;
		this.max = max;
		this.minInclusive = true;
		this.maxInclusive = true;
		this.type = IntervalType.RANGE;
	}
	
	/**
	 * Creates a new interval between min and max.
	 * @param min The minimum value
	 * @param max The maximum value
	 * @param open Whether the interval is an open interval
	 */
	public Interval(T min, T max, boolean open) {
		this.min = min;
		this.max = max;
		this.minInclusive = !open;
		this.maxInclusive = !open;
		this.type = IntervalType.RANGE;
	}
	
	/**
	 * Creates a new interval.
	 * @param min The minimum value
	 * @param max The maximum value
	 * @param minInclusive Whether it is acceptable for the given value to be equal to the minimum
	 * @param maxInclusive Whether it is acceptable for the given value to be equal to the maximum
	 */
	public Interval(T min, T max, boolean minInclusive, boolean maxInclusive) {
		this.min = min;
		this.max = max;
		this.minInclusive = minInclusive;
		this.maxInclusive = maxInclusive;
		this.type = IntervalType.RANGE;
	}
	
	public Interval(T min, T max, boolean minInclusive, boolean maxInclusive, IntervalType type) {
		this(min, max, minInclusive, maxInclusive);
		this.type = type;
	}
	
	@Override
	public String toString() {
		return "Range: " + (minInclusive ? "[" : "(") + min.toString() + ", " + max.toString() + (maxInclusive ? "]" : ")");
	}
	
	public T getMin() {
		return min;
	}
	
	public T getMax() {
		return max;
	}
	
	public IntervalType getType() {
		return this.type;
	}
	
	public boolean isValueWithin(T val) {
		return ((val.compareTo(min) > 0) 
				|| (val.compareTo(min) == 0 && minInclusive)) 
				&& ((val.compareTo(max) < 0) 
						|| (val.compareTo(max) == 0 && maxInclusive));
	}
	
	public static class DoubleInterval extends Interval<Double> {

		public DoubleInterval(Double min, Double max) {
			super(min, max);	
		}
		
		public DoubleInterval(Double min, Double max, boolean open) {
			super(min, max, open);
		}
		
		public DoubleInterval(Double min, Double max, boolean minInclusive, boolean maxInclusive) {
			super(min, max, minInclusive, maxInclusive);
		}
		
		public DoubleInterval(Double min, Double max, boolean minInclusive, boolean maxInclusive, IntervalType type) {
			super(min, max, minInclusive, maxInclusive, type);
		}
		
		public static DoubleInterval under(double max, boolean inclusive) {
			return new DoubleInterval(Double.NEGATIVE_INFINITY, max, true, inclusive, IntervalType.UNDER);
		}
		
		public static DoubleInterval under(double max) {
			return DoubleInterval.under(max, false);
		}
		
		public static DoubleInterval underOrEqualsTo(double max) {
			return DoubleInterval.under(max, true);
		}
		
		public static DoubleInterval above(double min, boolean inclusive) {
			return new DoubleInterval(min, Double.POSITIVE_INFINITY, inclusive, true, IntervalType.ABOVE);
		}
		
		public static DoubleInterval above(double min) {
			return DoubleInterval.above(min, false);
		}
		
		public static DoubleInterval aboveOrEqualsTo(double min) {
			return DoubleInterval.above(min, true);
		}
		
		public static DoubleInterval epsilon(double val, double range) {
			return new DoubleInterval(val - range, val + range);
		}
		
	}
	
	public enum IntervalType {
		RANGE,
		UNDER,
		ABOVE
	}
	
}
