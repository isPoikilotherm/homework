package question_3;

import org.apache.commons.lang.StringUtils;
import question_3.orders.CharOrder;
import question_3.orders.CharOrderDESC;
import question_3.orders.IndexOrder;
import question_3.orders.NatureOrder;
import utils.ReadFileUtil;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class TextDecode {

    public static void main(String[] args) {
        NatureOrder natureOrder=new NatureOrder();
        IndexOrder indexOrder=new IndexOrder();
        CharOrder charOrder=new CharOrder();
        CharOrderDESC charOrderDESC=new CharOrderDESC();

        BufferedReader br=new ReadFileUtil().getreader("src/main/resources/sdxl_prop.txt");
        String line=null;

        try {
            while ((line = br.readLine()) != null){
               natureOrder.add(line);
               indexOrder.add(line);
               charOrder.add(line);
               charOrderDESC.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        natureOrder.sort();
        charOrder.sort();
        indexOrder.sort();
        charOrderDESC.sort();

        br=new ReadFileUtil().getreader("src/main/resources/sdxl_template.txt");
        line=null;

        try {
            FileWriter fw=new FileWriter(new File(ClassLoader.getSystemResource("").
                    toURI()).getAbsolutePath()+"/sdxl.txt ",true);
            while ((line = br.readLine()) != null){
               if (line.contains("$")){
                   int fontsite=line.indexOf("$");
                   int endsite=line.indexOf(")",fontsite);
                   String funtion_and_index=line.substring(fontsite,endsite+1);
                   String funtion= StringUtils.substringBetween(funtion_and_index, "$", "(");
                   String index= StringUtils.substringBetween(funtion_and_index, "(", ")");
                   String text=null;
                   if (funtion.equals("natureOrder")){
                       text = natureOrder.getText(Integer.valueOf(index));
                   }else if (funtion.equals("indexOrder")){
                       text = indexOrder.getText(Integer.valueOf(index));
                   }else if (funtion.equals("charOrder")){
                       text = charOrder.getText(Integer.valueOf(index));
                   }else {
                       text = charOrderDESC.getText(Integer.valueOf(index));
                   }
                   line=line.substring(0,fontsite)+ text+ line.substring(endsite+1);

               }
                fw.write(line+"\r\n");
                fw.flush();
            }
            fw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}
