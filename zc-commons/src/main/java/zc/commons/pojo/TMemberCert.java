package zc.commons.pojo;

public class TMemberCert {
    private Integer id;

    private Integer memberid;

    private Integer certid;

    private String path;

    public TMemberCert(Integer id, Integer memberid, Integer certid, String path) {
        this.id = id;
        this.memberid = memberid;
        this.certid = certid;
        this.path = path;
    }

    public TMemberCert() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMemberid() {
        return memberid;
    }

    public void setMemberid(Integer memberid) {
        this.memberid = memberid;
    }

    public Integer getCertid() {
        return certid;
    }

    public void setCertid(Integer certid) {
        this.certid = certid;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path == null ? null : path.trim();
    }
}