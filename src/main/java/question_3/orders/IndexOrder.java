package question_3.orders;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class IndexOrder implements DefaultOeder{
    List<String> indexlist=new ArrayList<>();
    @Override
    public void add(String text) {
        indexlist.add(text);
    }

    @Override
    public void sort() {
        indexlist.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return Integer.valueOf(o1.split("\\s+")[0])-Integer.valueOf(o2.split("\\s+")[0]);
                //return o1.split("\\s+")[0].compareTo(o2.split("\\s+")[0]);
            }
        });
    }

    @Override
    public String getText(Integer index) {

        return  indexlist.get(index).split("\\s+")[1];
    }
}
