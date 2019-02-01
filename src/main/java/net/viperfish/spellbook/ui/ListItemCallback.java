package net.viperfish.spellbook.ui;

import net.viperfish.spellbook.core.Callback;
import net.viperfish.spellbook.core.Item;

public class ListItemCallback implements Callback<Iterable<Item>> {

	@Override
	public void call(Iterable<Item> result, Exception error) {
		if (error != null) {
			System.out.println("Failed to retrieve items:" + error.getMessage());
			error.printStackTrace();
			return;
		}
		for (Item i : result) {
			System.out.println("----------------------------");
			System.out.println("ID:" + i.getId());
			System.out.println("Name:" + i.getName());
			System.out.println("Amount:" + i.getAmount());
			System.out.println("Description:");
			System.out.println(i.getDesc());
		}
	}
}
