package net.viperfish.spellbook.core;

import javax.lang.model.type.NullType;

public interface CRUDService<ID, T> {

	void init() throws Exception;

	void execGet(ID id, Callback<? super T> callback);

	void execGetAll(Iterable<ID> ids, Callback<Iterable<T>> callback);

	void execGetAll(Callback<Iterable<T>> callback);

	void execDelete(ID id, Callback<NullType> callback);

	void execPersist(T data, Callback<? super T> callback);

	void execPersist(Iterable<T> data, Callback<Iterable<T>> callback);

	void execDelete(Iterable<ID> ids, Callback<NullType> callback);

	void destroy();
}
