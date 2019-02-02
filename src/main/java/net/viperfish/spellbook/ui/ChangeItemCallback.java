package net.viperfish.spellbook.ui;

import net.viperfish.spellbook.core.Callback;

public class ChangeItemCallback implements Callback<Double> {

	@Override
	public void call(Double result, Exception error) {
		if (error != null) {
			System.out.println("Failed to change item amount:" + error.getMessage());
			error.printStackTrace();
			return;
		}
		System.out.println("Item amount changed to:" + result);
	}
}
