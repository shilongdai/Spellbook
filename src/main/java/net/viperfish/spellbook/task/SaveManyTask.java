package net.viperfish.spellbook.task;

import java.util.concurrent.Callable;
import net.viperfish.spellbook.core.CRUDRepository;

public class SaveManyTask<T> implements Callable<Iterable<T>> {

	private CRUDRepository<?, T> repo;
	private Iterable<T> data;

	public SaveManyTask(CRUDRepository<?, T> repo, Iterable<T> data) {
		this.repo = repo;
		this.data = data;
	}

	@Override
	public Iterable<T> call() throws Exception {
		repo.persist(data);
		return data;
	}
}
