package net.viperfish.spellbook.task;

import java.util.concurrent.Callable;
import net.viperfish.spellbook.core.CRUDRepository;
import net.viperfish.spellbook.core.Item;
import net.viperfish.spellbook.core.ItemRequirement;
import net.viperfish.spellbook.core.Spell;

public class SaveSpellTask implements Callable<Spell> {

	private CRUDRepository<Long, Spell> spellRepo;
	private CRUDRepository<Long, Item> itemRepo;
	private Spell toSave;

	public SaveSpellTask(
		CRUDRepository<Long, Spell> repo, CRUDRepository<Long, Item> itemRepo,
		Spell toSave) {
		this.spellRepo = repo;
		this.itemRepo = itemRepo;
		this.toSave = toSave;
	}

	@Override
	public Spell call() throws Exception {
		for (ItemRequirement req : toSave.getRequirements()) {
			Item toAdd = itemRepo.get(req.getItem().getId());
			if (toAdd == null) {
				throw new IllegalArgumentException(
					"The item associated with the requirement does not exist");
			}
			req.setItem(toAdd);
		}
		spellRepo.persist(toSave);
		return toSave;
	}
}
