package alephinfinity1.forgeblock.misc.coins;

import java.util.concurrent.Callable;

public class CoinsFactory implements Callable<ICoins> {

	@Override
	public ICoins call() throws Exception {
		return new Coins();
	}

}
