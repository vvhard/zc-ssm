package zc.commons.bean;

public class ProjectPayInfo {
    private int id;
    private int projectid;
    private String appid;
    private String appprvatekey;
    private String alipaypublickey;

    public ProjectPayInfo(int id, int projectid, String appid, String appprvatekey, String alipaypublickey) {
        this.id = id;
        this.projectid = projectid;
        this.appid = appid;
        this.appprvatekey = appprvatekey;
        this.alipaypublickey = alipaypublickey;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProjectid() {
        return projectid;
    }

    public void setProjectid(int projectid) {
        this.projectid = projectid;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getAppprvatekey() {
        return appprvatekey;
    }

    public void setAppprvatekey(String appprvatekey) {
        this.appprvatekey = appprvatekey;
    }

    public String getAlipaypublickey() {
        return alipaypublickey;
    }

    public void setAlipaypublickey(String alipaypublickey) {
        this.alipaypublickey = alipaypublickey;
    }
}
