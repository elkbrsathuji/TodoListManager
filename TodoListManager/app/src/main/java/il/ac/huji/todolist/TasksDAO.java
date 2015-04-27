package il.ac.huji.todolist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import org.w3c.dom.Comment;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Android- on 4/27/2015.
 */
public class TasksDAO {


    // Database fields
    private SQLiteDatabase database;
    private DBHelper dbHelper;
    private String[] allColumns = { DBHelper.COLUMN_ID,
            DBHelper.COLUMN_TITLE, DBHelper.COLUMN_DUE };

    public TasksDAO(Context context) {
        dbHelper = new DBHelper(context);
    }

    public void open()  {
        try {
            database = dbHelper.getWritableDatabase();
        }catch (Exception e){
            e.printStackTrace();
        }
        }

    public void close() {
        dbHelper.close();
    }

    public Task createTask(Task task) {
        ContentValues values = new ContentValues();
        values.put(DBHelper.COLUMN_TITLE, task.getTitle());
        values.put(DBHelper.COLUMN_DUE,task.getDueDate().getTime());
        long insertId = database.insert(DBHelper.TABLE_NAME, null,
                values);
        Cursor cursor = database.query(DBHelper.TABLE_NAME,
                allColumns, DBHelper.COLUMN_ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        Task newTask = cursorToComment(cursor);
        cursor.close();
        return newTask;
    }

    public void deleteTask(Task task) {
        long id = task.getId();
//        System.out.println("Task deleted with id: " + id);
        database.delete(DBHelper.TABLE_NAME, DBHelper.COLUMN_ID
                + " = " + id, null);
    }

    public ArrayList<Task> getAllTasks() {
        ArrayList<Task> tasks = new ArrayList<Task>();

        Cursor cursor = database.query(DBHelper.TABLE_NAME,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Task task = cursorToComment(cursor);
            tasks.add(task);
            cursor.moveToNext();
        }
        cursor.close();
        return tasks;
    }

    private Task cursorToComment(Cursor cursor) {
        Task task = new Task();
        task.setId(cursor.getLong(0));
        task.setTitle(cursor.getString(1));
        task.setDueDate(new Date(cursor.getLong(2)));
        return task;
    }
}
