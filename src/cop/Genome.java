package cop;

public interface Genome<T> {

    T getGenome();

    double getFitness();

    void setFitness(double fitness);

}
