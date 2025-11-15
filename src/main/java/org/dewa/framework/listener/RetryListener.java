package org.dewa.framework.listener;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryListener implements IRetryAnalyzer{

    private int count=0;
    @Override
    public boolean retry(ITestResult result) {
       
        int limit=0;
        if(count<limit){
            count++;
            return true;
        }
        return false;
    }

}
