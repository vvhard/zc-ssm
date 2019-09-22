package zc.commons.pojo;

public class TDictionary {
    private Integer id;

    private String name;

    private String code;

    private String subcode;

    private String val;

    public TDictionary(Integer id, String name, String code, String subcode, String val) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.subcode = subcode;
        this.val = val;
    }

    public TDictionary() {
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getSubcode() {
        return subcode;
    }

    public void setSubcode(String subcode) {
        this.subcode = subcode == null ? null : subcode.trim();
    }

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val == null ? null : val.trim();
    }
}