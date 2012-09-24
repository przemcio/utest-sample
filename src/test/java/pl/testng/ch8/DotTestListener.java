package pl.testng.ch8;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;


public class DotTestListener extends TestListenerAdapter{
    @Override
    public void onTestFailure(ITestResult tr) {
        System.out.println("Failure");
    }

    @Override
    public void onTestSkipped(ITestResult tr) {
        System.out.println("Skipped");
    }

        @Override
    public void onTestSuccess(ITestResult tr) {

            String className = tr.getTestClass().getName().substring(tr.getTestClass().getName().lastIndexOf(".") +1,tr.getTestClass().getName().length());


            System.out.println("OK "+className+" "+tr.getMethod().getMethodName()+" "+(tr.getEndMillis()-tr.getStartMillis())+" ms");
    }

    @Override
    public void onFinish(ITestContext testContext) {
        System.out.println("");
        super.onFinish(testContext);
    }
}
