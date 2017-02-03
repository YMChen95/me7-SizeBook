package com.example.mengyangchen.assignment1;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;



public class MainActivity extends Activity {

    private static final String FILENAME = "file.sav";
    List<Sizebook> SizeBookList = new ArrayList<>();
    ListView SizeBookListView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button AddButton = (Button) findViewById(R.id.Add);
        SizeBookListView = (ListView) findViewById(R.id.ListView);
        AddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);

                startActivity(intent);

            }
        });
        registerForContextMenu(SizeBookListView);
        //SizeBookListView.setOnItemLongClickListener(new AdapterView.());
    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                      ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("Context Menu");
        menu.add(0, v.getId(), 0, "Edit");
        menu.add(0, v.getId(), 0, "Delete");

    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        //taken from http://stackoverflow.com/questions/2453620/android-how-to-find-the-position-clicked-from-the-context-menu
        //2017-02-02
        AdapterView.AdapterContextMenuInfo info =(AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int position = info.position;

        if (item.getTitle() == "Edit") {
            String name = SizeBookList.get(position).get_name();
            Toast.makeText(this, "Editing "+ name, Toast.LENGTH_SHORT).show();
            Intent i = new Intent(MainActivity.this, EditActivity.class);

            //TO DO
            i.putExtra("position",position);

            startActivity(i);
        } else if (item.getTitle() == "Delete") {
            String name = SizeBookList.get(position).get_name();
            Toast.makeText(this, name+" Deleted", Toast.LENGTH_SHORT).show();
            SizeBookList.remove(position);
            ArrayAdapter<Sizebook>adapter = new SizeBookListAdapter();
            SizeBookListView.setAdapter(adapter);
            saveInFile();
        } else {
            return false;
        }

        return true;
    }

    @Override
    protected void onStart(){
        super.onStart();
        loadFromFile();
        ArrayAdapter<Sizebook>adapter = new SizeBookListAdapter();
        SizeBookListView.setAdapter(adapter);
    }
    private class SizeBookListAdapter extends ArrayAdapter<Sizebook>{
        public SizeBookListAdapter(){
            super(MainActivity.this,R.layout.listview_sizebook,SizeBookList);
        }
        @Override
        public View getView(int position, View view, ViewGroup parent){
            if (view == null)
                view=getLayoutInflater().inflate(R.layout.listview_sizebook,parent,false);
            Sizebook currentSizebook = SizeBookList.get(position);
            int count =position+1;
            TextView count_out = (TextView) view.findViewById(R.id.count_view);
            count_out.setText("Count = "+count);

            TextView name_out = (TextView) view.findViewById(R.id.name_view);
            name_out.setText("Name: "+currentSizebook.get_name());
            TextView date_out = (TextView) view.findViewById(R.id.date_view);
            date_out.setText("Date: "+currentSizebook.get_date());
            TextView neck_out = (TextView) view.findViewById(R.id.neck_view);
            neck_out.setText("Neck: "+currentSizebook.get_neck());
            TextView bust_out = (TextView) view.findViewById(R.id.bust_view);
            bust_out.setText("Bust: "+currentSizebook.get_bust());
            TextView chest_out = (TextView) view.findViewById(R.id.chest_view);
            chest_out.setText("Chest: "+currentSizebook.get_chest());
            TextView waist_out = (TextView) view.findViewById(R.id.waist_view);
            waist_out.setText("Waist: "+currentSizebook.get_waist());
            TextView hip_out = (TextView) view.findViewById(R.id.hip_view);
            hip_out.setText("Hip: "+currentSizebook.get_hip());
            TextView inseam_out = (TextView) view.findViewById(R.id.inseam_view);
            inseam_out.setText("Inseam: "+currentSizebook.get_inseam());
            TextView comment_out = (TextView) view.findViewById(R.id.comment_view);
            comment_out.setText("Comment: "+currentSizebook.get_comment());
            return view;
        }
    }

    public void loadFromFile() {
        try {
            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            Gson gson =new Gson();
            //Taken from http://stackoverflow.com/questions/12384064/gson-convert-from-json-to-a-typed-arraylistt
            //2017-01-26
            SizeBookList =  gson.fromJson(in, new TypeToken<ArrayList<Sizebook>>(){}.getType());
            fis.close();
        } catch (FileNotFoundException e) {
            SizeBookList = new ArrayList<>();
        } catch (IOException e) {
            throw new RuntimeException();
        }

    }

    public void saveInFile() {
        try {
            FileOutputStream fos = openFileOutput(FILENAME,
                    Context.MODE_PRIVATE);
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));
            Gson gson = new Gson();
            gson.toJson(SizeBookList, out);
            out.flush();

            fos.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException();

        } catch (IOException e) {
            throw new RuntimeException();
        }
    }
}
