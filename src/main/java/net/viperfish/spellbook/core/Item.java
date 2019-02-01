package net.viperfish.spellbook.core;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import java.util.Objects;

@DatabaseTable(tableName = "Item")
public class Item {

	@DatabaseField(generatedId = true)
	private Long id;
	@DatabaseField(width = 100)
	private String name;
	@DatabaseField
	private double amount;
	@DatabaseField(width = 1000)
	private String desc;

	public Item(String name, double amount, String desc) {
		this.name = name;
		this.amount = amount;
		this.desc = desc;
	}

	public Item() {
		this.name = null;
		this.amount = 0;
		this.desc = "";
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Item item = (Item) o;
		return id == item.id &&
				Double.compare(item.amount, amount) == 0 &&
				name.equals(item.name) &&
				Objects.equals(desc, item.desc);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name, amount, desc);
	}
}
