package cop.genome;

import java.util.List;

public class CopGenomeFactory implements ListGenomeFactory<List<Byte>, Byte> {

    @Override
    public Genome<List<Byte>> createGenome(List<Byte> genome) {
        return new COPGenome(genome);
    }

    @Override
    public List<Byte> createGenes(List<Byte> genes) {
        return genes;
    }
}
