package cop.mutation;

import cop.genome.Genome;

public interface MutationStrategy<T> {
    Genome<T> mutate(Genome<T> genome);
}
