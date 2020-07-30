package model;
import java.io.Serializable;
public class HerbItem implements Serializable{
	private Herb herb;
	private int quantity;	
	
	public HerbItem(Herb herb) {
		this.herb = herb;
		quantity = 1;
	}
	public HerbItem(Herb herb,int quantity) {
		this.herb = herb;
		this.quantity = quantity;
	}
	
	public Herb getHerb() {
		return herb;
	}
	public void setHerb(Herb herb) {
		this.herb = herb;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	

}
