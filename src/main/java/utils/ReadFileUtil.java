package utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class ReadFileUtil {
    public BufferedReader getreader(String url){
        BufferedReader br = null;

        try {
            FileReader fileReader=new FileReader(url);
            br=new BufferedReader(fileReader);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return br;
    }

}
