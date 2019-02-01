package net.viperfish.spellbook.task;

import java.util.concurrent.Callable;
import net.viperfish.spellbook.core.CRUDRepository;

public class GetManyTask<ID, T> implements Callable<Iterable<T>> {

	private CRUDRepository<ID, T> repo;
	private Iterable<ID> ids;

	public GetManyTask(CRUDRepository<ID, T> repo, Iterable<ID> ids) {
		this.repo = repo;
		this.ids = ids;
	}

	@Override
	public Iterable<T> call() throws Exception {
		return repo.getAll(ids);
	}
}
