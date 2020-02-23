package zc.commons.pojo;

public class TMemberProjectFollow {
    private Integer id;

    private Integer projectid;

    private Integer memberid;

    public TMemberProjectFollow(Integer id, Integer projectid, Integer memberid) {
        this.id = id;
        this.projectid = projectid;
        this.memberid = memberid;
    }

    public TMemberProjectFollow() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProjectid() {
        return projectid;
    }

    public void setProjectid(Integer projectid) {
        this.projectid = projectid;
    }

    public Integer getMemberid() {
        return memberid;
    }

    public void setMemberid(Integer memberid) {
        this.memberid = memberid;
    }
}