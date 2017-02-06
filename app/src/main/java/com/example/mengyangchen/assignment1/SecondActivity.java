package com.example.mengyangchen.assignment1;


import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by me7 on 1/19/17.
 * This is the subclass(AddActivity) for the main activity
 * When user clicked add, it goes to this activity
 */

public class SecondActivity extends MainActivity {

    EditText name_in,date_in,neck_in,bust_in,chest_in,waist_in,hip_in,inseam_in,comment_in;



    /**
     * Called when the activity is first created
     * Get the information from all the EditText
     * Store them into String and add to the SizeBookList by using SizeBook
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        name_in = (EditText) findViewById(R.id.name_in);
        date_in = (EditText) findViewById(R.id.date_in);
        neck_in = (EditText) findViewById(R.id.neck_in);
        bust_in = (EditText) findViewById(R.id.bust_in);
        chest_in = (EditText) findViewById(R.id.chest_in);
        waist_in = (EditText) findViewById(R.id.waist_in);
        hip_in = (EditText) findViewById(R.id.hip_in);
        inseam_in =(EditText) findViewById(R.id.inseam_in);
        comment_in = (EditText) findViewById(R.id.comment_in);

        final Button SaveButton=(Button) findViewById(R.id.save);

        SaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name= name_in.getText().toString();
                String date = date_in.getText().toString();
                String neck = neck_in.getText().toString();
                String bust =bust_in.getText().toString();
                String chest =chest_in.getText().toString();
                String waist =waist_in.getText().toString();
                String hip =hip_in.getText().toString();
                String inseam = inseam_in.getText().toString();
                String comment=comment_in.getText().toString();

                //Intent intent = new Intent(SecondActivity.this, MainActivity.class);
                SizeBookList.add(new Sizebook(name,date,neck,bust,chest,waist,hip,inseam,comment));
                Toast.makeText(getApplicationContext(),name+" has been successfully added to the SizeBook",Toast.LENGTH_SHORT).show();
                //startActivity(intent);
                saveInFile();
                //Log.i("Size", ""+SizeBookList.size());
                setResult(RESULT_OK);
                finish();
            }
        });
            /**
             *Only when the EditText Name_in is not empty the button "Save" is enable
             */
        name_in.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                SaveButton.setEnabled(!name_in.getText().toString().trim().isEmpty());
            }
            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }
    /** Called whenever back to the activity
     * super.onStart()its for the SizeBookList
     */
    @Override
    protected void onStart(){
        super.onStart();
    }


}
