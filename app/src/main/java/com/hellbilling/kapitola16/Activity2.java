package com.hellbilling.kapitola16;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import java.util.ArrayList;

public class Activity2 extends ListActivity {
    private static final String[] items={"tu su obe menu generovane z .xml, pouzit toto","len by to asi chcelo pouzit android:theme=\"@style/AppTheme\" v manifeste, tak ako to vygeneruje nova trieda v android studiu", "ipsum", "dolor"};
    private ArrayList<String> words=null;

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);

        initAdapter();
        registerForContextMenu(getListView());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        new MenuInflater(this).inflate(R.menu.menu_activity2, menu);

        return(super.onCreateOptionsMenu(menu));
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        new MenuInflater(this).inflate(R.menu.context, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add:
                add();
                return(true);

            case R.id.reset:
                initAdapter();
                return(true);
        }

        return(super.onOptionsItemSelected(item));
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info=
                (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
        ArrayAdapter<String> adapter=(ArrayAdapter<String>)getListAdapter();

        switch (item.getItemId()) {
            case R.id.cap:
                String word=words.get(info.position);

                word=word.toUpperCase();

                adapter.remove(words.get(info.position));
                adapter.insert(word, info.position);

                return(true);

            case R.id.remove:
                adapter.remove(words.get(info.position));

                return(true);
        }

        return(super.onContextItemSelected(item));
    }

    private void initAdapter() {
        words=new ArrayList<>();

        for (String s : items) {
            words.add(s);
        }

        setListAdapter(new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, words));
    }

    private void add() {
        final View addView=getLayoutInflater().inflate(R.layout.add, null);

        new AlertDialog.Builder(this)
                .setTitle("Add a Word")
                .setView(addView)
                .setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int whichButton) {
                                ArrayAdapter<String> adapter=(ArrayAdapter<String>)getListAdapter();
                                EditText title=(EditText)addView.findViewById(R.id.title);

                                adapter.add(title.getText().toString());
                            }
                        })
                .setNegativeButton("Cancel", null)
                .show();
    }
}