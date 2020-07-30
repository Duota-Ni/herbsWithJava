package model;

import java.io.Serializable;

public class Herb implements Serializable {
	private int id;
	private String title;
	private float price;
	private int stock;
	private String province;
	// 构造方法
	public Herb() {}

	public Herb(int id, String title, float price, int stock, String province) {
		super();
		this.id=id;
		this.title=title;
		this.price=price;
		this.stock=stock;
		this.province=province;
	}

	public int getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public float getPrice() {
		return price;
	}

	public int getStock() {
		return stock;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}
	

}
