package question_5;

import org.jsoup.Jsoup;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class Spider {
    String url="";
    Spider(String url){
        this.url=url;
    }
    String job() {
        int sum=0,chinese=0,english=0,tag=0;
        String html=getData();
        if (html==null){
            return null;
        }
        char[] chars = html.toCharArray();
        System.out.println(chars);
        for (char ch : chars) {
            if (Character.isSpaceChar(ch)){
                continue;
            }
            sum+=1;
            if ((ch >= 65)&&(ch <= 90)||(ch >= 97)&&(ch <= 122)){
                english+=1;
            }else if ((ch >= 0x4E00)&&(ch <= 0x9FA5)){
                chinese+=1;
            }else if (!Character.isDigit(ch)){
                tag+=1;
            }

        }
       return "总字数是："+sum+";  汉字数是："+chinese+";  字母数是："+english+";  符号数是"+tag;

    }

    public String getData(){

        URL req=null;
        StringBuilder sb=new StringBuilder();
        try {
            req=new URL(url);
        } catch (MalformedURLException e) {
            System.out.println("URL格式错误");
            e.printStackTrace();
            return null;
        }

        try {
            InputStream is = req.openStream();
            InputStreamReader isr=new InputStreamReader(is);
            BufferedReader br=new BufferedReader(isr);
            String line=br.readLine();
            while (line!=null){
                sb.append(line).append('\n');
                line=br.readLine();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return Jsoup.parse(sb.toString()).text();
    }
}