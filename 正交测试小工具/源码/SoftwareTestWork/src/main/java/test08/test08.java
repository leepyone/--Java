package test08;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class test08 {
    public static void main(String[] args) throws Exception {

        dataDeal dataDeal = new dataDeal();
//        File fileData = new File(".\\src\\main\\java\\test08\\data.txt");
//        File fileTest = new File(".\\src\\main\\java\\test08\\test.txt");
        String fileNameData = new File("").getAbsolutePath()+"\\data.txt";
        String fileNameTest = new File("").getAbsolutePath()+"\\test.txt";
        File fileData = new File(fileNameData);
        File fileTest = new File(fileNameTest);

        dataToExcel dataToExcel = new dataToExcel();
        List<List<String>> dataList = dataDeal.handleResult(fileData,fileTest);
        if(dataList==null){


            return;
        }
        List<String> attributes =dataDeal.getAttributes();
        dataToExcel.setAttributeAndDataList(attributes,dataList);
        dataToExcel.noModleWrite();
//        System.out.println(new File("").getAbsolutePath());

    }

}
