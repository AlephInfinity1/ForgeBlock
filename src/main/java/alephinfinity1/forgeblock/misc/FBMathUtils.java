package alephinfinity1.forgeblock.misc;

import net.minecraft.command.arguments.LocalLocationArgument;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.Vec3d;

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
	
	/**
	 * Get an offset location of an entity, similar to if it ran a command with ^ ^ ^
	 * @param entity The entity
	 * @param left The amount of blocks to move leftwards
	 * @param up The amount of blocks to move upwards
	 * @param forwards The amount of blocks to move forwards
	 * @return The offset Vec3d
	 */
	public Vec3d offsetEntityLocalCoordinates(Entity entity, double left, double up, double forwards) {
		return new LocalLocationArgument(left, up, forwards).getPosition(entity.getCommandSource());
	}

}
