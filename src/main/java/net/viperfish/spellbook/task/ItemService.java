package net.viperfish.spellbook.task;

import net.viperfish.spellbook.core.CRUDRepository;
import net.viperfish.spellbook.core.Item;

public class ItemService extends TaskCRUDService<Long, Item> {

	public ItemService(
		CRUDRepository<Long, Item> repo) {
		super(repo);
	}
}
