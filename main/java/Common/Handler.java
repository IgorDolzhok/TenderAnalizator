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
		return Integer.parseInt(price.substring(18, price.indexOf(",")).replaceAll("\\s",""));
	}
	
	
	
    public String getID(String id) {
	    return id.substring(4, id.length()); 	
	}
    
    public String removeSpecSymbols(String str) {
    	System.out.println("Input: "+str); 
    	str = str.replace("'", " ");
    	//str = str.replaceAll("/(", " ");
    	//str = str.replaceAll("/)", " ");
    	System.out.println("Output: "+str);
    	return str;
    }
    
    
    
    
	
	
	
	
	
}
