package net.viperfish.spellbook.task;

import java.util.concurrent.Callable;
import net.viperfish.spellbook.core.CRUDRepository;
import net.viperfish.spellbook.core.Item;
import net.viperfish.spellbook.core.ItemRequirement;
import net.viperfish.spellbook.core.Spell;

public class SaveManySpellTask implements Callable<Iterable<Spell>> {

	private CRUDRepository<Long, Spell> spellRepo;
	private CRUDRepository<Long, Item> itemRepo;
	private Iterable<Spell> spellCollection;

	public SaveManySpellTask(
		CRUDRepository<Long, Spell> spellRepo,
		CRUDRepository<Long, Item> itemRepo,
		Iterable<Spell> toSave) {
		this.spellRepo = spellRepo;
		this.itemRepo = itemRepo;
		this.spellCollection = toSave;
	}

	@Override
	public Iterable<Spell> call() throws Exception {
		for (Spell toSave : spellCollection) {
			for (ItemRequirement req : toSave.getRequirements()) {
				Item toAdd = itemRepo.get(req.getItem().getId());
				if (toAdd == null) {
					throw new IllegalArgumentException(
						"The item associated with the requirement does not exist");
				}
				req.setItem(toAdd);
			}
		}
		spellRepo.persist(spellCollection);
		return spellCollection;
	}
}
