package com.example.sqlitetutorial;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton fab;

    SQLHelper sqlHelper;

    ArrayList<String> book_id,book_title,book_author,book_pages;

    CustomAdapter adapter;
    ImageView imageView;
    TextView tvNoData;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_item,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.removeAll){
            SQLHelper sql = new SQLHelper(MainActivity.this);
            sql.deleteAllData();
            Toast.makeText(this, "All deleted successfully!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MainActivity.this,MainActivity.class);
            startActivity(intent);
        }
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        fab = findViewById(R.id.floatingButton);
        imageView = findViewById(R.id.IVNodata);
        tvNoData = findViewById(R.id.tvNoData);



        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,AddActivity.class);
                startActivity(intent);
                /*SQLHelper sql = new SQLHelper(MainActivity.this);
                sql.deleteAllData();*/

            }
        });

        sqlHelper = new SQLHelper(this);
        book_id = new ArrayList<>();
        book_title = new ArrayList<>();
        book_author = new ArrayList<>();
        book_pages = new ArrayList<>();

        storeDataInArrays();
        adapter = new CustomAdapter(MainActivity.this,this,book_id,book_title,book_author,book_pages);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            recreate();
        }
    }

    void storeDataInArrays(){
        Cursor cursor = sqlHelper.readAllData();
        if(cursor.getCount() == 0){
            //Toast.makeText(this, "No data!", Toast.LENGTH_SHORT).show();
            imageView.setVisibility(View.VISIBLE);
            tvNoData.setVisibility(View.VISIBLE);
        }else{
            imageView.setVisibility(View.INVISIBLE);
            tvNoData.setVisibility(View.INVISIBLE);
            while(cursor.moveToNext()){
                book_id.add(cursor.getString(0));
                book_title.add(cursor.getString(1));
                book_author.add(cursor.getString(2));
                book_pages.add(cursor.getString(3));
            }
        }
    }
}