package net.viperfish.spellbook.core;

import java.io.IOException;

public interface CRUDRepository<ID, T> {

	T get(ID id) throws IOException;

	Iterable<T> getAll(Iterable<ID> ids) throws IOException;

	Iterable<T> getAll() throws IOException;

	void delete(ID id) throws IOException;

	void persist(T data) throws IOException;

	void persist(Iterable<T> data) throws IOException;

	void delete(Iterable<ID> ids) throws IOException;

	<Q> Iterable<T> findBy(String column, Q key) throws IOException;

	<Q> void deleteBy(String column, Q key) throws IOException;

}
