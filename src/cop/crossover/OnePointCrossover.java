package cop.crossover;

import cop.genome.Genome;
import cop.genome.GenomeFactory;
import cop.genome.ListGenomeFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class OnePointCrossover<T extends List<K>, K> implements CrossoverStrategy<T> {
    private final Random random = new Random();

    private final ListGenomeFactory<T,K> genomeFactory;

    public OnePointCrossover(ListGenomeFactory<T,K> genomeFactory) {
        this.genomeFactory = genomeFactory;
    }

    @Override
    public List<Genome<T>> crossover(Genome<T> parent1, Genome<T> parent2) {
        T parent1Genes = parent1.getGenome();
        T parent2Genes = parent2.getGenome();

        requireSameLength(parent1Genes, parent2Genes);

        int crossoverPoint = random.nextInt(parent1Genes.size());

        T child1Genes = createChildGenes(parent1Genes, parent2Genes, crossoverPoint);
        T child2Genes = createChildGenes(parent2Genes, parent1Genes, crossoverPoint);

        List<Genome<T>> children = new ArrayList<>();
        Genome<T> child1 = genomeFactory.createGenome(child1Genes);
        children.add(child1);

        Genome<T> child2 = genomeFactory.createGenome(child2Genes);
        children.add(child2);

        return children;
    }

    private T createChildGenes(T parent1Genes, T parent2Genes, int crossoverPoint) {
        ArrayList<K> childGenes = new ArrayList<>();
        for (int i = 0; i < crossoverPoint; i++) {
            childGenes.add(parent1Genes.get(i));
        }

        for (int i = crossoverPoint; i < parent2Genes.size(); i++) {
            childGenes.add(parent2Genes.get(i));
        }

        return genomeFactory.createGenes(childGenes);
    }

    private void requireSameLength(T parent1Genes, T parent2Genes) {
        if (parent1Genes.size() != parent2Genes.size()) {
            throw new IllegalArgumentException("Genomes must be of the same length");
        }
    }

    @Override
    public List<Genome<T>> crossoverByRate(Genome<T> parent1, Genome<T> parent2, double rate) {
        double probability = random.nextDouble();
        if (probability < rate) {
            return crossover(parent1, parent2);
        } else {
            List<Genome<T>> returnList = new ArrayList<>();
            returnList.add(parent1);
            returnList.add(parent2);
            return returnList;
        }
    }
}
