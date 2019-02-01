package net.viperfish.spellbook.task;

import java.util.concurrent.Callable;
import javax.lang.model.type.NullType;
import net.viperfish.spellbook.core.CRUDRepository;

public class RemoveManyTask<ID> implements Callable<NullType> {

	private Iterable<ID> ids;
	private CRUDRepository<ID, ?> repo;

	public RemoveManyTask(Iterable<ID> ids, CRUDRepository<ID, ?> repo) {
		this.ids = ids;
		this.repo = repo;
	}

	@Override
	public NullType call() throws Exception {
		repo.delete(ids);
		return null;
	}
}
