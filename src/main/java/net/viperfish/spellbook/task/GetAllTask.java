package net.viperfish.spellbook.task;

import java.util.concurrent.Callable;
import net.viperfish.spellbook.core.CRUDRepository;

public class GetAllTask<T> implements Callable<Iterable<T>> {

	private CRUDRepository<?, T> repo;

	public GetAllTask(CRUDRepository<?, T> repo) {
		this.repo = repo;
	}

	@Override
	public Iterable<T> call() throws Exception {
		return repo.getAll();
	}
}
