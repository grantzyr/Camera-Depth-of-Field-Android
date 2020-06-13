package com.example.Model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LensManager implements Iterable<Lens> {
    private List<Lens> lens = new ArrayList<>();

    private static LensManager lenses = null;

    private LensManager () {}

    public static LensManager getInstance() {
        if (lenses == null) {
            lenses = new LensManager();
            lenses.add(new Lens("Canon", 1.8, 50));
            lenses.add(new Lens("Tamron", 2.8, 90));
            lenses.add(new Lens("Sigma", 2.8, 200));
            lenses.add(new Lens("Nikon", 4, 200));
        }
        return lenses;
    }

    public int size() {
        return lens.size();
    }

    public Lens getLens(int index) {
        return lens.get(index);
    }

    public void add(Lens lenses) {
        lens.add(lenses);
    }

    public void replace (Lens lenses, int index) {
        lens.set(index, lenses);
    }

    public void remove (int index) {
        lens.remove(index);
    }

    @Override
    public Iterator<Lens> iterator() {
        return lens.iterator();
    }
}

