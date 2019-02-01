package net.viperfish.spellbook.task;

import java.util.concurrent.Callable;
import javax.lang.model.type.NullType;
import net.viperfish.spellbook.core.CRUDRepository;

public class RemoveTask<ID> implements Callable<NullType> {

	private CRUDRepository<ID, ?> repo;
	private ID id;

	public RemoveTask(CRUDRepository<ID, ?> repo, ID id) {
		this.repo = repo;
		this.id = id;
	}

	@Override
	public NullType call() throws Exception {
		repo.delete(id);
		return null;
	}
}
