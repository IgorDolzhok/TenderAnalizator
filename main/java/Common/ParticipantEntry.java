package Common;

/**
 * created for handling results from page and passing them into sql-query for entering in database
 * @author WORK-06
 *
 */
public class ParticipantEntry {

	public ParticipantEntry(String companyName, int price) {
		super();
		this.companyName = companyName;
		this.price = price;
	}

	private String id;
	private String companyName;
	private int price;
	
	public ParticipantEntry(String id, String companyName, int price) {
		super();
		this.id = id;
		this.companyName = companyName;
		this.price = price;
	}

	public ParticipantEntry() {
		 
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCompanyName(Handler handler) {
		return handler.removeSpecSymbols(companyName);
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "ParticipantEntry [id=" + id + ", companyName=" + companyName + ", price=" + price + "]";
	}
	
	
	
	
}
