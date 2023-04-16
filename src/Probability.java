import org.apache.commons.math3.distribution.HypergeometricDistribution;

import java.util.List;
import java.util.stream.Collectors;

public class Probability {                  //deklaracja zmiennych, dostęp ustawiony na public aby można było wywołać je do wypisania w innej klasie

    public double probability;
    public double nullHypothesis;
    public int numberOfGens;
    public int processGens;
    public int choosesGens;
    public int processChoosesGens;

    public Probability(){}

    public Probability(double probability, double nullHypothesis, int numberOfGens, int processGens, int choosesGens, int processChoosesGens) { //przypisanie danych do zmiennych
        this.probability = probability;
        this.nullHypothesis = nullHypothesis;
        this.numberOfGens = numberOfGens;
        this.processGens = processGens;
        this.choosesGens = choosesGens;
        this.processChoosesGens = processChoosesGens;
    }

    public Probability countProb(List<String> numberOfGens, List<String> processGens, List<String> choosesGens) {
            HypergeometricDistribution hydro = new HypergeometricDistribution(numberOfGens.size(), processGens.size(), choosesGens.size());

            List<String> processChoosesGens = choosesGens.stream()                                                      //wyliczenie liczby genów związanych z danym procesem biologicznym spośród zbioru wytypowanych
                    .filter(processGens::contains)
                    .collect(Collectors.toList());



            double probability = hydro.probability(processChoosesGens.size());                                          //wyliczenie prawdopodobieństwa - skorzystanie z biblioteki HypergeometricDistribution
            int i;
            double nullHypothesis = 0;
        if (processChoosesGens.size() > 0){
            for (i = 0; processChoosesGens.size() + i <= Math.min(processGens.size(), choosesGens.size()); i++) {       //obliczenie hipotezy zerowej
                int x = processChoosesGens.size() + i;
                double probabilityPlus = hydro.probability(x);

                nullHypothesis = nullHypothesis + probabilityPlus;
                }
            }
            //System.out.println(probability + "\n"+nullHypothesis);
            return new Probability(probability,nullHypothesis,numberOfGens.size(),processGens.size(),choosesGens.size(),processChoosesGens.size());

        }

}

