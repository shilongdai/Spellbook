package net.viperfish.spellbook.core;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;


@DatabaseTable(tableName = "Spell")
public class Spell {

	@DatabaseField(generatedId = true)
	private Long id;
	@DatabaseField(width = 100)
	private String name;
	@DatabaseField(width = 50)
	private String duration;
	@DatabaseField(width = 50)
	private String castingTime;
	@DatabaseField(width = 1000)
	private String description;
	@ForeignCollectionField
	private Collection<ItemRequirement> requirements;

	public Spell(String name, String duration, String castingTime, String description) {
		this();
		this.name = name;
		this.duration = duration;
		this.castingTime = castingTime;
		this.description = description;
	}

	public Spell() {
		requirements = new ArrayList<>();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getCastingTime() {
		return castingTime;
	}

	public void setCastingTime(String castingTime) {
		this.castingTime = castingTime;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Collection<ItemRequirement> getRequirements() {
		return requirements;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Spell spell = (Spell) o;
		return Objects.equals(id, spell.id) &&
				name.equals(spell.name) &&
				Objects.equals(duration, spell.duration) &&
				Objects.equals(castingTime, spell.castingTime) &&
				Objects.equals(description, spell.description) &&
				requirements.equals(spell.requirements);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name, duration, castingTime, description, requirements);
	}
}
