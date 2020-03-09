package zc.commons.pojo;

import zc.commons.util.DateUtil;

import java.util.Date;

public class TProject {
    private Integer id;

    private String name;

    private String remark;

    private Long money;

    private Integer day;

    private String status;

    private String deploydate;

    private Long supportmoney;

    private Integer supporter;

    private Integer completion;

    private Integer memberid;

    private String createdate;

    private Integer follower;

    private String concat;

    private String headpicpath;

    private String detailpicpath;

    public TProject(Integer id, String name, String remark, Long money, Integer day, String status,
                    String deploydate, Long supportmoney, Integer supporter, Integer completion,
                    Integer memberid, String createdate, Integer follower,String concat,String headpicpath,
                    String detailpicpath) {
        this.id = id;
        this.name = name;
        this.remark = remark;
        this.money = money;
        this.day = day;
        this.status = status;
        this.deploydate = deploydate;
        this.supportmoney = supportmoney;
        this.supporter = supporter;
        this.completion = completion;
        this.memberid = memberid;
        this.createdate = createdate;
        this.follower = follower;
        this.concat = concat;
        this.headpicpath = headpicpath;
        this.detailpicpath = detailpicpath;
    }


    public TProject() {
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

    public String getDeploydate() {
        return deploydate;
    }

    public void setDeploydate(String deploydate) {
        this.deploydate = deploydate == null ? null : deploydate.trim();
    }

    public Long getSupportmoney() {
        return supportmoney;
    }

    public void setSupportmoney(Long supportmoney) {
        this.supportmoney = supportmoney;
    }

    public Integer getSupporter() {
        return supporter;
    }

    public void setSupporter(Integer supporter) {
        this.supporter = supporter;
    }

    public Integer getCompletion() {
        return completion;
    }

    public void setCompletion(Integer completion) {
        this.completion = completion;
    }

    public Integer getMemberid() {
        return memberid;
    }

    public void setMemberid(Integer memberid) {
        this.memberid = memberid;
    }

    public String getCreatedate() {
        return createdate;
    }

    public void setCreatedate(String createdate) {
        this.createdate = createdate == null ? null : createdate.trim();
    }

    public Integer getFollower() {
        return follower;
    }

    public void setFollower(Integer follower) {
        this.follower = follower;
    }

    public String getConcat() {
        return concat;
    }

    public void setConcat(String concat) {
        this.concat = concat;
    }

    public String getHeadpicpath() {
        return headpicpath;
    }

    public void setHeadpicpath(String headpicpath) {
        this.headpicpath = headpicpath;
    }

    public String getDetailpicpath() {
        return detailpicpath;
    }

    public void setDetailpicpath(String detailpicpath) {
        this.detailpicpath = detailpicpath;
    }

    @Override
    public String toString() {
        return "TProject{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", remark='" + remark + '\'' +
                ", money=" + money +
                ", day=" + day +
                ", status='" + status + '\'' +
                ", deploydate='" + deploydate + '\'' +
                ", supportmoney=" + supportmoney +
                ", supporter=" + supporter +
                ", completion=" + completion +
                ", memberid=" + memberid +
                ", createdate='" + createdate + '\'' +
                ", follower=" + follower +
                ", concat='" + concat + '\'' +
                ", headpicpath='" + headpicpath + '\'' +
                ", detailpicpath='" + detailpicpath + '\'' +
                '}';
    }
}