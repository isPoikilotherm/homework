package question_3.orders;

import java.util.ArrayList;
import java.util.List;

public class NatureOrder implements DefaultOeder{
    List<String> naturelist=new ArrayList<>();
    @Override
    public void add(String text) {
        naturelist.add(text);

    }

    @Override
    public void sort() {

    }

    @Override
    public String getText(Integer index) {

        return naturelist.get(index).split("\\s+")[1];
    }
}
