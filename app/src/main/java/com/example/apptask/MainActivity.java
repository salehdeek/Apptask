package com.example.apptask;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    ArrayList<Task> tasks;
    ArrayAdapter<Task> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);
        tasks = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, tasks);
        listView.setAdapter(adapter);

        findViewById(R.id.addButton).setOnClickListener(v -> startActivity(new Intent(MainActivity.this, AddTaskActivity.class)));

        listView.setOnItemClickListener((parent, view, position, id) -> {
            Task task = tasks.get(position);
            task.setDone(!task.isDone());
            saveTasks();
            loadTasks();
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadTasks();
    }

    private void loadTasks() {
        SharedPreferences prefs = getSharedPreferences("Tasks", MODE_PRIVATE);
        String json = prefs.getString("tasks", null);
        if (json != null) {
            Type type = new TypeToken<ArrayList<Task>>() {}.getType();
            tasks = new Gson().fromJson(json, type);
        } else {
            tasks = new ArrayList<>();
        }
        adapter.clear();
        adapter.addAll(tasks);
        adapter.notifyDataSetChanged();
    }

    private void saveTasks() {
        SharedPreferences prefs = getSharedPreferences("Tasks", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("tasks", new Gson().toJson(tasks));
        editor.apply();
    }
}
