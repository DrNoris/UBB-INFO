package Task;


import java.util.Objects;

public abstract class Task{
    private String taskId;
    private String descriere;

    public Task(String id, String desc){
        this.taskId = id;
        this.descriere = desc;
    }

    protected Task() {
    }

    public String getTaskId(){
        return this.taskId;
    }

    public String getDescriere() {
        return this.descriere;
    }

    public void setTaskId(String id) {
        this.taskId = id;
    }

    public void setDescriere(String desc) {
        this.descriere = desc;
    }

    public abstract void execute();

    public String toString() {
        return "id=" + getTaskId() + " | descriere=" + getDescriere();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj != null && this.getClass() == obj.getClass()) {
            Task task = (Task)obj;
            return Objects.equals(this.taskId, task.taskId);
        } else {
            return false;
        }
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.taskId});
    }
}