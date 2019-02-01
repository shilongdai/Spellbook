package net.viperfish.spellbook.task;

import net.viperfish.spellbook.core.Callback;
import net.viperfish.spellbook.core.TaskScheduler;

import java.util.concurrent.Callable;

public class SequentialTaskScheduler implements TaskScheduler {

	@Override
	public void init() {

	}

	@Override
	public void submit(Callable<?> task, Callback callback) {
		Exception e = null;
		Object result = null;
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
