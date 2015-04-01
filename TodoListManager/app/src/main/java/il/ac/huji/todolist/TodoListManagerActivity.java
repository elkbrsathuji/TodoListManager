package il.ac.huji.todolist;

import android.app.Dialog;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;


public class TodoListManagerActivity extends ActionBarActivity {

    ListView mTodoList;
    EditText mAddTask;
    ArrayList<String> mTasks;
    TasksAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo_list_manager);

        mTodoList=(ListView)findViewById(R.id.lstTodoItems);
        mAddTask=(EditText)findViewById(R.id.edtNewItem);

mTasks=new ArrayList<String>();
       mAdapter  = new TasksAdapter(this,mTasks);
        mTodoList.setAdapter(mAdapter);

        mTodoList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
alertDeleteDialog(position);

                return false;
            }
        });
    }

    private void alertDeleteDialog(final int position){
        final Dialog dialog= new Dialog(this);
        dialog.setContentView(R.layout.delete_dialog);
        dialog.setTitle(mTasks.get(position));
        Button delBtn=(Button)dialog.findViewById(R.id.menuItemDelete);
        delBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTasks.remove(position);
                mAdapter.notifyDataSetChanged();
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_todo_list_manager, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_menu) {
            String newTask=mAddTask.getText().toString();
            mTasks.add(newTask);
            mAddTask.setText("");
            mAdapter.notifyDataSetChanged();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
