package zc.commons.pojo;

public class TMemeberProjectFollow {
    private Integer id;

    private Integer projectid;

    private Integer memberid;

    public TMemeberProjectFollow(Integer id, Integer projectid, Integer memberid) {
        this.id = id;
        this.projectid = projectid;
        this.memberid = memberid;
    }

    public TMemeberProjectFollow() {
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