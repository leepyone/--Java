package test08;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class dataDeal {

    private List<String> attributes;

    public List<String> getAttributes(){
        return attributes;
    }
    public List<List<String>> handleResult(File fileData,File fileTest) throws Exception {
        fileDeal fileDeal = new fileDeal();
        ArrayList<String> attributes= new ArrayList<>();
        Map<String,List<String>> map = fileDeal.getAttribute(fileTest,attributes);

        this.attributes=attributes;
        //因素的个数
        int attribute=attributes.size();
        //取值的个数
        int numbers=map.get(attributes.get(0)).size();
        int[][] data = fileDeal.getData(attribute,numbers,fileData);

        int value = attribute+numbers*100;

        if(!fileDeal.getHandlerList(fileData).contains(value)){
            System.out.println("这个情况data中没有记录，或test文件中数据格式不对");
            fileDeal.PrintChoice();
            return null;
        }
        return  dealData(data,map,attributes);

    }

    private List<List<String>> dealData(int[][] data, Map<String, List<String>> map,List<String> attributes){
        List<List<String>> list = new ArrayList<>();
        int row = data.length;
        for(int i=0;i<row;i++){
            List<String> stringList = new ArrayList<>();
            for(int j=0;j<data[0].length;j++){
                String name=attributes.get(j);
                String value = map.get(name).get(data[i][j]);
                stringList.add(value);
                System.out.print(value+" ");
            }
            list.add(stringList);
            System.out.println();
        }
        return list;
    }
}
