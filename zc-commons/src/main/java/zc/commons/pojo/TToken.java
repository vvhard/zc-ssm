package zc.commons.pojo;

public class TToken {
    private Integer id;

    private Integer userid;

    private String pwdToken;

    private String autoLoginToken;

    public TToken(Integer id, Integer userid, String pwdToken, String autoLoginToken) {
        this.id = id;
        this.userid = userid;
        this.pwdToken = pwdToken;
        this.autoLoginToken = autoLoginToken;
    }

    public TToken() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getPwdToken() {
        return pwdToken;
    }

    public void setPwdToken(String pwdToken) {
        this.pwdToken = pwdToken == null ? null : pwdToken.trim();
    }

    public String getAutoLoginToken() {
        return autoLoginToken;
    }

    public void setAutoLoginToken(String autoLoginToken) {
        this.autoLoginToken = autoLoginToken == null ? null : autoLoginToken.trim();
    }
}