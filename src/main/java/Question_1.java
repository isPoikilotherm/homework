import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import utils.ReadFileUtil;

import java.io.*;
import java.util.*;


public class Question_1 {
    static String url=null;
    static int sum=0;
    static int Get_Count=0;
    static int Post_Count=0;
    static Map<String,Integer> count_map;
    static Multimap<String,String> url_map;
    static Multimap<Integer,String> url_count_map;
    static PriorityQueue<Integer> priorityQueue;


    public static void main(String[] args) {
        url="src/main/resources/access.log";
        count_map= new HashMap<>();
        url_map= HashMultimap.create();
        url_count_map= HashMultimap.create();
        priorityQueue=new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2-o1;
            }
        });
        BufferedReader br = new ReadFileUtil().getreader(url);
        String line = null;
        try {
            while ((line = br.readLine()) != null) {
                add(line);
                sum++;
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
            Get_Count+=1;
        }
        if (strings[0].equals("POST")){
            Post_Count+=1;
        }
        count_map.put(strings[1],count_map.getOrDefault(strings[1],0)+1);

        String[] split = strings[1].split("/");
        url_map.put(split[1],strings[1]);

    }
}
