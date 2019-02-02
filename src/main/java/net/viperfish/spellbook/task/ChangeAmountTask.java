package net.viperfish.spellbook.task;

import java.util.concurrent.Callable;
import net.viperfish.spellbook.core.CRUDRepository;
import net.viperfish.spellbook.core.Item;

public class ChangeAmountTask implements Callable<Double> {

	private CRUDRepository<Long, Item> itemRepo;
	private Long itemID;
	private Double delta;

	public ChangeAmountTask(
		CRUDRepository<Long, Item> itemRepo, Long itemID, Double delta) {
		this.itemRepo = itemRepo;
		this.itemID = itemID;
		this.delta = delta;
	}

	@Override
	public Double call() throws Exception {
		Item toChange = itemRepo.get(itemID);
		if (toChange == null) {
			throw new IllegalArgumentException("Item not found");
		}
		toChange.setAmount(toChange.getAmount() + delta);
		itemRepo.persist(toChange);
		return toChange.getAmount();
	}
}
