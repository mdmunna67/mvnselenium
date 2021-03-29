package com.selenium.listeners;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.HashMap;

import com.selenium.base.AppConfig;
import com.selenium.base.TestBase;
import com.selenium.testdata.DataReader;
import org.testng.IAnnotationTransformer;
import org.testng.annotations.ITestAnnotation;

public class Transformer implements IAnnotationTransformer {

    static TestBase testBase = new TestBase();
    static HashMap<String, HashMap<String, String>> runmodeData = DataReader.testDataMappedToTestName(AppConfig.getTestDataExcelFileName(), AppConfig.getRunModeSheetName());

    public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
            annotation.setEnabled(DataReader.isRunnable(testMethod.getName(), runmodeData));
    }
}
