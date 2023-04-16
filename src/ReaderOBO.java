import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReaderOBO {

    public static List<ReadOBO> read(String fileName) throws IOException {

        String line;

        BufferedReader fileData = new BufferedReader((new FileReader(fileName)));

        List<ReadOBO> records = new ArrayList<>();

        while ((line = fileData.readLine()) != null) {

            if (line.startsWith("[Term]")) {
                line = fileData.readLine();
                String id = "";
                String name = "";
                String namespace = "";
                List<String> is_a = new ArrayList<>();
                List<String> part_of = new ArrayList<>();
                List<String> regulate = new ArrayList<>();
                while (line != null && !line.isEmpty()) {

                    if (line.startsWith("id")) {
                        id = line.split(" ")[1];
                    } else if (line.startsWith("name:")) {
                        name = line.split(": ")[1];
                    } else if (line.startsWith("namespace")) {
                        namespace = line.split(": ")[1];
                    } else if (line.startsWith("is_a")) {
                        String go = line.split(" ")[1];
                        is_a.add(go);
                    } else if (line.startsWith("relationship: part_of")) {
                        String go = line.split(" ")[2];
                        part_of.add(go);
                    } else if (line.startsWith("relationship: regulate")) {
                        String go = line.split(" ")[2];
                        regulate.add(go);
                    }

                    line = fileData.readLine();
                }
                records.add(new ReadOBO(id, name, namespace, is_a, part_of, regulate));
            }
        }
//        for (
//                int k = 0; k < records.size(); k++) {
//            System.out.println("id" + records.get(k).id);
//            System.out.println("name" + records.get(k).name);
//            System.out.println("namespace" + records.get(k).namespace);
//            System.out.println("is_a" + records.get(k).is_a);
//            System.out.println("part_of" + records.get(k).part_of);
//            System.out.println("regulate" + records.get(k).regulates+"\n");
//        }
        return records;
    }
}
