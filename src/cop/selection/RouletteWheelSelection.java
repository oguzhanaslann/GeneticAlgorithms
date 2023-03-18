package cop.selection;

import cop.genome.Genome;

import java.util.ArrayList;
import java.util.List;

public class RouletteWheelSelection<T> implements Selection<T> {
    @Override
    public List<Genome<T>> selectFrom(List<Genome<T>> population, int count) {
        ArrayList<Double> rouletteWheel = new ArrayList<>();

        // 1. calculate the sum of all fitness values
        double sum = 0;
        for (Genome<T> genome : population) {
            sum += genome.getFitness();
        }

        // 2. calculate the probability of each individual
        for (Genome<T> genome : population) {
            double probability = genome.getFitness() / sum;
            rouletteWheel.add(probability);
        }

        // calculate cumulative probabilities
        for (int i = 1; i < rouletteWheel.size(); i++) {
            rouletteWheel.set(i, rouletteWheel.get(i) + rouletteWheel.get(i - 1));
        }


        // 3. randomly select an individual based on the probability
        ArrayList<Genome<T>> selectedGenomes = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            double random = Math.random();
            for (int j = 0; j < rouletteWheel.size(); j++) {
                if (random < rouletteWheel.get(j)) {
                    selectedGenomes.add(population.get(j));
                    break;
                }
            }
        }

        return selectedGenomes;
    }

}
