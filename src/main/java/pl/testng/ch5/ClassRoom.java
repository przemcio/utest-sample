package pl.testng.ch5;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

public class ClassRoom {

	private String name;
	private int maxPeople = 20;
	private Set<Equipment> equipments = new HashSet<Equipment>();
	private String startCleaningHour;
	private String endCleaningHour;

	public ClassRoom(String name) {
		this.name = name;
	}

	public ClassRoom(String name, int maxPeople) {
		this(name);
		this.maxPeople = maxPeople;
	}

	public String getName() {
		return name;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof ClassRoom) {
			if (StringUtils.equals(((ClassRoom) obj).name, name)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public int hashCode() {
		return name.hashCode();
	}

	@Override
	public String toString() {
		return "ClassRoom name: " + name;
	}

	public void addEquipment(Equipment equipment) {
		equipments.add(equipment);
	}

	public boolean hasEquipment(Equipment equipment) {
		return equipments.contains(equipment);
	}

	public int getMaxPeople() {
		return maxPeople;
	}

	public void setCleaningHour(String start, String end) {
		this.startCleaningHour = start;
		this.endCleaningHour = end;

	}

	public ClassRoomReservation getCleaningHours() {
		if (startCleaningHour != null && endCleaningHour != null) {
			return new ClassRoomReservation(getName(), null, startCleaningHour,
					endCleaningHour);
		} else {
			return null;
		}
	}

	boolean isFullfillRequirements(int atLeastMaxPeople, Equipment[] equipments) {
	
		boolean maxpeople = getMaxPeople() >= atLeastMaxPeople;
	
		for (Equipment equipment : equipments) {
			if (!hasEquipment(equipment)) {
	
				return false;
			}
		}
		return maxpeople;
	}

}
