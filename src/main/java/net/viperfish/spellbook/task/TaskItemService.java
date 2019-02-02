package net.viperfish.spellbook.task;

import net.viperfish.spellbook.core.CRUDRepository;
import net.viperfish.spellbook.core.Callback;
import net.viperfish.spellbook.core.Item;
import net.viperfish.spellbook.core.ItemService;

public class TaskItemService extends TaskCRUDService<Long, Item> implements ItemService {

	public TaskItemService(
		CRUDRepository<Long, Item> repo) {
		super(repo);
	}

	@Override
	public void execChangeAmount(Long id, Double amount, Callback<Double> callback) {
		ChangeAmountTask changer = new ChangeAmountTask(repo, id, amount);
		scheduler.submit(changer, callback);
	}
}
