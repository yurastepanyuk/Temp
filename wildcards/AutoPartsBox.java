package day10.wildcards;

import day10.domain.AutoParts;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by stepanyuk on 25.08.2015.
 */
public class AutoPartsBox<T extends AutoParts> {

    private List<T> autoParts;

    public AutoPartsBox() {
        autoParts = new ArrayList<>();
    }

    public void add(T autoPart) {
        autoParts.add(autoPart);
    }

    public List<T> getAutoParts() {
        return new ArrayList<>(autoParts);
    }
}
