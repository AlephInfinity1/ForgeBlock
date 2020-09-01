package alephinfinity1.forgeblock.misc.mana;

import java.util.concurrent.Callable;

public class ManaFactory implements Callable<IMana> {

	@Override
	public IMana call() throws Exception {
		return new Mana();
	}

}
