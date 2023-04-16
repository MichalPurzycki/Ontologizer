import java.util.List;
import java.util.Map;

public class Counter {   //konstruktor dotyczący hashmapy

    int size;
    Map<String, List<String>> records;

    public Counter(int size, Map<String, List<String>> records){
        this.size = size;
        this.records = records;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Map<String, List<String>> getRecords() {
        return records;
    }

    public void setRecords(Map<String, List<String>> records) {
        this.records = records;
    }



    }