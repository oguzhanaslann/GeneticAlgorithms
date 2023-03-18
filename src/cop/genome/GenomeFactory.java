package cop.genome;

public interface GenomeFactory<T> {
    Genome<T> createGenome(T genome);
}
