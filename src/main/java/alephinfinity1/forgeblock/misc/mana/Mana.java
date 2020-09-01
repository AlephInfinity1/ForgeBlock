package alephinfinity1.forgeblock.misc.mana;

public class Mana implements IMana {

	private double mana = 0;

	@Override
	public void decrease(double amount) {
		mana -= amount;
	}

	@Override
	public void increase(double amount) {
		mana += amount;
	}

	@Override
	public void set(double amount) {
		mana = amount;
	}

	@Override
	public double getMana() {
		return mana;
	}

}
