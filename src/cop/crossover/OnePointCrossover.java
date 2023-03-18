package cop.crossover;

import cop.genome.Genome;

import java.util.List;

public class OnePointCrossover<T> implements CrossoverStrategy<T> {

    @Override
    public List<Genome<T>> crossover(Genome<T> parent1, Genome<T> parent2) {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
