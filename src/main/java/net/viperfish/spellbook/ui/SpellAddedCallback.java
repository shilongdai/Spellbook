package net.viperfish.spellbook.ui;

import net.viperfish.spellbook.core.Callback;
import net.viperfish.spellbook.core.Spell;

public class SpellAddedCallback implements Callback<Spell> {

	@Override
	public void call(Spell result, Exception error) {
		if (error != null) {
			System.out.println("Failed to add spell:" + error.getMessage());
			error.printStackTrace();
			return;
		}
		System.out.println("Added:" + result.getName());
		System.out.println("ID:" + result.getId());
	}
}
