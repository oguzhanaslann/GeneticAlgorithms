package cop.genome;

import cop.fitness.COPFitnessCalculator;
import cop.fitness.FitnessCalculator;

import java.util.ArrayList;
import java.util.Collections;

public class COPGenome implements Genome<Byte[]> {

    private final Byte[] genome;

    private double fitness;

    public COPGenome(Byte[] genome) {
        this.genome = genome;
    }

    public COPGenome(int genomeSize) {
        this.genome = new Byte[genomeSize];
        randomlyFillGenomeWithBits();
    }

    public COPGenome(int genomeSize, FitnessCalculator<Byte[]> calculator) {
        this.genome = new Byte[genomeSize];
        randomlyFillGenomeWithBits();
        calculateAndSetFitnessBy(calculator);
    }

    public COPGenome(Byte[] genome, FitnessCalculator<Byte[]> calculator) {
        this.genome = genome;
        calculateAndSetFitnessBy(calculator);
    }

    public static void main(String[] args) {
        COPFitnessCalculator calculator = new COPFitnessCalculator();
        ArrayList<Genome<Byte[]>> genomes = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            COPGenome genome = new COPGenome(10);
            genome.calculateFitness(calculator);
            System.out.println(genome);
            genomes.add(genome);
        }

        // sort genomes by fitness
        Collections.sort(genomes);
        System.out.println("Sorted genomes:");
        for (Genome<Byte[]> genome : genomes) {
            System.out.println(genome);
        }
    }

    private void calculateAndSetFitnessBy(FitnessCalculator<Byte[]> calculator) {
        int calculatedFitness = calculator.calculateFitnessOf(this);
        setFitness(calculatedFitness);
    }

    private void randomlyFillGenomeWithBits() {
        for (int i = 0; i < genome.length; i++) {
            genome[i] = getRandomBit();
        }
    }

    private byte getRandomBit() {
        return (byte) (Math.random() < 0.5 ? 0 : 1);
    }

    @Override
    public Byte[] getGenome() {
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
    public void calculateFitness(FitnessCalculator<Byte[]> calculator) {
        calculateAndSetFitnessBy(calculator);
    }

    @Override
    public int compareTo(Genome<Byte[]> o) {
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

