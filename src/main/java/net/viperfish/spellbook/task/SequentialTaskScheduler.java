package net.viperfish.spellbook.task;

import java.util.concurrent.Callable;
import net.viperfish.spellbook.core.Callback;

class SequentialTaskScheduler implements TaskScheduler {

	@Override
	public void init() {

	}

	@Override
	public <S> void submit(Callable<S> task, Callback<? super S> callback) {
		Exception e = null;
		S result = null;
		try {
			result = task.call();
		} catch (Exception exp) {
			e = exp;
		}
		callback.call(result, e);
	}

	@Override
	public void destroy() {

	}
}
