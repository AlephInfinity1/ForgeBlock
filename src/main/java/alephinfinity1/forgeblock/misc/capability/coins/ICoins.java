package alephinfinity1.forgeblock.misc.capability.coins;

public interface ICoins {
	public void add(double amount);
	public void minus(double amount);
	public void set(double amount);
	
	/**
	 * Consumes money (e.g. buying in a shop), boolean indicates whether consumation is successful.
	 * No money is consumed if consumation is not successful.
	 * @param amount How much money to consume.
	 * @return whether money is consumed successfully.
	 */
	public boolean consume(double amount);
	
	public double getCoins();
}
