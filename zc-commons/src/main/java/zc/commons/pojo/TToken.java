package zc.commons.pojo;

public class TToken {
    private Integer id;

    private Integer userid;

    private String pwdToken;

    private String autoLoginToken;

    private String expireToken;

    private String ssoToken;

    public TToken(Integer id, Integer userid, String pwdToken, String autoLoginToken, String expireToken, String ssoToken) {
        this.id = id;
        this.userid = userid;
        this.pwdToken = pwdToken;
        this.autoLoginToken = autoLoginToken;
        this.expireToken = expireToken;
        this.ssoToken = ssoToken;
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

    public String getExpireToken() {
        return expireToken;
    }

    public void setExpireToken(String expireToken) {
        this.expireToken = expireToken == null ? null : expireToken.trim();
    }

    public String getSsoToken() {
        return ssoToken;
    }

    public void setSsoToken(String ssoToken) {
        this.ssoToken = ssoToken == null ? null : ssoToken.trim();
    }
}