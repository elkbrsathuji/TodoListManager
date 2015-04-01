package il.ac.huji.todolist;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Android- on 3/31/2015.
 */
public class TasksAdapter extends BaseAdapter {

    private LayoutInflater mInflater = null;
    private Context mContext;
    private ArrayList<String> mTasksList;

    public TasksAdapter(Context context, ArrayList<String> list) {
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

        TextView taskTitle;
        String curTask;

        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.row_task, null);
            taskTitle = (TextView) convertView.findViewById(R.id.task);
            convertView.setTag(taskTitle);

        } else {
            taskTitle = (TextView) convertView.getTag();
        }
        taskTitle.setText(mTasksList.get(position));
        if (position % 2 == 0) {
            taskTitle.setTextColor(Color.RED);
        } else {
            taskTitle.setTextColor(Color.BLUE);
        }
        return convertView;
    }
}
