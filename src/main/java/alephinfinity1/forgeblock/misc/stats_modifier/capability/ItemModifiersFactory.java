package alephinfinity1.forgeblock.misc.stats_modifier.capability;

import java.util.concurrent.Callable;

public class ItemModifiersFactory implements Callable<IItemModifiers> {

	@Override
	public IItemModifiers call() throws Exception {
		return new ItemModifiers();
	}

}
