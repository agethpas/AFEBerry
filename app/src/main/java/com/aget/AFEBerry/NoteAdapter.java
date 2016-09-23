package com.aget.AFEBerry;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by Pascal on 15.07.2016.
 */
public class NoteAdapter extends ArrayAdapter<Note>{

    public static class ViewHolder{
        TextView title;
        TextView note;
        TextView date;

    }
    public NoteAdapter(Context context, ArrayList<Note> notes){
        super(context, 0, notes);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        // Get the date Item for this position
        Note note = getItem(position);

        // create new Viewholder
        ViewHolder viewHolder;

        // Check if an existing vie is being reused, otherwise inflate a new view from costum row layout
        if (convertView == null) {

            // create view if not view beeing used and save viewreference in the viewholder
            viewHolder = new ViewHolder();

            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_row, parent, false);

            // set views to viewholder to not go back and use find by id every time by new row
            viewHolder.title = (TextView) convertView.findViewById(R.id.listItemNoteTitle);
            viewHolder.note = (TextView) convertView.findViewById(R.id.listItemNoteBody);
            viewHolder.date = (TextView) convertView.findViewById(R.id.listItemDate);


            // Use set tag to remember our viewholder which is holding reference
            convertView.setTag(viewHolder);
        }else{
            //we already have a view so just go to viewholder and grab widgets from it
            viewHolder = (ViewHolder) convertView.getTag();

        }

        // Populate the data into template view using the data object
        viewHolder.title.setText(note.getTitle());
        viewHolder.note.setText(note.getMessage());

        String data = String.valueOf(note.getDateCreatedMilli());
        SimpleDateFormat df1 = new SimpleDateFormat("MMM dd");
        String formatedDate = df1.format(note.getDateCreatedMilli());
        viewHolder.date.setText(formatedDate);

        //return the data to display
        return convertView;


    }

}
