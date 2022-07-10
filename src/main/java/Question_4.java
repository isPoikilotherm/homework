import utils.ReadFileUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Question_4 {

    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        String command=scanner.nextLine();
        String[] commands = command.split(" \\| ");
        List<String> result=new ArrayList<>();
        for (String cmd : commands) {
            if (is_cat(cmd)){
                result = cat_command(cmd);
            }else if (is_grep(cmd)){
                result=grep_command(result,cmd);
            }else if (is_wc(cmd)){
                result=wc_command(result,cmd);
            }else {
                System.out.println("命令错误");
                break;
            }
        }
        for (String line : result) {
            System.out.println(line);
        }

    }

    public static boolean is_cat(String command){
        String[] s = command.split(" ");
        if (s[0].equals("cat")&&s.length==2){
            return true;
        }
        return false;
    }
    public static boolean is_grep(String command){
        String[] s = command.split(" ");
        if (s[0].equals("grep")&&(s.length==2||s.length==3)){
            return true;
        }
        return false;
    }
    public static boolean is_wc(String command){
        String[] s = command.split(" ");
        if (s[0].equals("wc")&&(s.length==2||s.length==3)){
            return true;
        }
        return false;
    }


    public static List<String> cat_command(String command){
        String[] s = command.split(" ");
        List<String> res=new ArrayList<>();
        BufferedReader br = new ReadFileUtil().getreader("src/main/resources/"+s[1]);
        String line=null;
        try {
            while ((line = br.readLine()) != null){
                res.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return res;
    }

    public static List<String> grep_command(List<String> list,String command){
        String[] s = command.split(" ");
        List<String> res=new ArrayList<>();
        if (s.length==3){       //自带文件名
            BufferedReader br = new ReadFileUtil().getreader(s[1]);
            String line=null;
            try {
                while ((line = br.readLine()) != null){
                    if (line.contains(s[1])){
                        res.add(line);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            for (String line : list) {
                if (line.contains(s[1])){
                    res.add(line);
                }
            }
        }
        return res;
    }

    public static List<String> wc_command(List<String> list,String command){
        String[] s = command.split(" ");
        List<String> res=new ArrayList<>();
        int count=0;
        if (s.length==3){       //自带文件名
            BufferedReader br = new ReadFileUtil().getreader(s[1]);
            String line=null;
            try {
                while ((line = br.readLine()) != null){
                    count+=1;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            res.add(String.valueOf(count));
        }else {
            res.add(String.valueOf(list.size()));
        }
        return res;
    }
}
