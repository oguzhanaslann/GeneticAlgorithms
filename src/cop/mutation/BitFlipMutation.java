package cop.mutation;

import cop.genome.Genome;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BitFlipMutation implements MutationStrategy<List<Byte>> {
    public static final int GENE_SIZE_DIVIDER = 3;
    private Random random = new Random();

    @Override
    public void mutate(List<Genome<List<Byte>>> genome) {
        for (Genome<List<Byte>> genomeToMutate : genome) {
            ArrayList<Byte> genes = new ArrayList<>(genomeToMutate.getGenome());
            int geneSize = genes.size();
            int mutationGeneSize = geneSize / GENE_SIZE_DIVIDER;
            mutateGenes(genes, geneSize, mutationGeneSize);
        }
    }

    private void mutateGenes(ArrayList<Byte> genes, int geneSize, int mutationGeneSize) {
        for (int i = 0; i < mutationGeneSize; i++) {
            int geneIndex = random.nextInt(geneSize);
            genes.set(geneIndex, flippedBit(genes.get(geneIndex)));
        }
    }

    private Byte flippedBit(Byte gene) {
        return (byte) (gene == 0 ? 1 : 0);
    }

    @Override
    public void mutateByRate(List<Genome<List<Byte>>> genome, double rate) {
        double probability = random.nextDouble();
        if (probability < rate) {
            mutate(genome);
        }
    }
}
