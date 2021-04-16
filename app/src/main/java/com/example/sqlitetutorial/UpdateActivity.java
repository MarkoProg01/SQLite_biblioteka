package com.example.sqlitetutorial;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {

    EditText etBookTitle,etBookAuthor,etNumberOfPage;
    Button btn,deleteButton;
    String id,title,author,pages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        etBookTitle = findViewById(R.id.etBookTitle2);
        etBookAuthor = findViewById(R.id.etBookAuthor2);
        etNumberOfPage = findViewById(R.id.etNumberOfPage2);
        btn = findViewById(R.id.updateButton);
        deleteButton = findViewById(R.id.deleteButton);


        getAndSetIntentData();
        // Postavlja naslov ActionBaru
        ActionBar ab = getSupportActionBar();
        if(ab!=null) {
            ab.setTitle(title);
        }
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                title=etBookTitle.getText().toString().trim();
                author=etBookAuthor.getText().toString().trim();
                pages=etNumberOfPage.getText().toString().trim();
                SQLHelper sqlHelper = new SQLHelper(UpdateActivity.this);
                sqlHelper.updateData(id,title,author,pages);
                finish();
            }
        });

       deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            SQLHelper sql = new SQLHelper(UpdateActivity.this);
            sql.deleteOneRow(id);
                Intent intent = new Intent(UpdateActivity.this,MainActivity.class);
                startActivity(intent);

            }
        });


    }

    void getAndSetIntentData(){
        if(getIntent().hasExtra("id") && getIntent().hasExtra("title") &&
                getIntent().hasExtra("author") && getIntent().hasExtra("pages")){

            //Getting Data
            id = getIntent().getStringExtra("id");
            title = getIntent().getStringExtra("title");
            author = getIntent().getStringExtra("author");
            pages = getIntent().getStringExtra("pages");
            //Setting data
            etBookTitle.setText(title);
            etBookAuthor.setText(author);
            etNumberOfPage.setText(pages);

        }else {
            Toast.makeText(this, "No data!", Toast.LENGTH_SHORT).show();
        }
    }
}