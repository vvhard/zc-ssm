package zc.commons.pojo;

public class TProjectTemp {
    private Integer id;

    private Integer memberid;

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

    public TProjectTemp(Integer id, Integer memberid, String name, String type, String remark, Long money, Integer day, String status, String createdate, String headpicpath, String detailpicpath, String contact) {
        this.id = id;
        this.memberid = memberid;
        this.name = name;
        this.type = type;
        this.remark = remark;
        this.money = money;
        this.day = day;
        this.status = status;
        this.createdate = createdate;
        this.headpicpath = headpicpath;
        this.detailpicpath = detailpicpath;
        this.contact = contact;
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
}