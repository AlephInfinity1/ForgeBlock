package alephinfinity1.forgeblock.misc.collections;

import java.util.concurrent.Callable;

public class CollectionsDataFactory implements Callable<ICollectionsData> {

	@Override
	public ICollectionsData call() throws Exception {
		return new CollectionsData();
	}

}
