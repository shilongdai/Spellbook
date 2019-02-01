package net.viperfish.spellbook.ui;

import net.viperfish.spellbook.core.Callback;
import net.viperfish.spellbook.core.ItemRequirement;
import net.viperfish.spellbook.core.Spell;

public class ListSpellCallback implements Callback<Iterable<Spell>> {

	@Override
	public void call(Iterable<Spell> result, Exception error) {
		if (error != null) {
			System.out.println("Failed to retrieve spells:" + error.getMessage());
			error.printStackTrace();
			return;
		}
		for (Spell i : result) {
			System.out.println("--------------------------------");
			System.out.print("ID:");
			System.out.println(i.getId());
			System.out.print("Name:");
			System.out.println(i.getName());
			System.out.print("Duration:");
			System.out.println(i.getDuration());
			System.out.print("Casting Time:");
			System.out.println(i.getCastingTime());
			System.out.println("Requirements:");
			for (ItemRequirement r : i.getRequirements()) {
				System.out.println("\t-" + r.getItem().getName() + ":" + r.getAmount());
			}
			System.out.println("Description:");
			System.out.println(i.getDescription());
		}
	}
}
