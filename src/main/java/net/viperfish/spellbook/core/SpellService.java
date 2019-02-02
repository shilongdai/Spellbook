package net.viperfish.spellbook.core;

public interface SpellService extends CRUDService<Long, Spell> {

	void execGetCastable(Callback<Iterable<Spell>> callback);

	void execSpell(Long id, Callback<Boolean> callable);

}
