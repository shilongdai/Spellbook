package net.viperfish.spellbook.task;

import java.util.concurrent.Callable;
import net.viperfish.spellbook.core.CRUDRepository;

public class SaveTask<T> implements Callable<T> {

	private CRUDRepository<?, T> repo;
	private T toSave;

	public SaveTask(CRUDRepository<?, T> repo, T toSave) {
		this.repo = repo;
		this.toSave = toSave;
	}

	@Override
	public T call() throws Exception {
		repo.persist(toSave);
		return toSave;
	}
}
