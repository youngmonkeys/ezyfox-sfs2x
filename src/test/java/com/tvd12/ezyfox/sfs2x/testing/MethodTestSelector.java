package com.tvd12.ezyfox.sfs2x.testing;

import java.util.List;

import org.testng.IMethodSelector;
import org.testng.IMethodSelectorContext;
import org.testng.ITestNGMethod;

public class MethodTestSelector implements IMethodSelector {
    private static final long serialVersionUID = 3049018039943594946L;

    @Override
    public boolean includeMethod(IMethodSelectorContext context, 
            ITestNGMethod method, boolean isTestMethod) {
        return true;
    }

    @Override
    public void setTestMethods(List<ITestNGMethod> arg0) {
        
    }

}
