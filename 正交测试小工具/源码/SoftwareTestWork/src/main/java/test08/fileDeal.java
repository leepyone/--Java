package test08;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class fileDeal {
    List<Integer> HandleList;
    //从test.txt 中读取数据
    public Map<String,List<String>> getAttribute(File file,List<String> attributes) throws Exception {
        Map<String,List<String>> map =new HashMap<>();
//        FileReader in =  new FileReader(file);
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file),"UTF-8"));

        ArrayList<String> lists = new ArrayList<>();
        String s;
        while((s=reader.readLine())!=null){
            lists.add(s);
        }
        reader.close();

        int row = lists.size();
        String[][] attribute = new String[row][];

        //将每一个的属性及其的取值存储到map
        for(String str : lists){
            List<String> list = new ArrayList<>();
            String[] strs = str.split(":");
            String name = strs[0];
            attributes.add(name);
            String[]  attributeStrs = strs[1].split(",");
            for (String a:attributeStrs) {
                list.add(a);
            }
            map.put(name,list);
        }

         return map;
    }
    //将可以处理的情况进行打印
    public  void PrintChoice(){
        System.out.println("可以处理的取值情况和控件数的组合如下:");
        for(int i:HandleList){
            int n=i/100;
            int column=i%100;
            System.out.println(n+"^"+column);
        }
    }

    //从data.txt 中读取数据
    public List<Integer> getHandlerList(File file) throws Exception{
//        HashMap<Integer, Integer> map = new HashMap<>();
        ArrayList<Integer> list = new ArrayList<>();
//        FileReader in = new FileReader(file);
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file),"UTF-8"));
        ArrayList<String> strs = new ArrayList<>();
        String s;
        while(( s =reader.readLine())!=null){
            if(!s.isEmpty()&&s.charAt(0)=='s'){
                strs.add(s);
            }
        }
        reader.close();
        for(String str:strs){
            char[] chars = str.toCharArray();
            int column=0,n=0;
            for (int i = 0; i <chars.length ; i++) {
                if(chars[i]=='^'){
                    n=chars[i-1]-48;
                    column=chars[i+1]-48;
//                    map.put(column,n);
                    list.add(n*100+column);
                }
            }
        }
        this.HandleList=list;
        return list;
    }

    public int[][] getData(int attributes,int numbers,File file) throws Exception{
        FileReader in = new FileReader(file);
        BufferedReader reader = new BufferedReader(in);
        ArrayList<String> strs = new ArrayList<>();
        String s;
        int row = 0;
        int count = 0;
        boolean begin =false;
        while(( s =reader.readLine())!=null){
            //获取对应的数据
            if(begin&&count<row){
                strs.add(s);
                count++;
                if(count==row){
                    break;
                }
            }
            //找到对应的开始语句
            if(!s.isEmpty()&&s.charAt(0)=='s'){
                char[] chars = s.toCharArray();
                int column=0,n=0;
                for (int i = 0; i <chars.length ; i++) {
                    if(chars[i]=='^'){
                        n=chars[i-1]-48;
                        column=chars[i+1]-48;

                    }
                }
                if(column==attributes&&numbers==n){
                    begin=true;
                    String[] strings = s.split("=");
                    row = Integer.parseInt(strings[1]);
                }

            }

        }
        in.close();
        //获取了每行的字符串 然后进行切割
        int[][] data = new int[strs.size()][attributes];
        count=0;
        for (String str:strs) {
            char[] chars = str.toCharArray();
            int[] ints = new int[attributes];
            for(int i =0;i<chars.length;i++){
                ints[i] = chars[i]-48;
            }
            data[count]=ints;
            count++;
        }

        return data;

    }
}
