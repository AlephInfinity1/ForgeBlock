package alephinfinity1.forgeblock.misc;

/**
 * A class dedicated to functions and convenient utilities relating to mathematics.
 */
public class FBMathUtils {
	
	private FBMathUtils() {
		throw new AssertionError("Class " + FBMathUtils.class.toGenericString() + " should not be instantiated!");
	}
	
	/**
	 * Gets the number in the list in which the provided value is closest to.
	 * @param value The value to compare with.
	 * @param list The list of values.
	 * @return The value in the list that is closest to the given number.
	 */
	public int getClosestValue(int value, int [] list) {
		//Initial handling, dealing with special list lengths
		if (list.length == 0) {
			throw new IllegalArgumentException("Array should not be empty!");
		} else if (list.length == 1) {
			return list[0];
		}
		
		int minDifference = Math.abs(list[0] - value);
		int index = 0;
		
		for (int i = 1; i < list.length; i++) {
			if (minDifference > Math.abs(list[i] - value)) {
				minDifference = Math.abs(list[i] - value);
				index = i;
			}
		}
		
		return list[index];
	}
	
	/**
	 * Gets the number in the list in which the provided value is closest to.
	 * @param value The value to compare with.
	 * @param list The list of values.
	 * @return The value in the list that is closest to the given number.
	 */
	public float getClosestValue(float value, float [] list) {
		//Initial handling, dealing with special list lengths
		if (list.length == 0) {
			throw new IllegalArgumentException("Array should not be empty!");
		} else if (list.length == 1) {
			return list[0];
		}
		
		float minDifference = Math.abs(list[0] - value);
		int index = 0;
		
		for (int i = 1; i < list.length; i++) {
			if (minDifference > Math.abs(list[i] - value)) {
				minDifference = Math.abs(list[i] - value);
				index = i;
			}
		}
		
		return list[index];
	}
	
	/**
	 * Gets the number in the list in which the provided value is closest to.
	 * @param value The value to compare with.
	 * @param list The list of values.
	 * @return The value in the list that is closest to the given number.
	 */
	public double getClosestValue(double value, double [] list) {
		//Initial handling, dealing with special list lengths
		if (list.length == 0) {
			throw new IllegalArgumentException("Array should not be empty!");
		} else if (list.length == 1) {
			return list[0];
		}
		
		double minDifference = Math.abs(list[0] - value);
		int index = 0;
		
		for (int i = 1; i < list.length; i++) {
			if (minDifference > Math.abs(list[i] - value)) {
				minDifference = Math.abs(list[i] - value);
				index = i;
			}
		}
		
		return list[index];
	}

}
