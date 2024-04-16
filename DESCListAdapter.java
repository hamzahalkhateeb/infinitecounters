package com.example.a99;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.TextView;
import androidx.core.content.ContextCompat;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class DESCListAdapter extends ArrayAdapter{
    private static final String TAG = "newListAdapter3";

    private Context mContext3;

    int graybackgroundColor = ContextCompat.getColor(getContext(),R.color.gray);

    int mResource3;

    public DESCListAdapter(@NonNull Context context, int resource, List<DESCconstructor> objects) {
        super (context, resource, objects);
        mContext3 = context;
        mResource3 = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @NonNull View convertView, @NonNull ViewGroup parent){
        if(convertView == null){
            convertView = LayoutInflater.from(mContext3).inflate(R.layout.desc_adapter_view, parent, false);
        }
        TextView viewname = convertView.findViewById(R.id.descending_counter_name_view);
        TextView view_current_clicks = convertView.findViewById(R.id.Current_clicks_view333);

        Button ascend = convertView.findViewById(R.id.asc333);
        Button descend = convertView.findViewById(R.id.desc333);
        Button overflowButton = convertView.findViewById(R.id.overflow_Button333);

        DESCconstructor counter = (DESCconstructor) getItem(position);
        viewname.setText(counter.getName());
        view_current_clicks.setText(String.valueOf(counter.getcurrent_clicks()) + " until 0");

        if (counter.getcurrent_clicks() == 0) {
            ascend.setEnabled(false);
            ascend.setBackgroundColor(graybackgroundColor);
            descend.setEnabled(false);
            descend.setBackgroundColor(graybackgroundColor);

        }

        ascend.setTag(position);
        ascend.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                int clickedPosition = (int) view.getTag();

                DESCconstructor ClickedCounter = (DESCconstructor) getItem(clickedPosition);

                database2d db = new database2d(mContext3);
                db.updateCurrent_clicks333(ClickedCounter.getId(), ClickedCounter.getcurrent_clicks()+1);
                ClickedCounter.setcurrent_clicks(ClickedCounter.getcurrent_clicks() + 1);

                notifyDataSetChanged();



                db.close();
            }
        });


        descend.setTag(position);
        descend.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                int clickedPosition = (int) view.getTag();

                DESCconstructor ClickedCounter = (DESCconstructor) getItem(clickedPosition);

                database2d db = new database2d(mContext3);
                db.updateCurrent_clicks333(ClickedCounter.getId(), ClickedCounter.getcurrent_clicks()-1);
                ClickedCounter.setcurrent_clicks(ClickedCounter.getcurrent_clicks() - 1);

                notifyDataSetChanged();

                db.close();
            }
        });

        overflowButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {showPopupMenu333(view, position);}

        });
        return convertView;
    }
    private void showPopupMenu333(View anchorView, int position) {
        PopupMenu popupMenu = new PopupMenu(mContext3, anchorView);
        popupMenu.inflate(R.menu.menu_main); // Use the same menu XML you defined earlier

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.reset_counter:
                        // Handle Reset option
                        handleResetOption(position);
                        return true;
                    case R.id.delete_counter:
                        // Handle Delete option
                        handleDeleteOption(position);
                        return true;
                    default:
                        return false;
                }
            }
        });

        popupMenu.show();
    }

    private void handleResetOption(int position) {
        DESCconstructor counter = (DESCconstructor) getItem(position);
        database2d db = new database2d(mContext3);
        db.updateCurrent_clicks333(counter.getId(), counter.getgoal_clicks());
        counter.setcurrent_clicks(counter.getgoal_clicks());

        notifyDataSetChanged();
        db.close();
    }

    private void handleDeleteOption(int position) {
        DESCconstructor counter = (DESCconstructor) getItem(position);
        database2d db = new database2d(mContext3);
        db.deleteCounter(counter.getId());
        remove(counter);
        notifyDataSetChanged();
        db.close();
    }


}



