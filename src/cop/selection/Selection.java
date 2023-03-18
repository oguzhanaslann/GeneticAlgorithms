package cop.selection;

import cop.genome.Genome;

import java.util.List;

public interface Selection<T>{
    List<Genome<T>> selectFrom(List<Genome<T>> population, int count);
}
