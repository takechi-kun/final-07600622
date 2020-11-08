package com.example.speedrecords;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.
        view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.speedrecords.db.AppDatabase;
import com.example.speedrecords.model.Speed;
import com.example.speedrecords.util.AppExecutors;

public class AddActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        final EditText distanceEditText = findViewById(R.id.distance_edit_text);
        final EditText timeEditText = findViewById(R.id.time_edit_text);
        Button saveButton = findViewById(R.id.save_button);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final double distance = Double.parseDouble(distanceEditText.getText().toString());
                final double time = Double.parseDouble(timeEditText.getText().toString());
                AppExecutors executors = new AppExecutors();
                executors.diskIO().execute(new Runnable() {
                    @Override
                    public void run() {
                        AppDatabase db = AppDatabase.getInstance(AddActivity.this);
                        db.speedDao().addSpeed(new Speed(0,distance,time));
                        Intent intent = new Intent(AddActivity.this , MainActivity.class);
                        startActivity(intent);
                    }
                });
            }
        });
    }
}