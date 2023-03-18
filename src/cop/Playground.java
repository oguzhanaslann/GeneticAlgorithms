package cop;

import cop.experiment.CopExperiment;
import cop.experiment.Experiment;

import java.util.Arrays;
import java.util.List;

public class Playground {
    public static void main(String[] args) {
        List<Experiment<Byte[]>> experiments = Arrays.asList(
                new CopExperiment(10, 10, 10 , 0.7, 0.3),
                new CopExperiment(100, 100, 100 , 0.7, 0.3),
                new CopExperiment(100, 100, 1000 , 0.7, 0.3),
                new CopExperiment(100, 1000, 100 , 0.7, 0.3)
        );

        for (Experiment<Byte[]> experiment : experiments) {
            for (int i = 0; i < 10; i++) {
                experiment.run();
            }
        }
    }
}
