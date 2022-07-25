package com.ombuweb.sirpda;

import android.app.Application;
import android.util.Log;

import androidx.concurrent.futures.CallbackToFutureAdapter;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.google.common.util.concurrent.ListenableFuture;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class InventoryViewModel extends AndroidViewModel {

    private InventoryRepository mRepository;

    private LiveData<List<Inventory>> mAllInventories;

    public InventoryViewModel (Application application) {
        super(application);
        mRepository = new InventoryRepository(application);
        mAllInventories = mRepository.getAllInventories();
    }

    LiveData<List<Inventory>> getAllInventories() { return mAllInventories; }

    void insert(Inventory inventory) {

        ListenableFuture<Long> future = mRepository.insert(inventory);

        try {
            Log.i("RUUM", future.get().toString());
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}