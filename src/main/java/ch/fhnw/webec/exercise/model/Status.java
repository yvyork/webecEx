package ch.fhnw.webec.exercise.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Entity
public class Status {
    @Id
    private int statusId;

    @NotEmpty
    private String status;

    @OneToMany(mappedBy = "status")
    private List<Device> deviceList;

    public Status() {}

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
