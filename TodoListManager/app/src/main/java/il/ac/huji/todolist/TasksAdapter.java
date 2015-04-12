package il.ac.huji.todolist;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Android- on 3/31/2015.
 */
public class TasksAdapter extends BaseAdapter {

    private LayoutInflater mInflater = null;
    private Context mContext;
    private ArrayList<Task> mTasksList;

    public TasksAdapter(Context context, ArrayList<Task> list) {
        mTasksList = list;
        mContext = context;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mTasksList == null ? 0 : mTasksList.size();
    }

    @Override
    public Object getItem(int position) {
        return mTasksList == null ? null : mTasksList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        Task curTask;

        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.row_task, null);
            holder= new ViewHolder();


            holder.title = (TextView) convertView.findViewById(R.id.txtTodoTitle);
            holder.date=(TextView)convertView.findViewById(R.id.txtTodoDueDate);
            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }
         curTask=mTasksList.get(position);
        holder.title.setText(curTask.getTitle());
        holder.date.setText(curTask.getDateAsString());
        if (curTask.getDueDate().before(new Date())) {
            holder.title.setTextColor(Color.RED);
            holder.date.setTextColor(Color.RED);
        }

        else {
            holder.title.setTextColor(Color.BLACK);
            holder.date.setTextColor(Color.BLACK);
        }
        return convertView;
    }


    class ViewHolder{
        TextView title;
        TextView date;
    }
}
