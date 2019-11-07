package zc.commons.pojo;

public class TCert {
    private Integer id;

    private String name;

    private String description;

    public TCert(Integer id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }
}