package hh.swd.example20.carprojectexample.domain;

public class Car {
	
	//attr.
	private String merkki;
	private Integer valmistuvsuosi;
	
	public Car(String merkki, Integer valmistuvsuosi) {
		super();
		this.merkki = merkki;
		this.valmistuvsuosi = valmistuvsuosi;
	}
	
	public Car() {
		super();
		this.merkki = null;
		this.valmistuvsuosi = null;
	}

	public String getMerkki() {
		return merkki;
	}

	public void setMerkki(String merkki) {
		this.merkki = merkki;
	}

	public Integer getValmistuvsuosi() {
		return valmistuvsuosi;
	}

	public void setValmistuvsuosi(Integer valmistuvsuosi) {
		this.valmistuvsuosi = valmistuvsuosi;
	}

	@Override
	public String toString() {
		return "Car [merkki=" + merkki + ", valmistuvsuosi=" + valmistuvsuosi + "]";
	}
	
	


}
