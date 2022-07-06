package utils;

import java.io.FileWriter;
import java.io.IOException;

public class WriteFileUtil {

    public FileWriter getWriter(String url,boolean isappend){
        FileWriter fw=null;
        try {
            fw = new FileWriter(url,false);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fw;

    }
}
