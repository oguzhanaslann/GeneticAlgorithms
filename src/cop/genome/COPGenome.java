package cop.genome;

import cop.fitness.COPFitnessCalculator;
import cop.fitness.FitnessCalculator;
import cop.selection.ElitismSelection;
import cop.selection.RouletteWheelSelection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class COPGenome implements Genome<List<Byte>> {

    private final List<Byte> genome;

    private double fitness;

    public COPGenome(int genomeSize) {
        this.genome = new ArrayList<>(genomeSize);
        for (int i = 0; i < genomeSize; i++) {
            genome.add((byte) 0);
        }
        randomlyFillGenomeWithBits();
    }

    public COPGenome(List<Byte> genome) {
        this.genome = genome;
    }

    private void randomlyFillGenomeWithBits() {
        for (int i = 0; i < genome.size(); i++) {
            genome.set(i, getRandomBit());
        }
    }

    private byte getRandomBit() {
        return (byte) (Math.random() < 0.5 ? 0 : 1);
    }

    @Override
    public List<Byte> getGenome() {
        return genome;
    }

    @Override
    public double getFitness() {
        return fitness;
    }

    private void setFitness(int fitness) {
        this.fitness = fitness;
    }

    @Override
    public void calculateFitness(FitnessCalculator<List<Byte>> calculator) {
        calculateAndSetFitnessBy(calculator);
    }

    private void calculateAndSetFitnessBy(FitnessCalculator<List<Byte>> calculator) {
        int calculatedFitness = calculator.calculateFitnessOf(this);
        setFitness(calculatedFitness);
    }

    @Override
    public int compareTo(Genome<List<Byte>> o) {
        return Double.compare(this.getFitness(), o.getFitness());
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("COPGenome [ genome = ");
        for (Byte gene : genome) {
            builder.append(gene);
        }
        builder.append(", fitness = ");
        builder.append(fitness);
        builder.append(" ]");
        return builder.toString();
    }
}

