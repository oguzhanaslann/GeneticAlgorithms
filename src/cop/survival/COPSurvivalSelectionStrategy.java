package cop.survival;

import cop.genome.Genome;
import cop.selection.ElitismSelection;
import cop.selection.Selection;

import java.util.ArrayList;
import java.util.List;

public class COPSurvivalSelectionStrategy implements SurvivalSelectionStrategy<Byte[]> {

    private final ElitismSelection<Byte[]> elitismSelection;

    private final Selection<Byte[]> nonElitismSelection;

    public COPSurvivalSelectionStrategy(int elitismSelectionSize, Selection<Byte[]> nonElitismSelection) {
        elitismSelection = new ElitismSelection<>(elitismSelectionSize);
        this.nonElitismSelection = nonElitismSelection;
    }


    @Override
    public List<Genome<Byte[]>> selectSurvivors(List<Genome<Byte[]>> population) {

        ArrayList<Genome<Byte[]>> copyPopulation = new ArrayList<>(population);

        ArrayList<Genome<Byte[]>> newPopulation = new ArrayList<>();

        List<Genome<Byte[]>> elites = selectWithElitism(copyPopulation);
        newPopulation.addAll(elites);

        // remove the elites from the population to avoid duplicates
        copyPopulation.removeAll(elites);

        newPopulation.addAll(selectWithoutElitism(copyPopulation));


        return newPopulation;
    }

    private List<Genome<Byte[]>> selectWithElitism(List<Genome<Byte[]>> population) {
        return elitismSelection.selectFrom(population);
    }

    private List<Genome<Byte[]>> selectWithoutElitism(List<Genome<Byte[]>> population) {
        return nonElitismSelection.selectFrom(population);
    }
}
