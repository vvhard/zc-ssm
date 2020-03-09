package zc.commons.bean;

import zc.commons.util.DateUtil;

import java.util.Date;

public class MySupport {

    // 项目名称
    private int project_id;

    private String project_name;
    // 项目完成度
    private int completion;
    // 项目剩余时间
    private int day;

    private String deploydate;
    // projectid
    // 支持时间
    private String support_time;
    // 订单编号
    private String order_num;
    // ordernum
    // 支持金额，运费 money
    private int support_money;
    // 回报数量 rtncount
    private int return_count;
    // 交易状态 status
    private String order_status;
    // 剩余时间
    private int remaining_day;

    public MySupport(int project_id, String project_name, int completion, int day,
                     String deploydate, String support_time, String order_num,
                     int support_money, int return_count, String order_status) {
        this.project_id = project_id;
        this.project_name = project_name;
        this.completion = completion;
        this.day = day;
        this.deploydate = deploydate;
        this.support_time = support_time;
        this.order_num = order_num;
        this.support_money = support_money;
        this.return_count = return_count;
        this.order_status = order_status;
        setRemainingDay();
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

    public int getCompletion() {
        return completion;
    }

    public void setCompletion(int completion) {
        this.completion = completion;
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

    public String getSupport_time() {
        return support_time;
    }

    public void setSupport_time(String support_time) {
        this.support_time = support_time;
    }

    public String getOrder_num() {
        return order_num;
    }

    public void setOrder_num(String order_num) {
        this.order_num = order_num;
    }

    public int getSupport_money() {
        return support_money;
    }

    public void setSupport_money(int support_money) {
        this.support_money = support_money;
    }

    public int getReturn_count() {
        return return_count;
    }

    public void setReturn_count(int return_count) {
        this.return_count = return_count;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public String getDeploydate() {
        return deploydate;
    }

    public void setDeploydate(String deploydate) {
        this.deploydate = deploydate;
    }

    public String getOrder_status() {
        return order_status;
    }

    public void setOrder_status(String order_status) {
        this.order_status = order_status;
    }

    @Override
    public String toString() {
        return "MySupport{" +
                "project_id=" + project_id +
                ", project_name='" + project_name + '\'' +
                ", completion=" + completion +
                ", day=" + day +
                ", deploydate='" + deploydate + '\'' +
                ", support_time='" + support_time + '\'' +
                ", order_num='" + order_num + '\'' +
                ", support_money=" + support_money +
                ", return_count=" + return_count +
                ", order_status='" + order_status + '\'' +
                ", remaining_day=" + remaining_day +
                '}';
    }
}
