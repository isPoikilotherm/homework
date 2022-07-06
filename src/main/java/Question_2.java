import utils.ReadFileUtil;

import java.io.*;
import java.net.URISyntaxException;

public class Question_2 {
    static String url;
    static int sum;
    static int valid_count;
    static boolean tag;

    public static void main(String[] args) {
        url="src/main/resources/StringUtils.java";
        BufferedReader br = new ReadFileUtil().getreader(url);
        String line = null;
        tag=false;
        try {
            while ((line = br.readLine()) != null) {
                check(line);
                sum++;
              //  System.out.println(line);
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
        if (str.length()==0){
            return;
        }
        String[] s = str.split(" ");
        int i=0;
        while (i<s.length&&(s[i].equals("")||s[i].equals(" "))){
            i++;
        }
        if (i==s.length){
            return;
        }
            if (s[i].equals("*/")){
                tag=false;
                return;
            }
            if (s[i].equals("*")||s[i].equals("/**")||s[i].equals("//")||tag){
                return;
            }
            if (s[i].equals("/*")){
                tag=true;
                return;
            }
            if (s[i].toCharArray()[0]=='/'){
                return;
            }
            valid_count+=1;
    }


}
