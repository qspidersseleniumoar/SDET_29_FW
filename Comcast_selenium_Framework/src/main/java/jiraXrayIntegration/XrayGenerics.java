package jiraXrayIntegration;

import java.util.List;

import io.restassured.response.Response;

public class XrayGenerics {
	
	public void updateStatus(String testCaseName,String status) {
		GraphQLmethods xray=new GraphQLmethods();

		Response res = xray.getTestsAndTestExcecution();
		List<String> allTestcaseSummary=res.jsonPath().get("data.getExpandedTests.results.jira.summary");
		List<String> allTestcaseID=res.jsonPath().get("data.getExpandedTests.results.issueId");

		List<String> allTestExecutionSummary=res.jsonPath().get("data.getTestExecutions.results.jira.summary");
		List<String> allTestExecutionID=res.jsonPath().get("data.getTestExecutions.results.issueId");
		String testExecutionId=null;
		String testCaseId=null;
		

		for(int i=0;i<allTestcaseSummary.size();i++) {
			String testCaseSummary=allTestcaseSummary.get(i);
			if(testCaseSummary.equalsIgnoreCase(testCaseName)) {
				testCaseId = allTestcaseID.get(i);

			}
		}

		for(int i=0;i<allTestExecutionSummary.size();i++) {
			String testExecutionSummary=allTestExecutionSummary.get(i);
			if(testExecutionSummary.equalsIgnoreCase(testCaseName)) {
				testExecutionId = allTestExecutionID.get(i);

			}
		}


		Response res1 = xray.getTestRun(testCaseId,testExecutionId); 
		String testRunId = res1.jsonPath().get("data.getTestRun.id");
		if(testRunId==null)
		{
			System.out.println("Please add the testCase into TestExecution");
		}
		else {
			xray.updateTestRunStatus(testRunId,status);
		}


	}

}
