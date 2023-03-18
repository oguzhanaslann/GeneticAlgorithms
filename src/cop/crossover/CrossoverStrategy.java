package cop.crossover;

import cop.genome.Genome;

import java.util.List;

public interface CrossoverStrategy<T> {

    List<Genome<T>> crossover(Genome<T> parent1, Genome<T> parent2);

    List<Genome<T>> crossoverByRate(Genome<T> parent1, Genome<T> parent2, double rate);

}
