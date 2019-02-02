package net.viperfish.spellbook.core;

public interface ItemService extends CRUDService<Long, Item> {

	void execChangeAmount(Long id, Double amount, Callback<Double> callback);

}
