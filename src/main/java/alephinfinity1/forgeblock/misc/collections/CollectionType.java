package alephinfinity1.forgeblock.misc.collections;

public enum CollectionType {
	FARMING("farming"),
	MINING("mining"),
	COMBAT("combat"),
	FORAGING("foraging"),
	FISHING("fishing"),
	BOSS("boss");
	
	private final String id;
	
	private CollectionType(String id) {
		this.id = id;
	}
	
	public String getID() {
		return id;
	}
}
