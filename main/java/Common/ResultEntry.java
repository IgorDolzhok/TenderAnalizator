package Common;

public class ResultEntry {
	
	private String id;
	private String vinner_company_name;
	private String vinner_company_code;
	private String item_of_tender;
	private int amount; 
	private String date;
	private int expected_Value;
	private String currency;
	
	public ResultEntry(String id, String vinner_company_name, String vinner_company_code, String item_of_tender,
			int amount, String date, int expected_Value, String currency) {
		super();
		this.id = id;
		this.vinner_company_name = vinner_company_name;
		this.vinner_company_code = vinner_company_code;
		this.item_of_tender = item_of_tender;
		this.amount = amount;
		this.date = date;
		this.expected_Value = expected_Value;
		this.currency = currency;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getVinner_company_name(Handler handl) {		 
		return handl.removeSpecSymbols(vinner_company_name);
	}

	public void setVinner_company_name(String vinner_company_name) {
		this.vinner_company_name = vinner_company_name;
	}

	public String getVinner_company_code() {
		return vinner_company_code;
	}

	public void setVinner_company_code(String vinner_company_code) {
		this.vinner_company_code = vinner_company_code;
	}

	public String getItem_of_tender(Handler handl) {		 
		return handl.removeSpecSymbols(item_of_tender);
	}

	public void setItem_of_tender(String item_of_tender) {
		this.item_of_tender = item_of_tender;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getExpected_Value() {
		return expected_Value;
	}

	public void setExpected_Value(int expected_Value) {
		this.expected_Value = expected_Value;
	}

	@Override
	public String toString() {
		return "ResultEntry [id=" + id + ", vinner_company_name=" + vinner_company_name + ", vinner_company_code="
				+ vinner_company_code + ", item_of_tender=" + item_of_tender + ", amount=" + amount + ", date=" + date
				+ ", expected_Value=" + expected_Value + ", currency=" + currency + "]";
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}
	
	
	
	

	 
   	
	
}
