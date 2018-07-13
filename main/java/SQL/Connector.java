package SQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import Common.Handler;
import Common.ParticipantEntry;
import Common.ResultEntry;
import Pages.CompletedResult;
import Pages.Results;


public class Connector {
	
	
	private String dbUrl = "jdbc:mysql://localhost:3306/yuaes"
			+                   "?verifyServerCertificate=false" 
			+                    "&useSSL=false" 
			+                    "&requireSSL=false"
			+                    "&useLegacyDatetimeCode=false" 
			+                    "&amp" 
			+                    "&serverTimezone=UTC";
	private String userName = "root";
	private String password = "Ujdbylf1998";
	private final Connection connection;
	public StringBuilder queryBuilder;
	 
	
	public Connector() {
		try {
			this.connection = DriverManager.getConnection(this.dbUrl, this.userName, this.password);
		}catch(SQLException e) {
			throw new IllegalStateException(e);
		}
	}
	
	
	/*
	public void insertDataIntoResults(String id, String company_name, String company_code, String itemOfTender, int amount, 
			                          String date, int expectedValue, String currency) {
		queryBuilder = new StringBuilder();
		try {
			Statement statement =  this.connection.createStatement();
			try {
				statement.executeUpdate("insert into results values('"+id+"','"+company_name+"','"+company_code+"','"+itemOfTender+"',"
						+amount+", '"+date+"',"+expectedValue+", '"+currency+"')");
			}catch(SQLIntegrityConstraintViolationException v){
				System.err.println("The entry with id "+id+" already exists");
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}*/
	
	
	public void insertDataIntoResults(List<ResultEntry> res, WebDriver driver, WebDriverWait wait, Handler handl
			//String id, String company_name, String company_code, String itemOfTender, int amount, 
            //String date, int expectedValue, String currency
			){
            queryBuilder = new StringBuilder();
            queryBuilder.append("insert into results values");
            for(int x=0; x<res.size(); x++) {
            if(x!=(res.size()-1)) {
            queryBuilder.append("('"+res.get(x).getId()+"','"+res.get(x).getVinner_company_name(handl)+"','"+res.get(x).getVinner_company_code()+"','"
            +res.get(x).getItem_of_tender(handl)+"',"+res.get(x).getAmount()+", '"+res.get(x).getDate()+"',"+res.get(x).getExpected_Value()+", '"+res.get(x).getCurrency()+"'),");
            }else{            
            queryBuilder.append("('"+res.get(x).getId()+"','"+res.get(x).getVinner_company_name(handl)+"','"+res.get(x).getVinner_company_code()+"','"
            +res.get(x).getItem_of_tender(handl)+"',"+res.get(x).getAmount()+", '"+res.get(x).getDate()+"',"+res.get(x).getExpected_Value()+", '"+res.get(x).getCurrency()+"')");
            }
            }                   
            try {
                Statement statement =  this.connection.createStatement();
            try {
            statement.executeUpdate(queryBuilder.toString());	
            }catch(SQLIntegrityConstraintViolationException v){
				v.printStackTrace();
			}          
            }catch(SQLException e) {
             e.printStackTrace();
            }            
	}
	
	public void insertDataIntoParticipants(List<ParticipantEntry> loosers, Handler handler) {
		queryBuilder = new StringBuilder();
		queryBuilder.append("insert into participants values");
		for(int x=0; x<loosers.size(); x++) {
			if(x!=(loosers.size()-1)) {
				queryBuilder.append("('"+loosers.get(x).getId()+"',"+loosers.get(x).getPrice()+", '"+loosers.get(x).getCompanyName(handler)+"'),");
			}else {
				queryBuilder.append("('"+loosers.get(x).getId()+"',"+loosers.get(x).getPrice()+", '"+loosers.get(x).getCompanyName(handler)+"')");
			}
		}
		try {
			Statement statement = this.connection.createStatement();
			try {
				statement.executeUpdate(queryBuilder.toString());
			}catch(SQLIntegrityConstraintViolationException v) {
				v.printStackTrace();
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public String getDbUrl() {
		return dbUrl;
	}

	public void setDbUrl(String dbUrl) {
		this.dbUrl = dbUrl;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	

}
