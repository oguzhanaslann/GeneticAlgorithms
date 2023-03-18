package cop;

import cop.experiment.CopExperiment;
import cop.experiment.Experiment;
import cop.genome.Genome;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Playground {

    public static final double CROSS_OVER_RATE = 0.7;
    public static final double MUTATION_RATE = 0.3;

    public static void main(String[] args) {

        CopExperiment problem1 = getProblem1();
        CopExperiment problem2 = getProblem2();
        CopExperiment problem3 = getProblem3();
        CopExperiment problem4 = getProblem4();

        List<Experiment<List<Byte>>> experiments = Arrays.asList(problem1, problem2, problem3, problem4);

        for (Experiment<List<Byte>> experiment : experiments) {
            System.out.println("----------------------------------------------------");
            System.out.println("Experiment: " + experiment.getName());
            int totalFitness = 0;
            ArrayList<Genome<List<Byte>>> bestGenomes = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                System.out.println("Run: " + (i + 1));
                experiment.run();
                System.out.println("Best genome: " + experiment.getBestGenome());
                totalFitness += experiment.getBestGenome().getFitness();
                bestGenomes.add(experiment.getBestGenome());
            }

            System.out.println("Average fitness: " + (totalFitness / 10d));
            System.out.println("Best genomes: ");
            for (Genome<List<Byte>> bestGenome : bestGenomes) {
                System.out.println(bestGenome);
            }
            System.out.println("----------------------------------------------------");
            System.out.println();
        }
    }

    private static CopExperiment getProblem1() {
        CopExperiment experiment = new CopExperiment(10, 10, 10, CROSS_OVER_RATE, MUTATION_RATE);
        experiment.setName("Problem- 1");
        return experiment;
    }

    private static CopExperiment getProblem2() {
        CopExperiment experiment = new CopExperiment(100, 100, 100, CROSS_OVER_RATE, MUTATION_RATE);
        experiment.setName("Problem- 2");
        return experiment;
    }

    private static CopExperiment getProblem3() {
        CopExperiment experiment = new CopExperiment(100, 100, 1000, CROSS_OVER_RATE, MUTATION_RATE);
        experiment.setName("Problem- 3");
        return experiment;
    }

    private static CopExperiment getProblem4() {
        CopExperiment experiment = new CopExperiment(100, 1000, 100, CROSS_OVER_RATE, MUTATION_RATE);
        experiment.setName("Problem- 4");
        return experiment;
    }
}
