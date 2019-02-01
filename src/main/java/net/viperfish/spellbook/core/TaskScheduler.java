package net.viperfish.spellbook.core;

import java.util.concurrent.Callable;

public interface TaskScheduler {

	void init();

	void submit(Callable<?> task, Callback callback);

	void destroy();

}
