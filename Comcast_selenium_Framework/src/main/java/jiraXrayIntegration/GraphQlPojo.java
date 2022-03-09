package jiraXrayIntegration;

public class GraphQlPojo{
	private String query;
	private String variables;
	public GraphQlPojo(String query, String variables) {
		super();
		this.query = query;
		this.variables = variables;
	}
	public GraphQlPojo() {}
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	public String getVariables() {
		return variables;
	}
	public void setVariables(String variables) {
		this.variables = variables;
	}
	
	
}