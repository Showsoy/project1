package vo;

public class ItemBean {
	private final String item_code;
	private final String item_name;
	private int price;
	private String origin;
	private String category;
	private String img_path;
	private int sale;
	private String content;
	private int readcount;
	private int ihide;

	public ItemBean(String item_code, String item_name, int price, String origin, String category, String img_path,
			int sale, String content, int readcount, int ihide) {
		super();
		this.item_code = item_code;
		this.item_name = item_name;
		this.price = price;
		this.origin = origin;
		this.category = category;
		this.img_path = img_path;
		this.sale = sale;
		this.content = content;
		this.readcount = readcount;
		this.ihide = ihide;
	}
	public String getItem_code() {
		return item_code;
	}
	public String getItem_name() {
		return item_name;
	}
	public int getPrice() {
		return price;
	}
	public String getOrigin() {
		return origin;
	}
	public String getCategory() {
		return category;
	}
	public String getImg_path() {
		return img_path;
	}
	public int getSale() {
		return sale;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getContent() {
		return content;
	}
	public int getReadcount() {
		return readcount;
	}
	public int getIhide() {
		return ihide;
	}
	
}
