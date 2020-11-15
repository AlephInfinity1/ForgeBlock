package alephinfinity1.forgeblock.misc;

/**
 * A utility class to determine the relationship between two objects.
 * @param <T> The type to compare with. Must be {@link Comparable}.
 */
public class CompareTuple<T extends Comparable<T>> {
	
	private T a;
	private T b;
	
	public CompareTuple(T a, T b) {
		this.a = a;
		this.b = b;
	}
	
	public T A() {
		return a;
	}
	
	public T B() {
		return b;
	}
	
	public boolean isEqual() {
		return a.equals(b);
	}
	
	public int compare() {
		return a.compareTo(b);
	}

}
