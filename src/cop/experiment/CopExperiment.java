package cop.experiment;

import cop.crossover.OnePointCrossover;
import cop.fitness.COPFitnessCalculator;
import cop.genome.COPGenome;
import cop.genome.CopGenomeFactory;
import cop.genome.Genome;
import cop.mating.CopMatingStrategy;
import cop.mutation.BitFlipMutation;
import cop.selection.RouletteWheelSelection;
import cop.survival.COPSurvivalSelectionStrategy;

import java.util.ArrayList;
import java.util.List;

public class CopExperiment extends Experiment<List<Byte>> {

    public CopExperiment(
            int geneSizeOfIndividuals,
            int populationSize,
            int numberOfGenerations,
            double crossOverRate,
            double mutationRate
    ) {
        super(
                geneSizeOfIndividuals,
                populationSize,
                numberOfGenerations,
                crossOverRate,
                new OnePointCrossover(new CopGenomeFactory()),
                mutationRate,
                new BitFlipMutation(),
                new CopMatingStrategy(),
                new COPSurvivalSelectionStrategy(
                        populationSize,
                        populationSize / 10,
                        new RouletteWheelSelection<>()
                ),
                new COPFitnessCalculator()
        );
    }

    @Override
    protected List<Genome<List<Byte>>> getInitPopulation(int populationSize) {
        ArrayList<Genome<List<Byte>>> population = new ArrayList<>();
        int geneSizeOfIndividuals = getGeneSizeOfIndividuals();
        for (int i = 0; i < populationSize; i++) {
            population.add(new COPGenome(geneSizeOfIndividuals));
        }
        return population;
    }


}
