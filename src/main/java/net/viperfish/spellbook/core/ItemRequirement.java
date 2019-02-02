package net.viperfish.spellbook.core;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import java.util.Objects;

@DatabaseTable(tableName = "ItemRequirement")
public class ItemRequirement {

	@DatabaseField(generatedId = true)
	private Long id;
	@DatabaseField(foreign = true, foreignAutoRefresh = true, columnName = "spell")
	private Spell spell;
	@DatabaseField
	private double amount;
	@DatabaseField(foreign = true, foreignAutoRefresh = true, columnName = "item")
	private Item item;

	public ItemRequirement(double amount) {
		this.amount = amount;
	}

	public ItemRequirement() {
		this.amount = 0.00;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Spell getSpell() {
		return spell;
	}

	public void setSpell(Spell spell) {
		this.spell = spell;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		ItemRequirement that = (ItemRequirement) o;
		return Double.compare(that.amount, amount) == 0 &&
			Objects.equals(id, that.id) &&
			Objects.equals(item, that.item);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, amount, item);
	}
}
