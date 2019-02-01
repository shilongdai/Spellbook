package net.viperfish.spellbook.task;

import net.viperfish.spellbook.core.CRUDRepository;
import net.viperfish.spellbook.core.Callback;
import net.viperfish.spellbook.core.Item;
import net.viperfish.spellbook.core.Spell;

public class SpellService extends TaskCRUDService<Long, Spell> {

	private CRUDRepository<Long, Item> itemRepo;

	public SpellService(
		CRUDRepository<Long, Spell> repo, CRUDRepository<Long, Item> itemRepo) {
		super(repo);
		this.itemRepo = itemRepo;
	}

	@Override
	public void execPersist(Spell data, Callback<? super Spell> callback) {
		SaveSpellTask saver = new SaveSpellTask(repo, itemRepo, data);
		scheduler.submit(saver, callback);
	}

	@Override
	public void execPersist(Iterable<Spell> data, Callback<Iterable<Spell>> callback) {
		SaveManySpellTask saver = new SaveManySpellTask(repo, itemRepo, data);
		scheduler.submit(saver, callback);
	}
}
