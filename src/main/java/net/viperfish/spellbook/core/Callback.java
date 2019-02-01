package net.viperfish.spellbook.core;

public interface Callback<T> {

	void call(T result, Exception error);

}
