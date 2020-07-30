package model;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

public class shoppingCart {
	HashMap<Integer,HerbItem> items = null;
	public shoppingCart() {
		items = new HashMap<Integer,HerbItem>();
	}
	//(1)add方法 添加商品
	public void add(HerbItem herbItem) {
		int herbid = herbItem.getHerb().getId();
		//购物车已经有就增加数量
		if(items.containsKey(herbid)) {
			HerbItem scitem = (HerbItem)items.get(herbid);
			scitem.setQuantity(scitem.getQuantity()+herbItem.getQuantity());
		}else {
			//购物车没有该商品就增加该商品
			items.put(herbid, herbItem);
		}
	}
	//(2)remove方法 删除商品
	public void remove(Integer herbid ){
		if(items.containsKey(herbid)) {
			HerbItem scitem = (HerbItem)items.get(herbid);
			scitem.setQuantity(scitem.getQuantity()-1);
			if(scitem.getQuantity()<=0) {
				items.remove(herbid);
			}
		}
	}
	//(3)getItems() 返回购物车GoodsItems集合
	public Collection<HerbItem> getItems(){
		return items.values();
	}
	//(4)getTotal方法 计算总价
	public double getTotal() {
		double amount=0.0;
		//迭代器遍历 返回集合中的元素赋值给i
		for(Iterator<HerbItem> i = getItems().iterator();i.hasNext();) {
			//item获取集合下一项   next()
			HerbItem item = (HerbItem) i.next();
			Herb herb = (Herb)item.getHerb();
			amount += item.getQuantity()*herb.getPrice();
			}
		return roundOff(amount);
	}
	//(5)roundOff()四舍五入
	private double roundOff(double x) {
		long val = Math.round(x*100);
		return val/100.0;
	}
	//(6)clear()清空购物车
	public void clear() {
		items.clear();
	}
	


}
