package cop.genome;

import java.util.List;

public interface ListGenomeFactory<T extends List<K>, K> extends GenomeFactory<T> {

    T createGenes(List<K> genes);

}
