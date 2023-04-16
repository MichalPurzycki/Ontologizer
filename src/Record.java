public class Record {   //konstruktor dotyczący rekordów

    String gen;
    String id;

    public Record(String gen, String id){
        this.gen = gen;
        this.id = id;
    }

    public String getGen() {
        return gen;
    }

    public void setGen(String gen) {
        this.gen = gen;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
