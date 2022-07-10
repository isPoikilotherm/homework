import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import utils.ReadFileUtil;

import java.io.*;
import java.util.*;


public class Question_1 {
    static String file=null;
    static int sum=0;
    static int Get_Count=0;
    static int Post_Count=0;
    static Map<String,Integer> count_map;
    static Multimap<String,String> url_map;
    static Multimap<Integer,String> url_count_map;
    static PriorityQueue<Integer> priorityQueue;


    public static void main(String[] args) {
        file="src/main/resources/access.log";
        count_map= new HashMap<>(); //通过map统计每个url访问的次数  K：url  V：次数
        url_map= HashMultimap.create();     //guava中的Multimap，利用可以存在多个V的特性，存储以/AAA分类后的url
        url_count_map= HashMultimap.create();   //以 K：次数  V：url 的形式存储，尚未解决访问次数相同的情况
        priorityQueue=new PriorityQueue<>(new Comparator<Integer>() {//使用优先级队列对访问次数进行排序
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2-o1;
            }
        });
        BufferedReader br = new ReadFileUtil().getreader(file);
        String line = null;
        try {
            while ((line = br.readLine()) != null) {
                add(line);
                sum++;       //统计总请求数量
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("总请求数： "+sum);

        System.out.println("###########################################################################");

        for (String s_key: count_map.keySet()) {
            url_count_map.put(count_map.get(s_key),s_key);
            priorityQueue.add(count_map.get(s_key));
        }
        List<String> list=new ArrayList<>();
        while (list.size()<10){
            int num=priorityQueue.poll();
            Collection<String> strings = url_count_map.get(num);
            for (String s : strings) {
                list.add(s);
            }
        }

        System.out.println("访问频率最高的十个地址及其次数分别是：");
        for (String s : list) {
            System.out.println(s);
            System.out.println(count_map.get(s)+"次");
        }

        System.out.println("###########################################################################");


        System.out.println("GET请求数： "+Get_Count);
        System.out.println("POST请求数： "+Post_Count);


        System.out.println("###########################################################################");
        

        for (String s:url_map.keySet()) {
            System.out.println(s+"类:");
            Collection<String> strings = url_map.get(s);
            for (String s1 : strings) {
                System.out.println(s1);
            }
        }

    }


    public static void add(String str){
        String[] strings = str.split(" ");
        if (strings[0].equals("GET")){
            Get_Count+=1;       //统计GET请求数量
        }
        if (strings[0].equals("POST")){
            Post_Count+=1;      //统计POST请求数量
        }
        //strings[1]为请求方式后面的url
        count_map.put(strings[1],count_map.getOrDefault(strings[1],0)+1);

        String[] split = strings[1].split("/");
        url_map.put(split[1],strings[1]);

    }
}
