package Hardware.Store.Example.Hardware.Store.Ltd.domains;

//import javax.annotation.Generated;
import javax.persistence.Entity;      
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
public class ElectronicProduct {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	@NotNull(message="Please input a logical name - size must be between 2-30 characters.")
	@Size(min=2, max=30, message="Please input a logical name - size must be between 2-30 characters.")
	private String name;
	@NotNull
	@Min(value=2016, message="Please input a correct year of publishing - value cannot be lower than 2019.")
	@Max(value=2023, message="Please input a correct year of publishing - value cannot be greater than 2023.")
	private int year;
	@NotNull(message="Please input a correct rating - must be in the form of e.g. \"4/5\".")
	@Size(min=3, max=3, message="Please input a correct rating - must be in the form of e.g. \"4/5\".")
	private String rating;
	@NotNull(message="Type either \"Available\" or \"Not available\".")
	@Size(min=9, max=13, message="Type either \"Available\" or \"Not available\".")
	private String availability;
	@NotNull(message="Please input a description with the length of 5-80 for customer awareness.")
	@Size(min=5, max=80, message="Please input a description with the length of 5-80 for customer awareness.")
	private String description;
	@NotNull(message="Please type in a price with at least a value of 3.00.")
	@DecimalMin(value="4", message="Please input a logical value. Must be greater than {value}.")
	private double price;
	@ManyToOne
	@JsonIgnore // Very important to get rid of endless looping!
	@JoinColumn(name = "categoryId")
	private EProductCategory category;
	
	public ElectronicProduct(String name, int year, String rating, String availability, String description, double price,
			EProductCategory category) {
		super();
		//this.id = id;
		this.name = name;
		this.year = year;
		this.rating = rating;
		this.availability = availability;
		this.description = description;
		this.price = price;
		this.category = category;
	}
	
	public ElectronicProduct() {
		super();
		this.id = null;
		this.name = null;
		this.year = 0;
		this.rating = null;
		this.availability = null;
		this.description = null;
		this.price = 0;
		this.category = null;
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

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public String getAvailability() {
		return availability;
	}

	public void setAvailability(String availability) {
		this.availability = availability;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public double getPrice() {
		return price;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}

	public EProductCategory getCategory() {
		return category;
	}

	public void setCategory(EProductCategory category) {
		this.category = category;
	}

	@Override
	public String toString() {
		if (this.category != null) 
		return "ElectronicProduct [id=" + id + ", name=" + name + ", year=" + year + ", rating=" + rating
				+ ", availability=" + availability + ", description=" + description + ", price=" + price + ", category=" + this.getCategory() + "]";
		
		else
			return "ElectronicProduct [id=" + id + ", name=" + name + ", year=" + year + ", rating=" + rating
					+ ", availability=" + availability + ", description=" + description + ", price=" + price + "]";
	
	}

}


	
