package cop.mating;

import cop.genome.Genome;
import cop.util.Pair;

import java.util.List;

public interface MatingStrategy<T> {

    Pair<Genome<T>, Genome<T>> getMateFrom(List<Genome<T>> population);
}
