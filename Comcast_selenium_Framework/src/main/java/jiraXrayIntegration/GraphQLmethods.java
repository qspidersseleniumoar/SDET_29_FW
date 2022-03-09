package jiraXrayIntegration;

import static io.restassured.RestAssured.given;

import java.util.List;

import io.restassured.http.ContentType;
import io.restassured.response.Response;




/**
 * 
 * @author Mohankumar GOWDA
 *
 */


public class GraphQLmethods {
	
	GraphQlPojo q=new GraphQlPojo();
	String token="Bearer "+GraphQLmethods.jwtgen();
	//String token="Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ0ZW5hbnQiOiJiOWFhMjAxYy1hMzNhLTNjZGUtYjA0MC1lZDM3NTVlNmJiNDUiLCJhY2NvdW50SWQiOiI2MWZhYzI0Yzg0NWQ2NzAwNzFmMzRkMzgiLCJpc1hlYSI6ZmFsc2UsImlhdCI6MTY0NjA2NjU5OCwiZXhwIjoxNjQ2MTUyOTk4LCJhdWQiOiI0N0E0MTc3MDdBNUU0QkYwOTU4MEUyM0JCRTcwMDdGNCIsImlzcyI6ImNvbS54cGFuZGl0LnBsdWdpbnMueHJheSIsInN1YiI6IjQ3QTQxNzcwN0E1RTRCRjA5NTgwRTIzQkJFNzAwN0Y0In0.NLvwojeNWBMgdr6TnEDTU17ht0WUL4B_XYwl1VlyuzM";


	/**
	 * JWT Token Generator
	 * @return
	 */
	public static String jwtgen() {
		String client_ID="C1750637FD4B4A72AAE32FEF2FB8D00F";
		String client_Secret="d89b4e19b72a01160bd0c035acfeb27c80c0a7ca16b899e2bbf8411871bcf6b4";

		String reqBody="{ \"client_id\": \""+client_ID+"\",\r\n"
				+ "\"client_secret\": \""+client_Secret+"\" }";
		Response res = given()
				.contentType("application/json")
				.body(reqBody)				
				.when()
				.post("https://xray.cloud.getxray.app/api/v2/authenticate");
		String jwtToken = res.body().asString().replaceAll("\"", "");
		return jwtToken;
	}


	/**
	 * Post
	 * @param q
	 * @return 
	 */
	public Response post(Object q) {
		Response res = given()
				.contentType(ContentType.JSON)
				.header("Authorization", token)
				.body(q)
				.when()
				.post("https://xray.cloud.getxray.app/api/v2/graphql");
		return res;
	}



	public void updateStatus(String testCaseName,String status) {


		Response res = getTestsAndTestExcecution();
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
				break;

			}
			else {
				System.out.println("Please create the Test Issue in Jira as ====>>> "+testCaseSummary);
			}
		}

		for(int i=0;i<allTestExecutionSummary.size();i++) {
			String testExecutionSummary=allTestExecutionSummary.get(i);
			if(testExecutionSummary.equalsIgnoreCase("TestExecutionSpint1")) {
				testExecutionId = allTestExecutionID.get(i);
				break;
			}
			else {
				System.out.println("Please create the Test Execution Issue in Jira as ====>>> "+testExecutionSummary);
			}
		}


		Response res1 = getTestRun(testCaseId,testExecutionId); 
		String testRunId = res1.jsonPath().get("data.getTestRun.id");
		if(testRunId==null)
		{
			System.out.println("Please add the testCase into TestExecution");
		}
		else {
			updateTestRunStatus(testRunId,status);
		}


	}


	/**
	 * Update Complete Test Run Result 
	 */
	public void updateTestRunStatus(String testRunId,String status) {
		//[EXECUTING , PASSED , FAILED , TO DO]

		q.setQuery("mutation {\r\n"
				+ "    updateTestRunStatus( id: \""+testRunId+"\", status: \""+status+"\")\r\n"
				+ "}");
		post(q).then().log().all();
	}


	/**
	 * Get Test
	 * @return 
	 */
	public Response getTestsAndTestExcecution() {


		q.setQuery("{\r\n"
				+ "    getExpandedTests(limit: 100) {\r\n"
				+ "        total\r\n"
				+ "        results {\r\n"
				+ "            issueId\r\n"
				+ "            jira(fields: [\"summary\"])\r\n"
				+ "            projectId\r\n"
				+ "        }\r\n"
				+ "    }\r\n"
				+ "    getTestExecutions(limit: 100) {\r\n"
				+ "        total\r\n"
				+ "        results {\r\n"
				+ "            issueId\r\n"
				+ "            jira(fields: [\"summary\"])\r\n"
				+ "        }\r\n"
				+ "    }\r\n"
				+ "}");

		return post(q);

	}


	/**
	 * Get test Run
	 * @return 
	 */
	public Response getTestRun(String testIssueId, String testExecIssueId) {


		q.setQuery("{\r\n"
				+ "    getTestRun( testIssueId: \""+testIssueId+"\", testExecIssueId: \""+testExecIssueId+"\") {\r\n"
				+ "        id\r\n"
				+ "        status {\r\n"
				+ "            name\r\n"
				+ "            color\r\n"
				+ "            description\r\n"
				+ "        }\r\n"
				+ "        gherkin\r\n"
				+ "        examples {\r\n"
				+ "            id\r\n"
				+ "            status {\r\n"
				+ "                name\r\n"
				+ "                color\r\n"
				+ "                description\r\n"
				+ "            }\r\n"
				+ "        }\r\n"
				+ "    }\r\n"
				+ "}");

		return post(q);
	}
	
