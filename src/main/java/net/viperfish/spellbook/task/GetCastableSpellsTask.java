package net.viperfish.spellbook.task;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import net.viperfish.spellbook.core.CRUDRepository;
import net.viperfish.spellbook.core.Item;
import net.viperfish.spellbook.core.ItemRequirement;
import net.viperfish.spellbook.core.Spell;

public class GetCastableSpellsTask implements Callable<Iterable<Spell>> {

	private CRUDRepository<Long, Spell> spellRepo;
	private CRUDRepository<Long, Item> itemRepo;

	public GetCastableSpellsTask(
		CRUDRepository<Long, Spell> spellRepo,
		CRUDRepository<Long, Item> itemRepo) {
		this.spellRepo = spellRepo;
		this.itemRepo = itemRepo;
	}

	@Override
	public Iterable<Spell> call() throws Exception {
		List<Spell> result = new ArrayList<>();
		for (Spell sp : spellRepo.getAll()) {
			boolean add = true;
			for (ItemRequirement req : sp.getRequirements()) {
				if (req.getItem().getAmount() < req.getAmount()) {
					add = false;
				}
			}
			if (add) {
				result.add(sp);
			}
		}
		return result;
	}
}
