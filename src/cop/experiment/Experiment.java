package cop.experiment;

import cop.crossover.CrossoverStrategy;
import cop.fitness.FitnessCalculator;
import cop.genome.Genome;
import cop.mating.MatingStrategy;
import cop.mutation.MutationStrategy;
import cop.survival.SurvivalSelectionStrategy;
import cop.util.Pair;

import java.util.ArrayList;
import java.util.List;

public abstract class Experiment<T> {
    private final int geneSizeOfIndividuals;
    private final int populationSize;
    private final int numberOfGenerations;
    private final double crossOverRate;
    private final CrossoverStrategy<T> crossoverStrategy;
    private final double mutationRate;
    private final MutationStrategy<T> mutationStrategy;
    private final MatingStrategy<T> matingStrategy;
    private final SurvivalSelectionStrategy<T> survivalSelectionStrategy;
    private final FitnessCalculator<T> fitnessCalculator;
    private final ArrayList<Genome<T>> population = new ArrayList<>();
    private String name;
    private Genome<T> bestGenome;


    public Experiment(
            int geneSizeOfIndividuals,
            int populationSize,
            int numberOfGenerations,
            double crossOverRate,
            CrossoverStrategy<T> crossoverStrategy,
            double mutationRate,
            MutationStrategy<T> mutationStrategy,
            MatingStrategy<T> matingStrategy,
            SurvivalSelectionStrategy<T> survivalSelectionStrategy,
            FitnessCalculator<T> fitnessCalculator
    ) {
        this.geneSizeOfIndividuals = geneSizeOfIndividuals;
        this.populationSize = populationSize;
        this.numberOfGenerations = numberOfGenerations;
        this.crossOverRate = crossOverRate;
        this.crossoverStrategy = crossoverStrategy;
        this.mutationRate = mutationRate;
        this.mutationStrategy = mutationStrategy;
        this.matingStrategy = matingStrategy;
        this.survivalSelectionStrategy = survivalSelectionStrategy;
        this.fitnessCalculator = fitnessCalculator;
    }


    public final void run() {

        List<Genome<T>> initPopulation = getInitPopulation(populationSize);
        if (initPopulation.size() != populationSize) {
            throw new IllegalArgumentException("The size of the initial population must be equal to the population size");
        }
        population.clear();
        population.addAll(initPopulation);
        evaluateEachIndividualFitness();
        bestGenome = population.get(0);
        calculateAndUpdateBestGenome();

        for (int i = 0; i < numberOfGenerations; i++) {
            ArrayList<Genome<T>> offSprings = new ArrayList<>();

            for (int j = 0; j < (populationSize / 2); j++) {
                Pair<Genome<T>, Genome<T>> parentPair = matingStrategy.getMateFrom(population);
                Genome<T> parent1 = parentPair.first;
                Genome<T> parent2 = parentPair.second;

                List<Genome<T>> children = crossoverStrategy.crossoverByRate(parent1, parent2, crossOverRate);
                mutationStrategy.mutateByRate(children, mutationRate);

                offSprings.addAll(children);
                calculateAndUpdateBestGenomeBy(children);
            }

            population.addAll(offSprings);

            List<Genome<T>> survivors = survivalSelectionStrategy.selectSurvivors(population);
            population.clear();
            population.addAll(survivors);
        }
    }


    protected abstract List<Genome<T>> getInitPopulation(int populationSize);

    private void evaluateEachIndividualFitness() {
        for (Genome<T> genome : population) {
            genome.calculateFitness(fitnessCalculator);
        }
    }

    protected final void calculateAndUpdateBestGenome() {
        Genome<T> bestGenome = getBestGenome();

        if (bestGenome == null) {
            return;
        }

        for (Genome<T> genome : population) {
            boolean isBetterGenome = genome.compareTo(bestGenome) > 0;
            if (isBetterGenome) {
                bestGenome = genome;
            }
        }
    }

    private void calculateAndUpdateBestGenomeBy(List<Genome<T>> genomes) {
        for (Genome<T> genome : genomes) {
            boolean isBetterGenome = genome.compareTo(bestGenome) > 0;
            if (isBetterGenome) {
                bestGenome = genome;
            }
        }
    }

    public Genome<T> getBestGenome() {
        return bestGenome;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    protected int getGeneSizeOfIndividuals() {
        return geneSizeOfIndividuals;
    }

    protected int getPopulationSize() {
        return populationSize;
    }

    protected int getNumberOfGenerations() {
        return numberOfGenerations;
    }

    protected double getCrossOverRate() {
        return crossOverRate;
    }

    protected CrossoverStrategy<T> getCrossoverStrategy() {
        return crossoverStrategy;
    }

    protected double getMutationRate() {
        return mutationRate;
    }

    protected MutationStrategy<T> getMutationStrategy() {
        return mutationStrategy;
    }

    protected MatingStrategy<T> getMatingStrategy() {
        return matingStrategy;
    }

    protected SurvivalSelectionStrategy<T> getSurvivalSelectionStrategy() {
        return survivalSelectionStrategy;
    }

    protected FitnessCalculator<T> getFitnessCalculator() {
        return fitnessCalculator;
    }

    protected List<Genome<T>> getPopulation() {
        return population;
    }

    public int getCurrentPopulationSize() {
        return population.size();
    }
}
