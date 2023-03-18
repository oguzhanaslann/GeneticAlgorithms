package cop.selection;

import cop.genome.Genome;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ElitismSelection<T> implements Selection<T> {

    @Override
    public List<Genome<T>> selectFrom(List<Genome<T>> population, int count) {
        ArrayList<Genome<T>> copyPopulation = new ArrayList<>(population);
        Collections.sort(copyPopulation);
        // sorted in ascending order, get the best ones (last ones)
        return copyPopulation.subList(copyPopulation.size() - count, copyPopulation.size());
    }
}


