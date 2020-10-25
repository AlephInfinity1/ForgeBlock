package alephinfinity1.forgeblock.misc;

import java.util.Random;

import net.minecraft.util.math.MathHelper;

public class RNGHelper {

	public static boolean randomChance(double chance, Random random) {
		return random.nextDouble() < chance;
	}
	
	public static boolean fractionalChance(int numerator, int denominator, Random random) {
		return random.nextInt(denominator) < numerator;
	}
	
	/*
	 * The below methods refers to https://gaming.stackexchange.com/questions/161430/calculating-the-constant-c-in-dota-2-pseudo-random-distribution
	 * Dota 2's RNG generation
	 * For the Nth trial, the chance of obtaining an item is P(N) = C*N, where C is a constant.
	 * The C constant is lower than the average probability of getting an item, P.
	 * Thanks to Adam Smith for the C# implementation of code.
	 */
	
	public static double getCFromP(double P) {
		double upper = P;
		double lower = 0.0D;
		double mid;
		double p1;
		double p2 = 1.0D;
		while(true) {
			mid = (upper + lower) / 2;
			p1 = getPFromC(mid);
			if(MathHelper.epsilonEquals(p1, p2)) break;
			
			if(p1 > P) {
				upper = mid;
			} else {
				lower = mid;
			}
			
			p2 = p1;
		}
		
		return mid;
	}
	
	public static double getPFromC(double C) {
		if(C < 0.0D || C > 1.0D) throw new IllegalArgumentException("C must be between 0.0 and 1.0.");
		
		double procOn = 0.0D; //The probability of proccing on a try.
		double procBefore = 0.0D; //The probability of proccing before a try.
		double expectancy = 0.0D; //The expectancy number of procs to get a drop.
		
		final double MAX_TRIES = Math.ceil(1.0D / C); //The number of procs needed to get a guaranteed drop.
		
		for(int i = 1; i <= MAX_TRIES; i++) { //Simulates proccing.
			procOn = Math.min(C * i, 1.0D) * (1 - procBefore);
			procBefore += procOn; //Adds chance of being procced before.
			expectancy += i * procOn;
		}
		
		return (1.0D / expectancy);
	}
	
}
