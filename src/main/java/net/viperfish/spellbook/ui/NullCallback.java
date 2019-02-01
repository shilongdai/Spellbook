package net.viperfish.spellbook.ui;

import net.viperfish.spellbook.core.Callback;

public class NullCallback implements Callback<Object> {

	@Override
	public void call(Object result, Exception error) {
		// do nothing
	}
}
