import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {


        Reader reader = new Reader();
        PrintWriter writer = new PrintWriter("results.txt");                           //stworzenie pliku z wynikami

        Counter ontology = reader.read("gene_association.goa_human");           //wczytanie ontologii
        for (Object key : ontology.records.keySet()) {
            String go = (String) key;

            List<String> n = reader.readAnnotations("human_population.ppm");    //wczytanie populacji
            List<String> m = reader.readAnnotations("peptydom.txt");            //wczytanie wytypowanych genów
            List<String> goGens = ontology.records.get(go);                              //wybranie genów związanych z danym procesem

            List<String> nt = n.stream()                                                 //wyliczenie liczby genów związanych z danym procesem biologicznym spośród całej populacji
                    .filter(goGens::contains)
                    .collect(Collectors.toList());

            if (nt.size() != 0) {
                Probability probability = new Probability();

                Probability newProbability =probability.countProb(n,nt,m);               //wyliczanie prawdopodobieństwa
                if (newProbability.processChoosesGens > 0)
                writer.println(go+"\nN = "+n.size()+"\nNt = "+nt.size()+"\nM = "+m.size()+"\nMt = "+newProbability.processChoosesGens+"\nprobability = "+newProbability.probability+"\nNull Hypothesis = "+newProbability.nullHypothesis+"\n\n");

            }
        }

        writer.close();

        PrintWriter writerOBO = new PrintWriter("resultsOBO.txt");                //wczytanie ontologii obo
        ReaderOBO readerOBO = new ReaderOBO();
        readerOBO.read("gene_ontology_ext.obo").stream().forEach( (k) -> writerOBO.println("id: "+k.id+"\nname: "+k.name+"\nnamespace: "+k.namespace+"\nis_a "+k.is_a+"\npart_of  "+k.part_of+"\nregulate "+k.regulates+"\n")); //wczytanie do pliku danych o białkach

        writerOBO.close();


    }



}

