package cop.selection;

import cop.genome.Genome;

import java.util.List;

public class ElitismSelection<T> implements Selection<T> {

    int elitismCount;

    public ElitismSelection(int elitismCount) {
        this.elitismCount = elitismCount;
    }

    @Override
    public List<Genome<T>> selectFrom(List<Genome<T>> population) {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
