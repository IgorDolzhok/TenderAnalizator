package SQL;

public class GetQueryMaker extends QueryMaker {

	public GetQueryMaker(StringBuilder queryBuilder) {
		super(queryBuilder);		 
	}
	
	public String getAllResultsOfCompany(String companyName) {
		queryBuilder= new StringBuilder();
		String handledName = companyName.substring(4, companyName.length()-2);
		queryBuilder.append("select * from resultsoptimized where vinner_company_name like '%"+handledName+"%'");
		System.out.println(queryBuilder.toString());
		return queryBuilder.toString();
	}
	
	public String getParticipants(String companyName) {
		queryBuilder = new StringBuilder();
		String handledName = companyName.substring(4, companyName.length()-2);
		queryBuilder.append("select * from participants where company_name like '%"+handledName+"%' "
				+ "group by tender_id");
		System.out.println(queryBuilder.toString());
		return queryBuilder.toString();
	}
	
	

}
