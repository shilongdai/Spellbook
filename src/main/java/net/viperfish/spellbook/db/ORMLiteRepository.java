package net.viperfish.spellbook.db;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.table.TableUtils;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import net.viperfish.spellbook.core.CRUDRepository;

public class ORMLiteRepository<ID, T> implements CRUDRepository<ID, T> {

	protected JdbcConnectionSource conn;
	protected Dao<T, ID> dao;
	protected Class<T> type;

	public ORMLiteRepository(JdbcConnectionSource connectionSource, Class<T> type) {
		this.conn = connectionSource;
		this.type = type;
	}

	public void init() throws IOException {
		try {
			TableUtils.createTableIfNotExists(conn, type);
			this.dao = DaoManager.createDao(conn, type);
		} catch (SQLException e) {
			throw new IOException(e);
		}
	}

	@Override
	public T get(ID id) throws IOException {
		try {
			T result = dao.queryForId(id);
			return result;
		} catch (SQLException e) {
			throw new IOException(e);
		}
	}

	@Override
	public Iterable<T> getAll(Iterable<ID> ids) throws IOException {
		List<T> result = new ArrayList<>();
		for (ID id : ids) {
			result.add(this.get(id));
		}
		return result;
	}

	@Override
	public void delete(ID id) throws IOException {
		try {
			dao.deleteById(id);
		} catch (SQLException e) {
			throw new IOException(e);
		}
	}

	@Override
	public void persist(T data) throws IOException {
		try {
			dao.createOrUpdate(data);
		} catch (SQLException e) {
			throw new IOException(e);
		}
	}

	@Override
	public void persist(Iterable<T> data) throws IOException {
		for (T dt : data) {
			this.persist(dt);
		}
	}

	@Override
	public void delete(Iterable<ID> ids) throws IOException {
		for (ID id : ids) {
			this.delete(id);
		}
	}

	@Override
	public Iterable<T> getAll() throws IOException {
		try {
			return dao.queryForAll();
		} catch (SQLException e) {
			throw new IOException(e);
		}
	}

	@Override
	public <Q> Iterable<T> findBy(String column, Q key) throws IOException {
		QueryBuilder<T, ID> queryBuilder = dao.queryBuilder();
		try {
			return queryBuilder.where().eq(column, key).query();
		} catch (SQLException e) {
			throw new IOException(e);
		}
	}

	@Override
	public <Q> void deleteBy(String column, Q key) throws IOException {
		DeleteBuilder<T, ID> deleteBuilder = dao.deleteBuilder();
		try {
			deleteBuilder.where().eq(column, key);
			deleteBuilder.delete();
		} catch (SQLException e) {
			throw new IOException(e);
		}
	}
}
