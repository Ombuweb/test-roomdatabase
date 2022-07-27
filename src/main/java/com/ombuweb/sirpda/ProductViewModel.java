package com.ombuweb.sirpda;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.google.common.util.concurrent.ListenableFuture;

import java.util.List;

public class ProductViewModel extends AndroidViewModel {
    private ProductRepository mProductRepo;
    @NonNull
    @Override
    public <T extends Application> T getApplication() {
        return super.getApplication();
    }

    public ProductViewModel (Application application) {
        super(application);
        mProductRepo = new ProductRepository(application);


    }

    public void  insertProducts(List<Product> products){
        mProductRepo.insertProducts(products);
    }

    public ListenableFuture<Long> insert(Product product){
        return mProductRepo.insert(product);
    }

}
