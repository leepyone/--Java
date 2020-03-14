package test08;

import com.alibaba.excel.EasyExcel;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class dataToExcel {
    //所有的属性集合，用于初始化表格的头部
    private  List<String > attribute;
    private  List<List<String>> dataList;

    public void setAttributeAndDataList(List<String > attribute,List<List<String>> dataList){
        this.attribute=attribute;
        this.dataList = dataList;
    }

    public void noModleWrite() {
        // 写法1
//        String fileName = TestFileUtil.getPath() + "noModleWrite" + System.currentTimeMillis() + ".xlsx";
        String fileName = new File("").getAbsolutePath() + "noModleWrite" + System.currentTimeMillis() + ".xlsx";

        // 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
        EasyExcel.write(fileName).head(head()).sheet("模板").doWrite(dataList);
    }

    private List<List<String>> head() {
        List<List<String>> list = new ArrayList<List<String>>();

        for(String a : attribute){
            List<String> head = new ArrayList<String>();
            head.add(a);
            list.add(head);
        }

        return list;
    }

    private List<List<Object>> dataList() {
        List<List<Object>> list = new ArrayList<List<Object>>();
        for (int i = 0; i < 10; i++) {
            List<Object> data = new ArrayList<Object>();
            data.add("字符串" + i);
            data.add(new Date());
            data.add(0.56);
            list.add(data);
        }
        return list;
    }
}
