package vo;

import java.sql.Date;

public class ItemStockBean {
	private final String item_code;
	private String state;
	private Date idate;
	private int amount;
	private int stock;
	private final int inumber;
	
	public ItemStockBean(String item_code, String state, Date idate, int amount, int stock, int inumber) {
		super();
		this.item_code = item_code;
		this.state = state;
		this.idate = idate;
		this.amount = amount;
		this.stock = stock;
		this.inumber = inumber;
	}
	
	public ItemStockBean(String item_code, String state, int amount, int stock, int inumber) {
		super();
		this.item_code = item_code;
		this.state = state;
		this.amount = amount;
		this.stock = stock;
		this.inumber = inumber;
	}
	
	public String getItem_code() {
		return item_code;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public Date getIdate() {
		return idate;
	}
	public void setIdate(Date idate) {
		this.idate = idate;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public int getInumber() {
		return inumber;
	}
	
	
}
