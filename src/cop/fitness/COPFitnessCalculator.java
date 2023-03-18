package cop.fitness;

import cop.genome.Genome;

import java.util.List;

public class COPFitnessCalculator implements FitnessCalculator<List<Byte>> {

    public static final int ONES = 1;
    public static final int ZEROS = 0;

    private final int bitToCount;

    public COPFitnessCalculator() {
        this.bitToCount = ONES;
    }

    public COPFitnessCalculator(int bitToCount) {
        this.bitToCount = bitToCount;
    }

    @Override
    public int calculateFitnessOf(Genome<List<Byte>> genome) {
        List<Byte> genes = genome.getGenome();

        int largestAmounthOfConsecutiveOnes = 0;
        int currentConsecutiveOnes = 0;

        for (Byte gene : genes) {
            if (gene == bitToCount) {
                currentConsecutiveOnes++;
            } else {
                boolean isLargestChanged = isLargestChanged(largestAmounthOfConsecutiveOnes, currentConsecutiveOnes);
                if (isLargestChanged) {
                    largestAmounthOfConsecutiveOnes = currentConsecutiveOnes;
                }
                currentConsecutiveOnes = 0;
            }
        }

        boolean isLargestChanged = isLargestChanged(largestAmounthOfConsecutiveOnes, currentConsecutiveOnes);
        if (isLargestChanged) {
            largestAmounthOfConsecutiveOnes = currentConsecutiveOnes;
        }

        return largestAmounthOfConsecutiveOnes;
    }

    private boolean isLargestChanged(int largestAmounthOfConsecutiveOnes, int currentConsecutiveOnes) {
        return currentConsecutiveOnes > largestAmounthOfConsecutiveOnes;
    }
}
