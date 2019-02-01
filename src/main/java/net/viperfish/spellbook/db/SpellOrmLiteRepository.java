package net.viperfish.spellbook.db;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.table.TableUtils;
import java.io.IOException;
import java.sql.SQLException;
import net.viperfish.spellbook.core.ItemRequirement;
import net.viperfish.spellbook.core.Spell;

public class SpellOrmLiteRepository extends ORMLiteRepository<Long, Spell> {

	private Dao<ItemRequirement, Long> itemReqDao;

	public SpellOrmLiteRepository(JdbcConnectionSource connectionSource,
		Class<Spell> type) {
		super(connectionSource, type);
	}

	@Override
	public void init() throws IOException {
		super.init();
		try {
			TableUtils.createTableIfNotExists(conn, ItemRequirement.class);
			itemReqDao = DaoManager.createDao(conn, ItemRequirement.class);
		} catch (SQLException e) {
			throw new IOException(e);
		}
	}

	@Override
	public void delete(Long id) throws IOException {
		super.delete(id);
		try {
			DeleteBuilder<ItemRequirement, Long> builder = itemReqDao.deleteBuilder();
			builder.where().eq("spell", id);
			builder.delete();
		} catch (SQLException e) {
			throw new IOException(e);
		}
	}

	@Override
	public void persist(Spell data) throws IOException {
		super.persist(data);
		try {
			for (ItemRequirement itemRequirement : data.getRequirements()) {
				itemReqDao.createOrUpdate(itemRequirement);
			}
		} catch (SQLException e) {
			throw new IOException(e);
		}
	}
}
