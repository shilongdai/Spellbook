package net.viperfish.spellbook.task;

import java.util.concurrent.Callable;
import net.viperfish.spellbook.core.Callback;

interface TaskScheduler {

	void init();

	<S> void submit(Callable<S> task, Callback<? super S> callback);

	void destroy();

}
