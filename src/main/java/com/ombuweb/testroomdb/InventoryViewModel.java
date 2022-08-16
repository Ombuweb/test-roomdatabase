package com.ombuweb.testroomdb;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.google.common.util.concurrent.ListenableFuture;

import java.util.List;

public class InventoryViewModel extends AndroidViewModel {

    private InventoryRepository mRepository;

    @NonNull
    @Override
    public <T extends Application> T getApplication() {
        return super.getApplication();
    }

    private LiveData<List<InventoryWithProducts>> mAllInventories;

    public InventoryViewModel(Application application) {
        super(application);
        mRepository = new InventoryRepository(application);
        mAllInventories = mRepository.getAllInventories();

    }

    LiveData<List<InventoryWithProducts>> getAllInventories() {
        return mAllInventories;
    }

public void deleteInventory(Integer id){
        mRepository.deleteInventory(id);
}
    public  ListenableFuture<Long> insert(Inventory inventory) {
//Products

        ListenableFuture<Long> future = mRepository.insert(inventory);
        return future;
    }
}
