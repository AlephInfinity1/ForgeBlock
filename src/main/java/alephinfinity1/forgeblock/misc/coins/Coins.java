package alephinfinity1.forgeblock.misc.coins;

public class Coins implements ICoins {
	
	private double amount = 0.0D;

	@Override
	public void add(double amount) {
		this.amount += amount;
	}

	@Override
	public void minus(double amount) {
		this.amount -= amount;
	}

	@Override
	public void set(double amount) {
		this.amount = amount;
	}

	@Override
	public boolean consume(double amount) {
		if(amount > this.amount) return false;
		else {
			this.amount -= amount;
			return true;
		}
	}

	@Override
	public double getCoins() {
		return this.amount;
	}

}
