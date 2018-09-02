package Common;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Handler {

	public Handler() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getCurrency(String price) {
		return price.substring(price.length()-3, price.length());
	}
	
	public int getPrice(String price) {
		String value = price.substring(0, price.indexOf(",")).replaceAll("\\s", "");
		return Integer.parseInt(value);
	}
	
	
	
    public String getID(String id) {
	    return id.substring(4, id.length()); 	
	}
    
    public String removeSpecSymbols(String str) {
    	//System.out.println("Input: "+str); 
    	str = str.replace("'", " ");
    	//str = str.replaceAll("/(", " ");
    	//str = str.replaceAll("/)", " ");
    	//System.out.println("Output: "+str);
    	return str;
    }
    
    public String getVinnerCodeFromOuterHTML(String html) {
    	 html= html.replaceAll("\\s", "");
		 int i = html.indexOf("#");
		 int x = html.indexOf("</td>");
		 String result = html.substring(i+1, x);
		 return result;
    }
    
    public String getDateFromUrl(String url) {
    	String date = url.substring(url.indexOf("2"), url.indexOf("2")+10);
    	return date;
    }
    
    
    
    
	
	
	
	
	
}
