package utility;



import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import org.testng.Reporter;

import Sample_Group.Sample_Artifact.ExcelWriting;
import Sample_Group.Sample_Artifact.FlipKartTesting;

public class Listener implements ITestListener, ISuiteListener, IInvokedMethodListener {
	
	ExcelWriting ex;
	
	public void onTestFailure(ITestResult result) {
    	System.out.println("***** Error "+result.getName()+" test has failed *****");
    	String methodName=result.getName().toString().trim();
    	takeScreenShot(methodName);
    }
	public void takeScreenShot(String methodName) {
    	
    	 File scrFile = ((TakesScreenshot)(FlipKartTesting.driver)).getScreenshotAs(OutputType.FILE);
         
            try {
				FileUtils.copyFile(scrFile, new File("./Screenshots/"+methodName+"1"+".png"));
				System.out.println("***Placed screen shot in "+"./Screenshots/"+" ***");
			} catch (IOException e) {
				e.printStackTrace();
			}
    }

	public void onFinish(ISuite arg0) {
		// TODO Auto-generated method stub

	}

	public void onStart(ISuite arg0) {
		// TODO Auto-generated method stub

	}

	public void onFinish(ITestContext arg0) {
		// TODO Auto-generated method stub

	}

	public void onStart(ITestContext arg0) {
		// TODO Auto-generated method stub

	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
		// TODO Auto-generated method stub

	}

	public void onTestSkipped(ITestResult arg0) {
		// TODO Auto-generated method stub

	}

	public void onTestStart(ITestResult arg0) {
		// TODO Auto-generated method stub

	}

	public void onTestSuccess(ITestResult arg0) {
		// TODO Auto-generated method stub

	}

	private void printTestResults(ITestResult result) {

		Reporter.log("Test Method resides in " + result.getTestClass().getName(), true);

		if (result.getParameters().length != 0) {

			String params = null;

			for (Object parameter : result.getParameters()) {

				params += parameter.toString() + ",";

			}

			Reporter.log("Test Method had the following parameters : " + params, true);

		}

		String status = null;

		switch (result.getStatus()) {

		case ITestResult.SUCCESS:

			status = "Pass";

			break;

		case ITestResult.FAILURE:

			status = "Failed";

			break;

		case ITestResult.SKIP:

			status = "Skipped";

		}

		Reporter.log("Test Status: " + status, true);

	}

	
	public void beforeInvocation(IInvokedMethod arg0, ITestResult arg1) {

		String textMsg = "About to begin executing following method : " + returnMethodName(arg0.getTestMethod());

		Reporter.log(textMsg, true);

	}



	public void afterInvocation(IInvokedMethod arg0, ITestResult arg1) {

		String textMsg = "Completed executing following method : " + returnMethodName(arg0.getTestMethod());

		Reporter.log(textMsg, true);
		if(ITestResult.FAILURE==arg1.getStatus())
		{
			try 
			{
			
			TakesScreenshot ts=(TakesScreenshot)(FlipKartTesting.driver);
			 
			
			File source=ts.getScreenshotAs(OutputType.FILE);
			 
			
			FileUtils.copyFile(source, new File("./Screenshots/"+arg1.getName()+".png"));
			 
			System.out.println("Screenshot taken");
			} 
			catch (Exception e)
			{
			 
			System.out.println("Exception while taking screenshot "+e.getMessage());
			} 
		}

	}
	
    

	

	private String returnMethodName(ITestNGMethod method) {

		return method.getRealClass().getSimpleName() + "." + method.getMethodName();

	}

}
