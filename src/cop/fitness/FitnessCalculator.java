package cop.fitness;

import cop.genome.Genome;

public interface FitnessCalculator<T> {
    int calculateFitnessOf(Genome<T> genome);
}
