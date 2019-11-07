package zc.commons.pojo;

public class TRole {
    private Integer id;

    private String name;

    private String description;

    public TRole(Integer id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public TRole() {
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