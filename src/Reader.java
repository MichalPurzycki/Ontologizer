import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class Reader {

    public Counter read(String fileName) throws IOException {
        String line;
        String fileGen, fileId;

        BufferedReader fileData = new BufferedReader(new FileReader(fileName));
        List<Record> records = new ArrayList<>();

        while ((line = fileData.readLine()) != null){               //wczytywanie danych

            if(!line.startsWith("!")) {                             //pominięcie linni z komentarzem
                StringTokenizer tokenizer = new StringTokenizer(line);

                tokenizer.nextToken();
                fileGen = tokenizer.nextToken();
                tokenizer.nextToken();
                fileId = tokenizer.nextToken();
               do {                                                 //pętla w celu zabezpieczenia przed pominięciem linni
                   if (fileId.startsWith("GO")) {
                       records.add(new Record(fileGen, fileId));
                   } else {
                       tokenizer.nextToken();
                   }
               }
                while (fileGen.equals(null) ||  fileId.equals(null));

            }
        }

        fileData.close();
        int genSize = records.size();
        Map<String, List<String>> mapId = records.stream().collect(Collectors.groupingBy(Record::getId, Collectors.mapping(Record::getGen, Collectors.toList())));


        return new Counter(genSize, mapId);
    }

    public List<String> readAnnotations(String fileName) throws IOException {               //wczytywanie plików z jedną kolumną
        String line;
        BufferedReader fileAnnotations = new BufferedReader(new FileReader(fileName));
        List<String> records = new ArrayList<>();

        while ((line = fileAnnotations.readLine()) != null){
            records.add(line);
        }
        return records;

    }


}
