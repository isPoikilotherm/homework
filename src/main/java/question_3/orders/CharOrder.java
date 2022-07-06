package question_3.orders;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class CharOrder implements DefaultOeder {
    List<String> charlist=new ArrayList<>();
    @Override
    public void add(String text) {
        charlist.add(text);
    }

    @Override
    public void sort() {
        charlist.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.split("\\s+")[1].compareTo(o2.split("\\s+")[1]);
            }
        });
    }

    @Override
    public String getText(Integer index) {
        return charlist.get(index).split("\\s+")[1];
    }
}
