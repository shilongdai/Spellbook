package net.viperfish.spellbook.task;

import net.viperfish.spellbook.core.Item;
import net.viperfish.spellbook.db.ORMLiteRepository;

import javax.lang.model.type.NullType;
import java.util.concurrent.Callable;

public class AddItemTask implements Callable<NullType> {

	private ORMLiteRepository<Long, Item> itemRepo;
	private Item item;

	public AddItemTask(ORMLiteRepository<Long, Item> itemRepo, Item toAdd) {
		this.itemRepo = itemRepo;
		this.item = toAdd;
	}

	@Override
	public NullType call() throws Exception {
		itemRepo.persist(item);
		return null;
	}
}
