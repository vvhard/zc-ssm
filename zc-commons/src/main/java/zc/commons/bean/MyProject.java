package zc.commons.bean;

import zc.commons.pojo.TProject;
import zc.commons.util.DateUtil;

import java.util.Date;

public class MyProject {


    // 项目名称
    private int project_id;

    private String project_name;

    private String remark;

    private Long money;

    private Integer day;

    private String status;

    private String deploy_date;

    private Long support_money;

    private Integer supporter;

    private Integer completion;

    private Integer initiator_id;

    private String create_date;

    private Integer follower;

    private String contact;

    private String initiator_name;

    private String initiator_desc;

    private String project_head_pic;
    private String project_detail_pic;
    // 剩余时间
    private int remaining_day;

    //java.lang.Integer, java.lang.String, java.lang.String, java.lang.Long, java.lang.Integer,
    // java.lang.String, java.lang.String, java.lang.Long, java.lang.Integer, java.lang.Integer,
    // java.lang.Integer, java.lang.String, java.lang.Integer, java.lang.String, java.lang.String,
    // java.lang.String
    public MyProject(int project_id, String project_name, String remark, Long money, Integer day, String status,
                     String deploy_date, Long support_money, Integer supporter, Integer completion,
                     Integer initiator_id, String create_date, Integer follower, String contact, String initiator_name,
                     String initiator_desc,String project_head_pic,String project_detail_pic) {
        this.project_id = project_id;
        this.project_name = project_name;
        this.remark = remark;
        this.money = money;
        this.day = day;
        this.status = status;
        this.deploy_date = deploy_date;
        this.support_money = support_money;
        this.supporter = supporter;
        this.completion = completion;
        this.initiator_id = initiator_id;
        this.create_date = create_date;
        this.follower = follower;
        this.contact = contact;
        this.initiator_name = initiator_name;
        this.initiator_desc = initiator_desc;
        this.project_detail_pic = project_detail_pic;
        this.project_head_pic = project_head_pic;
        setRemaining_day();
    }

    public int getProject_id() {
        return project_id;
    }

    public void setProject_id(int project_id) {
        this.project_id = project_id;
    }

    public String getProject_name() {
        return project_name;
    }

    public void setProject_name(String project_name) {
        this.project_name = project_name;
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

    public String getDeploy_date() {
        return deploy_date;
    }

    public void setDeploy_date(String deploy_date) {
        this.deploy_date = deploy_date;
    }

    public Long getSupport_money() {
        return support_money;
    }

    public void setSupport_money(Long support_money) {
        this.support_money = support_money;
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

    public Integer getInitiator_id() {
        return initiator_id;
    }

    public void setInitiator_id(Integer initiator_id) {
        this.initiator_id = initiator_id;
    }

    public String getCreate_date() {
        return create_date;
    }

    public void setCreate_date(String create_date) {
        this.create_date = create_date;
    }

    public Integer getFollower() {
        return follower;
    }

    public void setFollower(Integer follower) {
        this.follower = follower;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getInitiator_name() {
        return initiator_name;
    }

    public void setInitiator_name(String initiator_name) {
        this.initiator_name = initiator_name;
    }

    public String getInitiator_desc() {
        return initiator_desc;
    }

    public void setInitiator_desc(String initiator_desc) {
        this.initiator_desc = initiator_desc;
    }


    public int getRemaining_day() {
        return remaining_day;
    }

    public void setRemaining_day() {
        this.remaining_day = 10 - DateUtil.calculate(this.deploy_date);
    }

    public String getProject_head_pic() {
        return project_head_pic;
    }

    public void setProject_head_pic(String project_head_pic) {
        this.project_head_pic = project_head_pic;
    }

    public String getProject_detail_pic() {
        return project_detail_pic;
    }

    public void setProject_detail_pic(String project_detail_pic) {
        this.project_detail_pic = project_detail_pic;
    }
}
