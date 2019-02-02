package net.viperfish.spellbook.task;

import java.util.concurrent.Callable;
import net.viperfish.spellbook.core.CRUDRepository;
import net.viperfish.spellbook.core.Item;
import net.viperfish.spellbook.core.ItemRequirement;
import net.viperfish.spellbook.core.Spell;

public class CastSpellTask implements Callable<Boolean> {

	private CRUDRepository<Long, Spell> spellRepo;
	private CRUDRepository<Long, Item> itemRepo;
	private Long spellID;

	public CastSpellTask(
		CRUDRepository<Long, Spell> spellRepo,
		CRUDRepository<Long, Item> itemRepo, Long spellID) {
		this.spellRepo = spellRepo;
		this.itemRepo = itemRepo;
		this.spellID = spellID;
	}

	@Override
	public Boolean call() throws Exception {
		Spell spell = spellRepo.get(spellID);
		if (spell == null) {
			throw new IllegalArgumentException("The spell doesn't exist");
		}
		for (ItemRequirement req : spell.getRequirements()) {
			if (req.getAmount() > req.getItem().getAmount()) {
				return false;
			}
		}
		for (ItemRequirement req : spell.getRequirements()) {
			req.getItem().setAmount(req.getItem().getAmount() - req.getAmount());
			itemRepo.persist(req.getItem());
		}
		return true;
	}
}
