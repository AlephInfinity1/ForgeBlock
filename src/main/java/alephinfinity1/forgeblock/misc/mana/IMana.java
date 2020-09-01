package alephinfinity1.forgeblock.misc.mana;

public interface IMana {
	public void decrease(double amount);
	public void increase(double amount);
	public void set(double amount);
	
	public double getMana();
}
