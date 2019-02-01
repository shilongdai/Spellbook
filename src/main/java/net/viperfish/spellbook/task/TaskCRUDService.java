package net.viperfish.spellbook.task;

import javax.lang.model.type.NullType;
import net.viperfish.spellbook.core.CRUDRepository;
import net.viperfish.spellbook.core.CRUDService;
import net.viperfish.spellbook.core.Callback;

class TaskCRUDService<ID, T> implements CRUDService<ID, T> {

	protected TaskScheduler scheduler;
	protected CRUDRepository<ID, T> repo;

	public TaskCRUDService(CRUDRepository<ID, T> repo) {
		this.scheduler = new SequentialTaskScheduler();
		this.repo = repo;
	}

	@Override
	public void init() {
		scheduler.init();
	}

	@Override
	public void execGet(ID id, Callback<? super T> callback) {
		GetTask<ID, T> getter = new GetTask<>(id, repo);
		scheduler.submit(getter, callback);
	}

	@Override
	public void execGetAll(Iterable<ID> ids, Callback<Iterable<T>> callback) {
		GetManyTask<ID, T> getter = new GetManyTask<>(repo, ids);
		scheduler.submit(getter, callback);
	}

	@Override
	public void execGetAll(Callback<Iterable<T>> callback) {
		GetAllTask<T> getter = new GetAllTask<>(repo);
		scheduler.submit(getter, callback);
	}

	@Override
	public void execDelete(ID id, Callback<NullType> callback) {
		RemoveTask<ID> deleter = new RemoveTask<>(repo, id);
		scheduler.submit(deleter, callback);
	}

	@Override
	public void execPersist(T data, Callback<? super T> callback) {
		SaveTask<T> saver = new SaveTask<>(repo, data);
		scheduler.submit(saver, callback);
	}

	@Override
	public void execPersist(Iterable<T> data, Callback<Iterable<T>> callback) {
		SaveManyTask<T> saveManyTask = new SaveManyTask<>(repo, data);
		scheduler.submit(saveManyTask, callback);
	}

	@Override
	public void execDelete(Iterable<ID> ids, Callback<NullType> callback) {
		RemoveManyTask<ID> task = new RemoveManyTask<>(ids, repo);
		scheduler.submit(task, callback);
	}

	@Override
	public void destroy() {
		scheduler.destroy();
	}
}
