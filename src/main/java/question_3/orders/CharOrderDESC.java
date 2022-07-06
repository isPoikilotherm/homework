package question_3.orders;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class CharOrderDESC implements DefaultOeder{
    List<String> desclist=new ArrayList<>();
    @Override
    public void add(String text) {
        desclist.add(text);

    }

    @Override
    public void sort() {
        desclist.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return   o2.split("\\s+")[1].compareTo(o1.split("\\s+")[1]);
            }
        });

    }
    @Override
    public String getText(Integer index) {

        return desclist.get(index).split("\\s+")[1];
    }
}
