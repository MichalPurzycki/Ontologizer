import java.util.List;

public class ReadOBO { //konstruktor dotyczący dancyh o genach

    String id;
    String name;
    String namespace;
    List<String> is_a;
    List<String> part_of;
    List<String> regulates;

    public ReadOBO(String id, String name, String namespace, List<String> is_a, List<String> part_of, List<String> regulates){
        this.id = id;
        this.name = name;
        this.namespace = namespace;
        this.is_a = is_a;
        this.part_of = part_of;
        this.regulates = regulates;
    }
}
