package il.ac.huji.todolist;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Android- on 4/12/2015.
 */
public class Task {

  private  String title;
    private Date dueDate;


    public Task(String title,Date dueDate) {
     this.title=title;
        this.dueDate = dueDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public String getDateAsString(){
        SimpleDateFormat fmtOut = new SimpleDateFormat("dd/MM/yyyy");
        return fmtOut.format(this.dueDate);
    }
}
