package net.viperfish.spellbook.ui;

import net.viperfish.spellbook.core.Callback;

public class RemoveStuffCallback<T> implements Callback<T> {

	@Override
	public void call(T result, Exception error) {
		if (error != null) {
			System.out.println("Failed to remove the specified record:" + error.getMessage());
			error.printStackTrace();
			return;
		}
		System.out.println("Item Removed");
	}
}
