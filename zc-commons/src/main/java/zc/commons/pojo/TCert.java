package zc.commons.pojo;

public class TCert {
    private Integer id;

    private String name;

    public TCert(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public TCert() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }
}