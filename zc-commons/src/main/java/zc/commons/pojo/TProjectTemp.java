package zc.commons.pojo;

public class TProjectTemp {
    private Integer id;

    private Integer memberid;

    // 新加的membername，数据库没有此字段
    private String membername;

    private String name;

    private String type;

    private String remark;

    private Long money;

    private Integer day;

    private String status;

    private String createdate;

    private String headpicpath;

    private String detailpicpath;

    private String contact;

    private String appid;

    private String alipaypublickey;

    private String appprivatekey;

    private String feedback;

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getAlipaypublickey() {
        return alipaypublickey;
    }

    public void setAlipaypublickey(String alipaypublickey) {
        this.alipaypublickey = alipaypublickey;
    }

    public String getAppprivatekey() {
        return appprivatekey;
    }

    public void setAppprivatekey(String appprivatekey) {
        this.appprivatekey = appprivatekey;
    }

    public TProjectTemp() {
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Long getMoney() {
        return money;
    }

    public void setMoney(Long money) {
        this.money = money;
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getCreatedate() {
        return createdate;
    }

    public void setCreatedate(String createdate) {
        this.createdate = createdate == null ? null : createdate.trim();
    }

    public String getHeadpicpath() {
        return headpicpath;
    }

    public void setHeadpicpath(String headpicpath) {
        this.headpicpath = headpicpath == null ? null : headpicpath.trim();
    }

    public String getDetailpicpath() {
        return detailpicpath;
    }

    public void setDetailpicpath(String detailpicpath) {
        this.detailpicpath = detailpicpath == null ? null : detailpicpath.trim();
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact == null ? null : contact.trim();
    }

    public String getMembername() {
        return membername;
    }

    public void setMebername(String membername) {
        this.membername = membername;
    }

    public void setMembername(String membername) {
        this.membername = membername;
    }

    @Override
    public String toString() {
        return "TProjectTemp{" +
                "id=" + id +
                ", memberid=" + memberid +
                ", membername='" + membername + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", remark='" + remark + '\'' +
                ", money=" + money +
                ", day=" + day +
                ", status='" + status + '\'' +
                ", createdate='" + createdate + '\'' +
                ", headpicpath='" + headpicpath + '\'' +
                ", detailpicpath='" + detailpicpath + '\'' +
                ", contact='" + contact + '\'' +
                ", appid='" + appid + '\'' +
                ", alipaypublickey='" + alipaypublickey + '\'' +
                ", appprivatekey='" + appprivatekey + '\'' +
                ", feedback='" + feedback + '\'' +
                '}';
    }
}