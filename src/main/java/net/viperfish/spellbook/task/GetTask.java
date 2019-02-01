package net.viperfish.spellbook.task;

import java.util.concurrent.Callable;
import net.viperfish.spellbook.core.CRUDRepository;

public class GetTask<ID, T> implements Callable<T> {

	private ID id;
	private CRUDRepository<ID, T> repo;

	public GetTask(ID id, CRUDRepository<ID, T> repo) {
		this.id = id;
		this.repo = repo;
	}

	@Override
	public T call() throws Exception {
		return repo.get(id);
	}
}
