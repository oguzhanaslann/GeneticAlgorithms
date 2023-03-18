package cop.survival;

import cop.genome.Genome;
import cop.selection.ElitismSelection;
import cop.selection.Selection;

import java.util.ArrayList;
import java.util.List;

public class COPSurvivalSelectionStrategy implements SurvivalSelectionStrategy<List<Byte>> {

    private final ElitismSelection<List<Byte>> elitismSelection;

    private final Selection<List<Byte>> nonElitismSelection;

    private final int totalSelectionCount;

    private final int elitismCount;


    public COPSurvivalSelectionStrategy(
            int totalSelectionCount,
            int elitismCount,
            Selection<List<Byte>> nonElitismSelection
    ) {
        this.nonElitismSelection = nonElitismSelection;
        this.totalSelectionCount = totalSelectionCount;
        this.elitismCount = elitismCount;
        elitismSelection = new ElitismSelection<>();
    }


    @Override
    public List<Genome<List<Byte>>> selectSurvivors(List<Genome<List<Byte>>> population) {

        ArrayList<Genome<List<Byte>>> copyPopulation = new ArrayList<>(population);

        ArrayList<Genome<List<Byte>>> newPopulation = new ArrayList<>();

        List<Genome<List<Byte>>> elites = selectWithElitism(copyPopulation);
        newPopulation.addAll(elites);

        // remove the elites from the population to avoid duplicates
        copyPopulation.removeAll(elites);

        newPopulation.addAll(selectWithoutElitism(copyPopulation));


        return newPopulation;
    }

    private List<Genome<List<Byte>>> selectWithElitism(List<Genome<List<Byte>>> population) {
        return elitismSelection.selectFrom(population,elitismCount);
    }

    private List<Genome<List<Byte>>> selectWithoutElitism(List<Genome<List<Byte>>> population) {
        return nonElitismSelection.selectFrom(population,(totalSelectionCount - elitismCount));
    }
}
