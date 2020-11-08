package com.example.speedrecords;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.speedrecords.adapter.SpeedAdapter;
import com.example.speedrecords.db.AppDatabase;
import com.example.speedrecords.model.Speed;
import com.example.speedrecords.util.AppExecutors;


public class MainActivity extends AppCompatActivity {
    int count = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final RecyclerView mRecyclerView = findViewById(R.id.recyclerView);
        final TextView totalTextView = findViewById(R.id.total_text_view);
        final TextView overlimitTextView = findViewById(R.id.over_limit_text_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        AppExecutors executors = new AppExecutors();
        executors.diskIO().execute(new Runnable() {
            @Override
            public void run() {
                AppDatabase db = AppDatabase.getInstance(MainActivity.this);
                Speed [] speeds =  db.speedDao().showAllData();
                for (Speed s : speeds) {
                    if(s.velocity > 80){
                        count++;
                        overlimitTextView.setText("OVERLIMT: "+count);
                    }

                }totalTextView.setText("TOTAL: "+speeds.length);
                SpeedAdapter adapter = new SpeedAdapter(MainActivity.this ,speeds);
                mRecyclerView.setAdapter(adapter);
            }
        });
        Button addRecordButton = findViewById(R.id.add_record_button);
        addRecordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this , AddActivity.class);
                startActivity(intent);
            }
        });
    }
}