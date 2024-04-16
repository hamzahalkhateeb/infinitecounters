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

public class ASCListAdapter extends ArrayAdapter{
    private static final String TAG = "newListAdapter2";

    private Context mContext2;
    int graybackgroundColor = ContextCompat.getColor(getContext(), R.color.gray);
    int mResource2;
    public ASCListAdapter(@NonNull Context context, int resource, List<ASCconstructor> objects) {
        super(context, resource, objects);
        mContext2 = context;
        mResource2 = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @NonNull View convertView, @NonNull ViewGroup parent) {
        if(convertView == null) {
            convertView = LayoutInflater.from(mContext2).inflate(R.layout.asc_adapter_view, parent, false);
        }
        TextView viewname = convertView.findViewById(R.id.ascending_counter_name_view);
        TextView view_current_clicks = convertView.findViewById(R.id.Current_clicks_view222);

        Button ascend = convertView.findViewById(R.id.asc222);
        Button descend = convertView.findViewById(R.id.desc222);
        Button overflowButton = convertView.findViewById(R.id.overflow_Button222);

        ASCconstructor counter = (ASCconstructor) getItem(position);

        viewname.setText(counter.getName());
        view_current_clicks.setText(String.valueOf(counter.getcurrent_clicks()) + "/" + counter.getgoal_clicks());



        if (counter.getcurrent_clicks() == counter.getgoal_clicks()){
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

                ASCconstructor ClickedCounter = (ASCconstructor) getItem(clickedPosition);

                database2 db = new database2(mContext2);
                db.updateCurrent_clicks222(ClickedCounter.getId(), ClickedCounter.getcurrent_clicks()+1);
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

                ASCconstructor ClickedCounter = (ASCconstructor) getItem(clickedPosition);

                database2 db = new database2(mContext2);
                db.updateCurrent_clicks222(ClickedCounter.getId(), ClickedCounter.getcurrent_clicks()-1);
                ClickedCounter.setcurrent_clicks(ClickedCounter.getcurrent_clicks() - 1);
                notifyDataSetChanged();

                db.close();
            }
        });

        overflowButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {showPopupMenu222(view, position);}

            });
        return convertView;
    }

    private void showPopupMenu222(View anchorView, int position) {
        PopupMenu popupMenu = new PopupMenu(mContext2, anchorView);
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
        ASCconstructor counter = (ASCconstructor) getItem(position);
        database2 db = new database2(mContext2);
        db.updateCurrent_clicks222(counter.getId(), 0);
        counter.setcurrent_clicks(counter.getcurrent_clicks() * 0);
        notifyDataSetChanged();
        db.close();
    }

    private void handleDeleteOption(int position) {
        ASCconstructor counter = (ASCconstructor) getItem(position);
        database2 db = new database2(mContext2);
        db.deleteCounter(counter.getId());
        remove(counter);
        notifyDataSetChanged();
        db.close();
    }


}
