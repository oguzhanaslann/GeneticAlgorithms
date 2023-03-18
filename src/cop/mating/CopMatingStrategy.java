package cop.mating;

import cop.genome.Genome;
import cop.selection.RouletteWheelSelection;
import cop.selection.Selection;
import cop.util.Pair;

import java.util.List;

public class CopMatingStrategy implements  MatingStrategy<List<Byte>>{

    Selection<List<Byte>> selection;

    public CopMatingStrategy() {
        this.selection = new RouletteWheelSelection<>();
    }

    @Override
    public Pair<Genome<List<Byte>>, Genome<List<Byte>>> getMateFrom(List<Genome<List<Byte>>> population) {
        Genome<List<Byte>> pairFirst  = selection.selectFrom(population,1).get(0);
        Genome<List<Byte>> pairSecond = selection.selectFrom(population,1).get(0);
        return new Pair<>(pairFirst, pairSecond);
    }
}
