package ro.ase.csie.classes;

import java.io.*;

public class Lander extends Spacecraft implements Comparable<Lander>{

	private String landingSite;
	private boolean hasLanded; // shows if the lander has landed; by default, this should be FALSE
	int[] temperatureReadings; // stores the temperatures registered until now

	public Lander(){
		this.landingSite="Necunoscut";
		this.hasLanded = false;
		this.temperatureReadings = null;
	}

	public Lander(String landingSite, boolean hasLanded, int[] temperatureReadings) {
		this.landingSite = landingSite;
		this.hasLanded = hasLanded;
		this.temperatureReadings = temperatureReadings;
	}

	public Lander(String name, float weight, String landingSite, boolean hasLanded, int[] temperatureReadings) {
		super(name, weight);
		this.landingSite = landingSite;
		this.hasLanded = hasLanded;
		this.temperatureReadings = temperatureReadings;
	}

	public String getLandingSite() {
		return landingSite;
	}

	public void setLandingSite(String landingSite) {
		this.landingSite = landingSite;
	}

	public boolean isHasLanded() {
		return hasLanded;
	}

	public void setHasLanded(boolean hasLanded) {
		this.hasLanded = hasLanded;
	}

	public int[] getTemperatureReadings() {
		return temperatureReadings;
	}

	public void setTemperatureReadings(int[] temperatureReadings) {
		this.temperatureReadings = temperatureReadings;
	}

	@Override
	public String toString() {
		final StringBuffer sb = new StringBuffer("Lander{");
		sb.append("name='").append(this.getName()).append('\'');;
		sb.append(", weight='").append(this.getWeight());
		sb.append(", landingSite='").append(landingSite).append('\'');
		sb.append(", hasLanded=").append(hasLanded);
		sb.append(", temperatureReadings=");
		if (temperatureReadings == null) sb.append("null");
		else {
			sb.append('[');
			for (int i = 0; i < temperatureReadings.length; ++i)
				sb.append(i == 0 ? "" : ", ").append(temperatureReadings[i]);
			sb.append(']');
		}
		sb.append('}');
		return sb.toString();
	}

	@Override
	public void land() {
		this.hasLanded = true;
	}

	@Override
	public double getAverageTemperature() {
		if (temperatureReadings == null || temperatureReadings.length == 0) {
			return 0f;
		}
		double sum = 0;
		for (int temp : temperatureReadings) {
			sum += temp;
		}
		return sum / temperatureReadings.length;
	}

	public void serialize() throws IOException {
		FileOutputStream fileOutputStream = new FileOutputStream("landers.data");
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
		objectOutputStream.writeObject(this);
		objectOutputStream.close();
	}

	public void deserialize() throws IOException, ClassNotFoundException {
		FileInputStream fileInputStream = new FileInputStream("landers.data");
		ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
		Lander l = (Lander) objectInputStream.readObject();
		this.setName(l.getName());
		this.setWeight(l.getWeight());
		this.landingSite = l.landingSite;
		this.hasLanded = l.hasLanded;
		this.temperatureReadings=l.temperatureReadings;
//		System.out.println("DES: "+this.toString());
		objectInputStream.close();
	}


	@Override
	public int compareTo(Lander o) {
		return Float.compare(this.getWeight(), o.getWeight());
	}
}
