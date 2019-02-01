package net.viperfish.spellbook.ui;

import net.viperfish.spellbook.core.Callback;
import net.viperfish.spellbook.core.Item;

public class ItemAddedCallback implements Callback<Item> {

	@Override
	public void call(Item result, Exception error) {
		if (error != null) {
			System.out.println("Failed to add item:" + error.getMessage());
			error.printStackTrace();
			return;
		}
		System.out.println("Added:" + result.getName());
		System.out.println("ID:" + result.getId());
	}
}
