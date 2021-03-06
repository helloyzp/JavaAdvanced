package com.example.javaadvanced.ui.RecyclerView.RecyclerViewCache;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.LinearLayout;

import com.example.javaadvanced.R;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewCacheActivity extends AppCompatActivity {

    private RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerviewcache);

        rv = (RecyclerView) findViewById(R.id.rv);

        rv.setLayoutManager(new GridLayoutManager(this, 3));
        rv.addItemDecoration(new DividerItemDecoration(this, LinearLayout.VERTICAL));

        final List<String> list = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            list.add("" + i);
        }

        final CustomAdapter adapter = new CustomAdapter(this, list);
        rv.setAdapter(adapter);
    }
}