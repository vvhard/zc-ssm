package zc.commons.pojo;

public class TOrder {
    private Integer id;

    private Integer memberid;

    private Integer projectid;

    private Integer returnid;

    private String ordernum;

    private String createdate;

    private double price;

    private Integer money;

    private Integer count;

    private String status;

    private String receiptname;

    private String tel;

    private String address;

    private String invoice;

    private String invoictitle;

    private String remark;

    public TOrder(Integer id, Integer memberid, Integer projectid, Integer returnid, String ordernum,
                  String createdate, double price, Integer money, Integer count, String status,
                  String receiptname, String tel, String address, String invoice, String invoictitle,
                  String remark) {
        this.id = id;
        this.memberid = memberid;
        this.projectid = projectid;
        this.returnid = returnid;
        this.ordernum = ordernum;
        this.createdate = createdate;
        this.price = price;
        this.money = money;
        this.count = count;
        this.status = status;
        this.receiptname = receiptname;
        this.tel = tel;
        this.address = address;
        this.invoice = invoice;
        this.invoictitle = invoictitle;
        this.remark = remark;
    }

    public TOrder() {
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

    public Integer getProjectid() {
        return projectid;
    }

    public void setProjectid(Integer projectid) {
        this.projectid = projectid;
    }

    public Integer getReturnid() {
        return returnid;
    }

    public void setReturnid(Integer returnid) {
        this.returnid = returnid;
    }

    public String getOrdernum() {
        return ordernum;
    }

    public void setOrdernum(String ordernum) {
        this.ordernum = ordernum == null ? null : ordernum.trim();
    }

    public String getCreatedate() {
        return createdate;
    }

    public void setCreatedate(String createdate) {
        this.createdate = createdate == null ? null : createdate.trim();
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

    public String getReceiptname() {
        return receiptname;
    }

    public void setReceiptname(String receiptname) {
        this.receiptname = receiptname;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getInvoice() {
        return invoice;
    }

    public void setInvoice(String invoice) {
        this.invoice = invoice == null ? null : invoice.trim();
    }

    public String getInvoictitle() {
        return invoictitle;
    }

    public void setInvoictitle(String invoictitle) {
        this.invoictitle = invoictitle == null ? null : invoictitle.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}