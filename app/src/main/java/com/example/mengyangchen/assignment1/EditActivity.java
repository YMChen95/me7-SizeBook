package com.example.mengyangchen.assignment1;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * This activity is the sub class of the MainActivity.
 * This Activity basically received the position that passed from context menu
 * set all the Edittexts to the original name, date, neck etc.
 * allowed user to change it, and whenever the edit_confirm button is clicked
 * The new SizeBook will replace the origin one in SizeBookList
 */
public class EditActivity extends MainActivity {
    EditText name_edit,date_edit,neck_edit,bust_edit,chest_edit,waist_edit,hip_edit,inseam_edit,comment_edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        Intent intent= getIntent();
        final int current_pos = intent.getIntExtra("position",0);
        //getIntent().getSerializableExtra("MyClass");
        name_edit = (EditText) findViewById(R.id.name_edit);
        date_edit = (EditText) findViewById(R.id.date_edit);
        neck_edit = (EditText) findViewById(R.id.neck_edit);
        bust_edit = (EditText) findViewById(R.id.bust_edit);
        chest_edit = (EditText) findViewById(R.id.chest_edit);
        waist_edit = (EditText) findViewById(R.id.waist_edit);
        hip_edit = (EditText) findViewById(R.id.hip_edit);
        inseam_edit =(EditText) findViewById(R.id.inseam_edit);
        comment_edit = (EditText) findViewById(R.id.comment_edit);


        //name_edit.setText(SizeBookList.get(Passed_pos).get_name());

        final Button SaveEditButton=(Button) findViewById(R.id.save_edit);


        SaveEditButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name= name_edit.getText().toString();
                String date = date_edit.getText().toString();
                String neck =neck_edit.getText().toString();
                String bust =bust_edit.getText().toString();
                String chest =chest_edit.getText().toString();
                String waist =waist_edit.getText().toString();
                String hip =hip_edit.getText().toString();
                String inseam = inseam_edit.getText().toString();
                String comment=comment_edit.getText().toString();

                SizeBookList.set(current_pos,new Sizebook(name,date,neck,bust,chest,waist,hip,inseam,comment));
                Toast.makeText(getApplicationContext(),name+" had been changed",Toast.LENGTH_SHORT).show();
                setResult(RESULT_OK);
                finish();
                saveInFile();

            }
        });
        name_edit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                SaveEditButton.setEnabled(!name_edit.getText().toString().trim().isEmpty());
            }
            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    /** Called whenever back to the activity
    * load from file first every time and set the Array adapter
    * get view from the lisview_sizebook layout
    * get the position for the count to count how many people in list now
    */
    @Override
    protected void onStart(){
        super.onStart();
        Intent intent= getIntent();
        int Passed_pos = intent.getIntExtra("position",0);
        name_edit.setText(SizeBookList.get(Passed_pos).get_name());
        date_edit.setText(SizeBookList.get(Passed_pos).get_date());
        neck_edit.setText(SizeBookList.get(Passed_pos).get_neck());
        bust_edit.setText(SizeBookList.get(Passed_pos).get_bust());
        chest_edit.setText(SizeBookList.get(Passed_pos).get_chest());
        waist_edit.setText(SizeBookList.get(Passed_pos).get_waist());
        hip_edit.setText(SizeBookList.get(Passed_pos).get_hip());
        inseam_edit.setText(SizeBookList.get(Passed_pos).get_inseam());
        comment_edit.setText(SizeBookList.get(Passed_pos).get_comment());
    }

}
