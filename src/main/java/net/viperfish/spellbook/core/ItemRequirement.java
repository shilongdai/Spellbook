package net.viperfish.spellbook.core;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Objects;

@DatabaseTable(tableName = "ItemRequirement")
public class ItemRequirement {

	@DatabaseField(id = true)
	private Long id;
	@DatabaseField
	private Long itemId;
	@DatabaseField
	private double amount;

	public ItemRequirement(Long itemId, double amount) {
		this.itemId = itemId;
		this.amount = amount;
	}

	public ItemRequirement() {
		this.itemId = 0L;
		this.amount = 0.00;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		ItemRequirement that = (ItemRequirement) o;
		return Double.compare(that.amount, amount) == 0 &&
				Objects.equals(id, that.id) &&
				Objects.equals(itemId, that.itemId);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, itemId, amount);
	}
}
