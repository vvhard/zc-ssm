package zc.commons.pojo;

public class TMemberAddress {
    private Integer id;

    private Integer memberid;

    private String name;

    private String tel;

    private String address;

    public TMemberAddress(Integer id, Integer memberid, String name, String tel, String address) {
        this.id = id;
        this.memberid = memberid;
        this.name = name;
        this.tel = tel;
        this.address = address;
    }

    public TMemberAddress() {
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }
}