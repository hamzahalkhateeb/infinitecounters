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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class newListAdapter extends ArrayAdapter {
    private static final String TAG = "newListAdapter";

    private Context mContext;
    int mResource;

    public newListAdapter(@NonNull Context context, int resource, List<infinconstructor> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

        @NonNull
        @Override
        public View getView(int position,@Nullable View convertView, @NonNull ViewGroup parent) {
            // Check if convertView is null, indicating that it needs to be inflated
            if (convertView == null) {
                // Inflate the layout for your item view
                convertView = LayoutInflater.from(mContext).inflate(R.layout.adapter_view, parent, false);
            }

            // Get references to views within your item layout
            TextView viewname = convertView.findViewById(R.id.infinite_counter_name_view);
            TextView view_current_clicks = convertView.findViewById(R.id.Current_clicks_view);
            Button ascend = convertView.findViewById(R.id.asc111);
            Button descend = convertView.findViewById(R.id.desc111);
            Button overflowButton = convertView.findViewById(R.id.overflow_Button);

            // Get the data object for the current position from the ArrayAdapter using getItem(position)
            infinconstructor person = (infinconstructor) getItem(position);


            // Set the Name and Current_clicks values into the views
            viewname.setText(person.getName());
            view_current_clicks.setText(String.valueOf(person.getCurrent_clicks()));



            ascend.setTag(position);
            ascend.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    int clickedPosition = (int) view.getTag();

                    //get the data object for the clicked position, in other words, get the specific counter where the click was positioned
                    infinconstructor ClickedPerson = (infinconstructor) getItem(clickedPosition);

                    //Access teh database
                    database1 database = new database1(mContext);

                    //now that we accessed teh database above, we can manipulate it by doing the following
                    database.updateCurrent_Clicks(ClickedPerson.getId(), ClickedPerson.getCurrent_clicks() + 1);

                    //update the corresponding object in the infinconstructor in the everyone list
                    ClickedPerson.setCurrent_clicks(ClickedPerson.getCurrent_clicks() + 1);

                    //update the list udapter to display the new data
                    notifyDataSetChanged();


                    //close the database as we opened earlier
                    database.close();
                    view_current_clicks.setText(String.valueOf(person.getCurrent_clicks()));
                }
            });

            descend.setTag(position);
            descend.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view){

                int clickedPosition = (int) view.getTag();

                //get the data object for the clicked position, in other words, get the specific counter where the click was positioned
                infinconstructor ClickedPerson = (infinconstructor) getItem(clickedPosition);

                //Access teh database
                database1 database = new database1(mContext);

                //now that we accessed teh database above, we can manipulate it by doing the following
                    database.updateCurrent_Clicks(ClickedPerson.getId(), ClickedPerson.getCurrent_clicks() - 1);


                    //Update the infinconstructor object to display the changes
                    ClickedPerson.setCurrent_clicks(ClickedPerson.getCurrent_clicks() - 1 );
                //update the list udapter to display the new data
                notifyDataSetChanged();

                //close the database as we opened earlier
                    database.close();
                    view_current_clicks.setText(String.valueOf(person.getCurrent_clicks()));
                }
            });

            //the drop down menu code
            overflowButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showPopupMenu(v, position);
                }
            });


            // Return the populated view
            return convertView;
        }

    private void showPopupMenu(View anchorView, int position) {
        PopupMenu popupMenu = new PopupMenu(mContext, anchorView);
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
        infinconstructor person = (infinconstructor) getItem(position);
        database1 database = new database1(mContext);
        database.updateCurrent_Clicks(person.getId(), 0);
        person.setCurrent_clicks(person.getCurrent_clicks() * 0);
        //ClickedPerson.setCurrent_clicks(ClickedPerson.getCurrent_clicks() - 1 );
        notifyDataSetChanged();
        database.close();
    }

    private void handleDeleteOption(int position) {
        infinconstructor person = (infinconstructor) getItem(position);
        database1 database = new database1(mContext);
        database.deleteCounter(person.getId());
        remove(person);
        notifyDataSetChanged();
        database.close();
    }





    }



