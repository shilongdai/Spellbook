package net.viperfish.spellbook.db;

import com.j256.ormlite.jdbc.JdbcConnectionSource;
import net.viperfish.spellbook.core.CRUDRepository;
import net.viperfish.spellbook.core.ItemRequirement;
import net.viperfish.spellbook.core.Spell;
import net.viperfish.spellbook.core.SpellDAO;

import java.io.IOException;

public class SpellH2Database extends ORMLiteRepository<Long, Spell> implements SpellDAO {

	private CRUDRepository<Long, ItemRequirement> requirementCRUDRepository;

	public SpellH2Database(JdbcConnectionSource connectionSource) throws IOException {
		super(connectionSource, Spell.class);
		requirementCRUDRepository = new ORMLiteRepository<>(connectionSource, ItemRequirement.class);
	}

	@Override
	public Spell get(Long id) throws IOException {
		Spell sp = super.get(id);
		if (sp == null) {
			return null;
		}
		for (ItemRequirement itemReq : requirementCRUDRepository.findBy("itemId", sp.getId())) {
			sp.getRequirements().add(itemReq);
		}
		return sp;
	}

	@Override
	public void persist(Spell data) throws IOException {
		super.persist(data);
		for (ItemRequirement itemRequirement : data.getRequirements()) {
			requirementCRUDRepository.persist(itemRequirement);
		}
	}

	@Override
	public void delete(Long id) throws IOException {
		super.delete(id);
		requirementCRUDRepository.deleteBy("itemId", id);
	}

	@Override
	public Iterable<Spell> getAll() throws IOException {
		Iterable<Spell> result = super.getAll();
		fillItemRequirements(result);
		return result;
	}

	@Override
	public <Q> Iterable<Spell> findBy(String column, Q key) throws IOException {
		Iterable<Spell> result = super.findBy(column, key);
		fillItemRequirements(result);
		return result;
	}

	@Override
	public <Q> void deleteBy(String column, Q key) throws IOException {
		for (Spell sp : super.findBy(column, key)) {
			requirementCRUDRepository.deleteBy("itemId", sp.getId());
			super.delete(sp.getId());
		}
	}

	private void fillItemRequirements(Iterable<Spell> spells) throws IOException {
		for (Spell sp : spells) {
			for (ItemRequirement req : requirementCRUDRepository.findBy("itemId", sp.getId())) {
				sp.getRequirements().add(req);
			}
		}
	}
}
