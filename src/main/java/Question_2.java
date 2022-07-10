import utils.ReadFileUtil;

import java.io.*;
import java.net.URISyntaxException;

public class Question_2 {
    static String file;
    static int sum;         //总行数
    static int valid_count;         //有效行数
    static boolean tag;     //标志，判断是否在段注释内

    public static void main(String[] args) {
        file="src/main/resources/StringUtils.java";
        BufferedReader br = new ReadFileUtil().getreader(file);
        String line = null;
        tag=false;
        try {
            while ((line = br.readLine()) != null) {
                check(line);
                sum++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(sum);
        System.out.println(valid_count);

        try {
            FileWriter fw = new FileWriter(new File(ClassLoader.getSystemResource("").
                    toURI()).getAbsolutePath()+"/validLineCount.txt ",false);
            fw.write(Integer.toString(valid_count));
            fw.flush();
            fw.close();
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public static void check(String str){
        str = str.trim();       //删除前后空格
        if (str.length()==0){       //空行
            return;
        }

        if (str.startsWith("*/")){     //判断注释段是否结束
            tag=false;
            return;
        }

        if (str.startsWith("/*")){      //判断注释段是否开始
            tag=true;
            return;
        }

        if (str.startsWith("/")||tag){      //判断是否为注释行或者是否在注释段内
            return;
        }

        valid_count+=1;
    }

}
