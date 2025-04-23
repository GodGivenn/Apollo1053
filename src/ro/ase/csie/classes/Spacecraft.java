package ro.ase.csie.classes;

import java.io.Serializable;

public abstract class Spacecraft implements Serializable {

	private String name;
	private float weight; // measured in kg

	public Spacecraft(){
		this.name = "Necunoscut";
		this.weight = 0;
	}

	public Spacecraft(String name, float weight){
		this.name = name;
		this.weight = weight;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getWeight() {
		return weight;
	}

	public void setWeight(float weight) {
		this.weight = weight;
	}

	public abstract void land();

	public abstract double getAverageTemperature();
}
