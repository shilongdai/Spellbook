package net.viperfish.spellbook.ui;

import net.viperfish.spellbook.core.Callback;

public class CastSpellCallback implements Callback<Boolean> {

	@Override
	public void call(Boolean result, Exception error) {
		if (error != null) {
			System.out.println("Error casting spells:" + error.getMessage());
			error.printStackTrace();
		}
		if (result) {
			System.out.println("Spell casted properly");
		} else {
			System.out.println("Insufficient requirement satisfaction or spell not found");
		}
	}
}
