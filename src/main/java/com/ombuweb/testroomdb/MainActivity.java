package com.ombuweb.testroomdb;

import android.os.Bundle;

import com.google.common.util.concurrent.ListenableFuture;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.util.Log;
import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {
    private InventoryViewModel mInventoryViewModel;
    private ProductViewModel mProductViewModel;
    private Button saveBtn;
    private Button deleteBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
saveBtn = findViewById(R.id.save_button);
deleteBtn = findViewById(R.id.delete_button);
        mInventoryViewModel = ViewModelProviders.of(this).get(InventoryViewModel.class);
        mProductViewModel = ViewModelProviders.of(this).get(ProductViewModel.class);


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

    public void saveInvToLocal(View view) {

        Date fecha = new Date();
        Long hora = fecha.getTime();
        Inventory inventory = new Inventory(
                fecha, hora,"miAlmancen","DEscription",
                "Nandee"
        );
        ListenableFuture<Long> future = mInventoryViewModel.insert(inventory);

                try {
                    Log.i("RUUM", future.isDone()+"");
                    Long inv_id = future.get();
                    Product pro1 = new Product(inv_id,"12384","lote",
                            20,23.89,
                            50,"code1",null);
                    Product pro2 = new Product(inv_id,"1204","lote",
                            20,23.89,
                            50,"code1",null);
                    List<Product> products = new ArrayList<Product>();
                    products.add(pro1);
                    products.add(pro2);
                    //mProductViewModel.insert(pro1);
                    mProductViewModel.insertProducts(products);
                } catch (ExecutionException e) {
                    Log.e("ERR", e.getMessage());
                } catch (InterruptedException e) {
                    Log.e("ERR", e.getMessage());
                }

        mInventoryViewModel.getAllInventories().observe(this, new Observer<List<InventoryWithProducts>>() {
            @Override
            public void onChanged(@Nullable final List<InventoryWithProducts> inventories) {
Log.i("Obs", "IN OBS"+inventories.size());
                for(int i = 0; i < inventories.size(); i++) {
                    System.out.println(inventories.get(i).inventory);
                }
            }
        });

    }

    public void deleteInv(View view) {
mInventoryViewModel.deleteInventory(1);
    }
}