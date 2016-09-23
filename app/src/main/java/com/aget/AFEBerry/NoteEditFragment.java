package com.aget.AFEBerry;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;



/**
 * A simple {@link Fragment} subclass.
 */
public class NoteEditFragment extends Fragment {



    private EditText title, message;


    private boolean newNote = false;
    private long noteId = 0;

    public NoteEditFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // grab bundle that send along whether or not create a new note
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            newNote = bundle.getBoolean(NoteDetailActivity.NEW_NOTE_EXTRA, false);
        }
        //essential for fragment to alter action bar
        setHasOptionsMenu(true);


        // inflate our fragement edit layout
        View fragmentLayout = inflater.inflate(R.layout.fragment_note_edit, container, false);

        //grab widget references from layout
        title = (EditText) fragmentLayout.findViewById(R.id.editNoteTitle);
        message = (EditText) fragmentLayout.findViewById(R.id.editNoteMessage);


        //populate widgets with note data
        Intent intent = getActivity().getIntent();
        title.setText(intent.getExtras().getString(MainActivity.NOTE_TITLE_EXTRA, ""));
        message.setText(intent.getExtras().getString(MainActivity.NOTE_MESSAGE_EXTRA, ""));
        noteId = intent.getExtras().getLong(MainActivity.NOTE_ID_EXTRA, 0);


        // Inflate the layout for this fragment
        return fragmentLayout;
    }



//menu now displays a save button
public void onCreateOptionsMenu(Menu menu, MenuInflater inflater){
    menu.clear();
    inflater.inflate(R.menu.menu_edit,menu);
    super.onCreateOptionsMenu(menu,inflater);
}
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_save_note:
                NotebookDbAdapter dbAdapter = new NotebookDbAdapter(getActivity().getBaseContext());
                dbAdapter.open();

                //if new note create it in DB
                if (newNote) {
                    dbAdapter.createNote(title.getText() + "", message.getText() + "");

                    //old note, just update the note
                } else {
                    dbAdapter.updateNote(noteId, title.getText() + "", message.getText() + "");
                }
                dbAdapter.close();
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
                //confirmDialogObject.show();
            case android.R.id.home:
                getActivity().onBackPressed();
                return true;
            case R.id.action_delete_note:
                dbAdapter = new NotebookDbAdapter(getActivity().getBaseContext());
                dbAdapter.open();
                dbAdapter.deleteNote(noteId);
                dbAdapter.close();
                intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);



        }

        return true;


    }


}


