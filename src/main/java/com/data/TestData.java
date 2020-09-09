package com.data;


import com.base.utilities.BaseClass;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TestData {

    Object[][] testData ;

    public TestData(){}

    public Object[][] getTestData(String filePath){

        try {
           List<Map<String,String>> dataSet =  (ArrayList)BaseClass.getInstance().getMapper().readValue(new File(filePath).getAbsoluteFile(), List.class);
           testData = new Object[dataSet.size()][1];
           for(int i=0;i<dataSet.size();i++){
               testData[i][0] = dataSet.get(i);
           }
            return testData;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
