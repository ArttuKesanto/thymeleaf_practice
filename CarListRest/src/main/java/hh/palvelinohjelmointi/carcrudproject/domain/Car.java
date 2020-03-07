package hh.palvelinohjelmointi.carcrudproject.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Car {
	// attribuutit
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id; // new attribute id
	private String model;
	private int year;
	
	//konstruktorit
	public Car() {
		super();
		this.id = null;
		this.model = null;
		this.year = 0;
	}
	public Car(String model, int year) {
		super();
		this.model = model;
		this.year = year;
	}
	
	public Car(Long id, String model, int year) {
		super();
		this.id = id;
		this.model = model;
		this.year = year;
	}
	//setterit
	public void setId(Long id) {
		this.id = id;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public void setYear(int year) {
		this.year = year;
	}
	// getterit
	public String getModel() {
		return model;
	}
	public int getYear() {
		return year;
	}
	
	public Long getId() {
		return id;
	}
	// toString	
	@Override
	public String toString() {
		return "Car [id=" + id + ", model=" + model + ", year=" + year + "]";
	}
	

}
