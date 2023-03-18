package cop;

public class COPGenome implements Genome<Byte[]>  {

    private final Byte[] genome;

    private double fitness;

    public COPGenome(Byte[] genome) {
        this.genome = genome;
    }

    @Override
    public Byte[] getGenome() {
        return genome;
    }

    @Override
    public double getFitness() {
        return fitness;
    }

    @Override
    public void setFitness(double fitness) {
        this.fitness = fitness;
    }
}
