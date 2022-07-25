package com.ombuweb.sirpda;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;

import android.view.Menu;
import android.view.MenuItem;

import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private InventoryViewModel mInventoryViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mInventoryViewModel = ViewModelProviders.of(this).get(InventoryViewModel.class);
        Date fecha = new Date();
        Long hora = fecha.getTime();
        Inventory inventory = new Inventory(
                fecha, hora,"miAlmancen","DEscription",
                "Nandee"
        );
        mInventoryViewModel.insert(inventory);
        mInventoryViewModel.getAllInventories().observe(this, new Observer<List<Inventory>>() {
            @Override
            public void onChanged(@Nullable final List<Inventory> inventories) {

                for(int i = 0; i < inventories.size(); i++) {
                    System.out.println(inventories.get(i).getAlmancen());
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}