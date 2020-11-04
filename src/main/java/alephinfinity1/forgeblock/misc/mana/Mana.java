package alephinfinity1.forgeblock.misc.mana;

public class Mana implements IMana {

	private double mana = 100;

	@Override
	public void decrease(double amount) {
		this.mana -= amount;
	}

	@Override
	public void increase(double amount) {
		this.mana += amount;
	}

	@Override
	public void set(double amount) {
		this.mana = amount;
	}

	@Override
	public double getMana() {
		return this.mana;
	}

	/**
	 * Consumes a set amount of mana
	 * 
	 * @param amount - the amount of mana consumed
	 * @return boolean - whether the consumption is successful or not.
	 */
	@Override
	public boolean consume(double amount) {
		if(this.mana < amount) return false;
		else {
			this.decrease(amount);
			return true;
		}
	}

}