/*	public void getTests() {


		q.setQuery("{\r\n"
				+ "    getExpandedTests(limit: 100) {\r\n"
				+ "        total\r\n"
				+ "        results {\r\n"
				+ "            issueId\r\n"
				+ "            jira(fields: [\"summary\"])\r\n"
				+ "            projectId\r\n"
				+ "        }\r\n"
				+ "    }\r\n"
				+ "}");

		post(q)
		.then().log().all();

	}

*/

	/**
	 * Get Test exectution
	 */
/*	public void getTestsExcecutions() {


		q.setQuery("{\r\n"
				+ "    getTestExecutions(limit: 100) {\r\n"
				+ "        total\r\n"
				+ "        results {\r\n"
				+ "            issueId\r\n"
				+ "            jira(fields: [\"summary\"])\r\n"
				+ "        }\r\n"
				+ "    }\r\n"
				+ "}");

		post(q).then().log().all();

	}
*/
/*	public void getTestsExcecutionsWithTestCases() {


		q.setQuery("{\r\n"
				+ "    getTestExecutions(limit: 100) {\r\n"
				+ "        total\r\n"
				+ "        results {\r\n"
				+ "            projectId\r\n"
				+ "            issueId\r\n"
				+ "            jira(fields: [\"summary\"])\r\n"
				+ "     tests(limit: 10) {\r\n"
				+ "                total\r\n"					
				+ "        results {\r\n"
				+ "            issueId\r\n "
				+ "       jira(fields: [\"summary\"])\r\n"
				+ "        }\r\n"		
				+ "            }\r\n"				
				+ "        }\r\n"
				+ "    }\r\n"				
				+ "}");

		post(q).then().log().all();

	}
*/
	


	/**
	 * Add Test Step
	 */
/*	public void addTestStep() {


		q.setQuery("mutation {\r\n"
				+ "    addTestStep(\r\n"
				+ "        issueId: \"10000\",\r\n"
				+ "        step: {\r\n"
				+ "            action: \"Use Xray Cloud Rest Api to add a new Step to the Test\",\r\n"
				+ "            data: \"data Abc\"\r\n"
				+ "            result: \"Step was added to the Test\",\r\n"
				+ "            customFields: [{id:\"621a4507b03d4f0019d29452\", value:\"Tokyo\"}]\r\n"
				+ "        }\r\n"
				+ "    ) {\r\n"
				+ "        id\r\n"
				+ "        action\r\n"
				+ "        data\r\n"
				+ "        result\r\n"
				+ "    }\r\n"
				+ "}");
		post(q).then().log().all();
	}
*/
	/**
	 * create test as manual
	 */
/*	public void createTest() {

		q.setQuery("mutation {\r\n"
				+ "    createTest(\r\n"
				+ "        testType: { name: \"Manual\" },\r\n"
				+ "       \r\n"
				+ "        jira: {\r\n"
				+ "            fields: { summary:\"Exploratory Test\",description:\"Hi\", \r\n"
				+ "            project: {key: \"XRAY\"} }\r\n"
				+ "        }\r\n"
				+ "    ) {\r\n"
				+ "        test {\r\n"
				+ "            issueId\r\n"
				+ "            testType {\r\n"
				+ "                name\r\n"
				+ "            }\r\n"
				+ "            steps {\r\n"
				+ "                action\r\n"
				+ "                data\r\n"
				+ "                result\r\n"
				+ "            }\r\n"
				+ "            jira(fields: [\"key\"])\r\n"
				+ "        }\r\n"
				+ "        warnings\r\n"
				+ "    }\r\n"
				+ "}");

		post(q).then().log().all();
	}

*/

	/**
	 * Create the Test ALong with Steps
	 */

/*	public void createTestAlongSteps() {


		q.setQuery("mutation {\r\n"
				+ "    createTest(\r\n"
				+ "        testType: { name: \"Manual\" },\r\n"
				+ "       steps: [\r\n"
				+ "            {\r\n"
				+ "                action: \"Create first example step\",\r\n"
				+ "                result: \"First step was created\"\r\n"
				+ "            },\r\n"
				+ "            {\r\n"
				+ "                action: \"Create second example step with data\",\r\n"
				+ "                data: \"Data for the step\",\r\n"
				+ "                result: \"Second step was created with data\"\r\n"
				+ "            }\r\n"
				+ "        ],\r\n"
				+ "        jira: {\r\n"
				+ "            fields: { summary:\"Exploratory Test\",description:\"Hi\", \r\n"
				+ "            project: {key: \"XRAY\"} }\r\n"
				+ "        }\r\n"
				+ "    ) {\r\n"
				+ "        test {\r\n"
				+ "            issueId\r\n"
				+ "            testType {\r\n"
				+ "                name\r\n"
				+ "            }\r\n"
				+ "            steps {\r\n"
				+ "                action\r\n"
				+ "                data\r\n"
				+ "                result\r\n"
				+ "            }\r\n"
				+ "            jira(fields: [\"key\"])\r\n"
				+ "        }\r\n"
				+ "        warnings\r\n"
				+ "    }\r\n"
				+ "}");

		post(q).then().log().all();
	}

*/

	

	/**
	 * Update TestRun Step Status
	 */
/*	public void updateTestRunStepStatus(String status) {


		q.setQuery("mutation {\r\n"
				+ "    updateTestRunStepStatus(\r\n"
				+ "        testRunId: \"621a4a7fc5daad001a40b191\",\r\n"
				+ "        stepId: \"0e793077-ab26-4fe5-820a-ff25208d27a8\",\r\n"
				+ "        status: \""+status+"\"\r\n"
				+ "    ) {\r\n"
				+ "        warnings\r\n"
				+ "    }\r\n"
				+ "}");

		post(q).then().log().all();
	}
*/
}
