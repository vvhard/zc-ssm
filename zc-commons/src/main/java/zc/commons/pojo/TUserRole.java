package zc.commons.pojo;

public class TUserRole {
    private Integer id;

    private Integer userid;

    private Integer roleid;

    private String description;

    public TUserRole(Integer id, Integer userid, Integer roleid, String description) {
        this.id = id;
        this.userid = userid;
        this.roleid = roleid;
        this.description = description;
    }

    public TUserRole() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getRoleid() {
        return roleid;
    }

    public void setRoleid(Integer roleid) {
        this.roleid = roleid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }
}