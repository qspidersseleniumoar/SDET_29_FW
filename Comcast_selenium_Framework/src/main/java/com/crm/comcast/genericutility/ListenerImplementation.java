package com.crm.comcast.genericutility;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import jiraXrayIntegration.GraphQLmethods;
import slackIntegration.SlackIntegrationTest;

public class ListenerImplementation implements ITestListener
{
	SlackIntegrationTest slack=new SlackIntegrationTest();
	GraphQLmethods xray=new GraphQLmethods();
	
	public void onTestStart(ITestResult result) {
		
		
	}

	public void onTestSuccess(ITestResult result) {
		String metodName = result.getMethod().getMethodName();
		try {
			slack.sendTestExecutionStatusToSlack(metodName+" is Success");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		xray.updateStatus(metodName, "PASSED");
		
		
	}

	public void onTestFailure(ITestResult result) {
		String metodName = result.getMethod().getMethodName();
		try {
			slack.sendTestExecutionStatusToSlack(metodName+" is Failed");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		xray.updateStatus(metodName, "FAILED");		
	}

	public void onTestSkipped(ITestResult result) {
		String metodName = result.getMethod().getMethodName();
		try {
			slack.sendTestExecutionStatusToSlack(metodName+" is Skipped");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		xray.updateStatus(metodName, "EXECUTING");
		
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		
		
	}

	public void onStart(ITestContext context) {
		
		
	}

	public void onFinish(ITestContext context) {
		
		
	}
	
	
	

}
