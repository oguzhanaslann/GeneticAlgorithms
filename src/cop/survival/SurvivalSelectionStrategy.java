package cop.survival;

import cop.genome.Genome;

import java.util.List;

public interface SurvivalSelectionStrategy<T> {

    List<Genome<T>> selectSurvivors(List<Genome<T>> population);

}
