package cop.mutation;

import cop.genome.Genome;

import java.util.List;

public interface MutationStrategy<T> {
    void  mutate(List<Genome<T>> genome);

    void  mutateByRate(List<Genome<T>> genome, double rate);
}
