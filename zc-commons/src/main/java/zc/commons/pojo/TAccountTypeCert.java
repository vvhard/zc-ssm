package zc.commons.pojo;

public class TAccountTypeCert {
    private Integer id;

    private Integer accttypeid;

    private Integer certid;

    public TAccountTypeCert(Integer id, Integer accttypeid, Integer certid) {
        this.id = id;
        this.accttypeid = accttypeid;
        this.certid = certid;
    }

    public TAccountTypeCert() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAccttypeid() {
        return accttypeid;
    }

    public void setAccttypeid(Integer accttypeid) {
        this.accttypeid = accttypeid;
    }

    public Integer getCertid() {
        return certid;
    }

    public void setCertid(Integer certid) {
        this.certid = certid;
    }
}