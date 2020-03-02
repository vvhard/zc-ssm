package zc.commons.pojo;

public class TStartProjectTask {
    private Integer id;

    private Integer taskid;

    private Integer projecttempid;

    private Integer returntempid;

    private String status;


    public TStartProjectTask() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTaskid() {
        return taskid;
    }

    public void setTaskid(Integer taskid) {
        this.taskid = taskid;
    }

    public Integer getProjecttempid() {
        return projecttempid;
    }

    public void setProjecttempid(Integer projecttempid) {
        this.projecttempid = projecttempid;
    }

    public Integer getReturntempid() {
        return returntempid;
    }

    public void setReturntempid(Integer returntempid) {
        this.returntempid = returntempid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}