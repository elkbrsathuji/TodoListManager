package il.ac.huji.todolist;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
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
import java.util.Date;


public class TodoListManagerActivity extends ActionBarActivity {

    ListView mTodoList;
    ArrayList<Task> mTasks;
    TasksAdapter mAdapter;

    private  final static int ADD_TASK=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo_list_manager);

        mTodoList=(ListView)findViewById(R.id.lstTodoItems);

mTasks=new ArrayList<Task>();
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
        final Task curTask=mTasks.get(position);
        final Dialog dialog= new Dialog(this);
        dialog.setContentView(R.layout.delete_dialog);
        dialog.setTitle(curTask.getTitle());
        Button delBtn=(Button)dialog.findViewById(R.id.menuItemDelete);
        delBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTasks.remove(position);
                mAdapter.notifyDataSetChanged();
                dialog.dismiss();
            }
        });
Button callBtn=(Button)dialog.findViewById(R.id.menuItemCall);
if (curTask.getTitle().startsWith("Call ") ){
    callBtn.setVisibility(View.VISIBLE);
    callBtn.setText(curTask.getTitle());
    callBtn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String number = curTask.getTitle().replace("Call","").trim();
            Intent intent = new Intent(Intent.ACTION_CALL);
            intent.setData(Uri.parse("tel:" + number));
            startActivity(intent);
        }
    });

}else{
    callBtn.setVisibility(View.GONE);        }
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
            Intent intent= new Intent(this, AddNewTodoItemActivity.class);
            startActivityForResult(intent,ADD_TASK);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case ADD_TASK:
                if (resultCode== Activity.RESULT_OK){
                    String title=data.getExtras().getString("title");
                    Date date=(Date)data.getExtras().getSerializable("dueDate");
                    Task task=new Task(title,date);
                    mTasks.add(task);
                    mAdapter.notifyDataSetChanged();
                }
                break;
        }
    }
}
