package Hardware.Store.Example.Hardware.Store.Ltd.domains;


import java.util.List;  

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
public class EProductCategory {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long categoryId;
	@NotNull(message="Please input a logical category name. Size between 3 and 25 characters.")
	@Size(min=3, max=25, message="Please input a logical category name. Size between 3 and 25 characters.")
	private String name;
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "category")
	private List<ElectronicProduct> electronicProducts;
	
	public EProductCategory () {
		super();
		this.name = "Type a category name!";
	}
	
	public EProductCategory(String name) {
		super();
		this.name = name;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<ElectronicProduct> getElectronicProducts() {
		return electronicProducts;
	}

	public void setElectronicProducts(List<ElectronicProduct> electronicProducts) {
		this.electronicProducts = electronicProducts;
	}

	@Override
	public String toString() {
		return "EProductCategory [categoryId=" + categoryId + ", type=" + name + "]";
	}
	
	
}
