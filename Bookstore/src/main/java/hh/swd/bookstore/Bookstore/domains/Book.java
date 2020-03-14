package hh.swd.bookstore.Bookstore.domains;

// import javax.annotation.Generated;
import javax.persistence.Entity;  
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;


@Entity
public class Book {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	@NotNull(message = "Minimum length is between 2 and 30 characters.")
	@Size(min=2, max=30, message="Please input a correct name.")
	private String title;
	@Size(min=2, max=30, message="Please input a logical author name.")
	private String author;
	@NotNull(message = "The year must have a value between 1800 and 2024.")
	@Min(value=1800, message="The year must be correct - minimum value is {value}.")
	@Max(value=2024, message="Year cannot be above 2024.")
	private int year;
	@Size(min=2, max=50, message="Please input a good ISBN.")
	private String isbn;
	@NotNull(message = "Please input a logical value - value has to be greater than 3.00.")
	@DecimalMin(value="3", message="Please input a logical value. Must be greater than {value}.")
	private double price;
	
	@ManyToOne
	@JsonManagedReference // Saadaan näin kunnossa myös REST-rajapinta.
	@JoinColumn(name = "categoryId")
	private Category category;
	
	
	public Book(String title, String author, int year, String isbn, double price, Category category) {
		super();
		this.title = title;
		this.author = author;
		this.year = year;
		this.isbn = isbn;
		this.price = price;
		this.category = category;
	}
	
	public Book() {
		super();
		this.id = null;
		this.title = null;
		this.author = null;
		this.year = 0;
		this.isbn = null;
		this.price = 0;
		this.category= null; 
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	@Override
	public String toString() {
		if (this.category != null)
			return "Book [id=" + id + ", name=" + title + ", author=" + author + ", isbn=" + isbn + ", year=" + year + ", price=" + price 
					+ ", category=" + this.getCategory() + "]";
			else
			 return "Book [id=" + id + ", name=" + title + ", author=" + author + ", isbn=" + isbn + ", year=" + year + ", price=" + price
						 + "]";
		}
	
	
}
