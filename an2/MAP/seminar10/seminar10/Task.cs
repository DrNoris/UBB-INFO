namespace seminar10;

public class Task
{
    public String taskID{get; set;}
    public String descriere{get; set;}
    
    public Task (String taskID, String descriere){
        this.taskID = taskID;
        this.descriere = descriere;
    }
}