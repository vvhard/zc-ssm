package zc.commons.bean;

public class MyOrder {
    private Integer id;
    private Integer member_id;
    private int project_id;
    private Integer return_id;
    private String order_num;
    private String create_date;
    private double price;
    private Integer money;
    private Integer count;
    private String remark;
    private String status;
    private String receipt_name;
    private String tel;
    private String address;
    private String invoice;
    private String invoic_title;
    private String content;
    private Integer rtncount;
    private Integer freight;
    private String project_name;
    private String realname;
    // java.lang.String,java.lang.String, java.lang.String, java.lang.Integer, java.lang.String, java.lang.Integer
    public MyOrder(Integer id, Integer member_id, int project_id, Integer return_id, String order_num,
                   String create_date, double price, Integer money, Integer count, String remark,
                   String status, String receipt_name, String tel, String address, String invoice,
                   String invoic_title, String content, Integer rtncount, Integer freight, String project_name,
                   String realname) {
        this.id = id;
        this.member_id = member_id;
        this.project_id = project_id;
        this.return_id = return_id;
        this.order_num = order_num;
        this.create_date = create_date;
        this.price = price;
        this.money = money;
        this.count = count;
        this.remark = remark;
        this.status = status;
        this.receipt_name = receipt_name;
        this.tel = tel;
        this.address = address;
        this.invoice = invoice;
        this.invoic_title = invoic_title;
        this.content = content;
        this.rtncount = rtncount;
        this.freight = freight;
        this.project_name = project_name;
        this.realname = realname;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMember_id() {
        return member_id;
    }

    public void setMember_id(Integer member_id) {
        this.member_id = member_id;
    }

    public int getProject_id() {
        return project_id;
    }

    public void setProject_id(int project_id) {
        this.project_id = project_id;
    }

    public Integer getReturn_id() {
        return return_id;
    }

    public void setReturn_id(Integer return_id) {
        this.return_id = return_id;
    }

    public String getOrder_num() {
        return order_num;
    }

    public void setOrder_num(String order_num) {
        this.order_num = order_num;
    }

    public String getCreate_date() {
        return create_date;
    }

    public void setCreate_date(String create_date) {
        this.create_date = create_date;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReceipt_name() {
        return receipt_name;
    }

    public void setReceipt_name(String receipt_name) {
        this.receipt_name = receipt_name;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getInvoice() {
        return invoice;
    }

    public void setInvoice(String invoice) {
        this.invoice = invoice;
    }

    public String getInvoic_title() {
        return invoic_title;
    }

    public void setInvoic_title(String invoic_title) {
        this.invoic_title = invoic_title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getRtncount() {
        return rtncount;
    }

    public void setRtncount(Integer rtncount) {
        this.rtncount = rtncount;
    }

    public Integer getFreight() {
        return freight;
    }

    public void setFreight(Integer freight) {
        this.freight = freight;
    }

    public String getProject_name() {
        return project_name;
    }

    public void setProject_name(String project_name) {
        this.project_name = project_name;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }
}
