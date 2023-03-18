package cop.genome;

import cop.fitness.FitnessCalculator;

public interface Genome<T> extends Comparable<Genome<T>> {

    T getGenome();

    double getFitness();

    void calculateFitness(FitnessCalculator<T> calculator);

}
