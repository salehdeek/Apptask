package com.example.apptask;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class AddTaskActivity extends AppCompatActivity {
    EditText descriptionInput, dueDateInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        descriptionInput = findViewById(R.id.descriptionInput);
        dueDateInput = findViewById(R.id.dueDateInput);

        Button saveButton = findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveTask();
            }
        });
    }

    private void saveTask() {
        String description = descriptionInput.getText().toString();
        String dueDate = dueDateInput.getText().toString();
        Task newTask = new Task(System.currentTimeMillis() + "", description, dueDate, false);

        SharedPreferences prefs = getSharedPreferences("Tasks", MODE_PRIVATE);
        String json = prefs.getString("tasks", null);
        ArrayList<Task> tasks;
        if (json != null) {
            Type type = new TypeToken<ArrayList<Task>>() {}.getType();
            tasks = new Gson().fromJson(json, type);
        } else {
            tasks = new ArrayList<>();
        }
        tasks.add(newTask);

        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("tasks", new Gson().toJson(tasks));
        editor.apply();

        finish();
    }
}
