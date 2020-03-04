package Hardware.Store.Example.Hardware.Store.Ltd.domains;

//import javax.annotation.Generated;
import javax.persistence.Entity;    
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity
public class ElectronicProduct {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String name;
	private int year;
	private String rating;
	private String availability;
	private String description;
	private double price;
	@ManyToOne
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


	
