package net.viperfish.spellbook.task;

import net.viperfish.spellbook.core.CRUDRepository;
import net.viperfish.spellbook.core.Callback;
import net.viperfish.spellbook.core.Item;
import net.viperfish.spellbook.core.Spell;
import net.viperfish.spellbook.core.SpellService;

public class TaskSpellService extends TaskCRUDService<Long, Spell> implements SpellService {

	private CRUDRepository<Long, Item> itemRepo;

	public TaskSpellService(
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

	@Override
	public void execGetCastable(Callback<Iterable<Spell>> callback) {
		GetCastableSpellsTask getter = new GetCastableSpellsTask(repo, itemRepo);
		scheduler.submit(getter, callback);
	}

	@Override
	public void execSpell(Long id, Callback<Boolean> callable) {
		CastSpellTask casting = new CastSpellTask(repo, itemRepo, id);
		scheduler.submit(casting, callable);
	}
}
