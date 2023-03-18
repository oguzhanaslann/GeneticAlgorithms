package cop.experiment;

import cop.crossover.OnePointCrossover;
import cop.fitness.COPFitnessCalculator;
import cop.genome.Genome;
import cop.mutation.BitFlipMutation;
import cop.selection.RouletteWheelSelection;
import cop.survival.COPSurvivalSelectionStrategy;

import java.util.ArrayList;

public class CopExperiment extends Experiment<Byte[]> {
    public CopExperiment(
            int geneSizeOfIndividuals,
            int populationSize,
            int numberOfGenerations,
            double crossOverRate,
//            CrossoverStrategy<Byte[]> crossoverStrategy,
            double mutationRate
//            MutationStrategy<Byte[]> mutationStrategy,
//            MatingStrategy<Byte[]> matingStrategy,
//            SurvivalSelectionStrategy<Byte[]> survivalSelectionStrategy,
//            FitnessCalculator<Byte[]> fitnessCalculator
    ) {
        super(
                geneSizeOfIndividuals,
                populationSize,
                numberOfGenerations,
                crossOverRate,
                new OnePointCrossover<>(),
                mutationRate,
                new BitFlipMutation(),
                population -> null,
                new COPSurvivalSelectionStrategy(geneSizeOfIndividuals / 10, new RouletteWheelSelection<>()),
                new COPFitnessCalculator()
        );
    }

    @Override
    protected ArrayList<Genome<Byte[]>> getInitPopulation(int populationSize) {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
