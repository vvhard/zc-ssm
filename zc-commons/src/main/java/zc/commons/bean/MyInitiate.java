package zc.commons.bean;

import zc.commons.util.DateUtil;

import java.util.Date;

public class MyInitiate {
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
    private String contact;

    private int remaining_day;

    public MyInitiate(Integer id, String name, String remark, Long money, Integer day,
                    String status, String deploydate, Long supportmoney, Integer supporter,
                    Integer completion, Integer memberid, String createdate, Integer follower,String contact) {
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
        this.contact = contact;
        setRemainingDay();
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
        this.name = name;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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
        this.status = status;
    }

    public String getDeploydate() {
        return deploydate;
    }

    public void setDeploydate(String deploydate) {
        this.deploydate = deploydate;
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
        this.createdate = createdate;
    }

    public Integer getFollower() {
        return follower;
    }

    public void setFollower(Integer follower) {
        this.follower = follower;
    }
    public int getRemainingDay() {
        return remaining_day;
    }
    public void setRemainingDay() {
        Date date = DateUtil.parseDate(deploydate);
        Date curDate = DateUtil.parseDate(DateUtil.currentDate());
        long sub = Math.abs(curDate.getTime() - date.getTime()); // 剩余毫秒数
        this.remaining_day =this.day- (int)(sub/1000/60/60/24);

    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
}
