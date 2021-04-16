package com.example.sqlitetutorial;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.EditText;

public class AddActivity extends AppCompatActivity {

    EditText etBookTitle,etBookAuthor,etNumberOfPage;
    Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        etBookTitle = findViewById(R.id.etBookTitle);
        etBookAuthor = findViewById(R.id.etBookAuthor);
        etNumberOfPage = findViewById(R.id.etNumberOfPage);
        button = findViewById(R.id.button);



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLHelper sqlHelper = new SQLHelper(AddActivity.this);
                sqlHelper.addBook(
                        etBookTitle.getText().toString().trim(),
                        etBookAuthor.getText().toString().trim(),
                        Integer.valueOf(etNumberOfPage.getText().toString().trim()));

                Intent i = new Intent(AddActivity.this,MainActivity.class);
                startActivity(i);

            }
        });

    }
}