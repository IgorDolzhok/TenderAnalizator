package SQL;

import java.sql.Statement;
import java.util.List;

import Common.Handler;
import Common.ParticipantEntry;
import Common.ResultEntry;

public class QueryMaker {

	public StringBuilder queryBuilder;

	public QueryMaker(StringBuilder queryBuilder) {
		super();
		this.queryBuilder = queryBuilder;
	}
	 
	public String insertDataIntoResults( List<ResultEntry> res, Handler handl) {
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
        return queryBuilder.toString();
	}
	
	public String insertDataIntoParticipants(List<ParticipantEntry> loosers, Handler handler) {
		queryBuilder = new StringBuilder();
		queryBuilder.append("insert into participants values ");
		for(int x=0; x<loosers.size(); x++) {
			if(x!=(loosers.size()-1)) {
				queryBuilder.append("('"+loosers.get(x).getId()+"',"+loosers.get(x).getPrice()+", '"+loosers.get(x).getCompanyName(handler)+"'),");
			}else {
				queryBuilder.append("('"+loosers.get(x).getId()+"',"+loosers.get(x).getPrice()+", '"+loosers.get(x).getCompanyName(handler)+"')");
			}
		}		
		return queryBuilder.toString();
	}
	
	
}
