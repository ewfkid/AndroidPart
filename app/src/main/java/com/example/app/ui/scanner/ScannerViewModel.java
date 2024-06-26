package com.example.app.ui.scanner;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;



public class ScannerViewModel {

    /*private final GetClassOfSortUseCase getClassOfSortUseCase = new GetClassOfSortUseCase(
            ProductRepositoryImpl.getInstance()
    ); */

    /* LiveData */

    private final MutableLiveData<ScannerViewModel.State> mutableStateLiveData = new MutableLiveData<>();

    public final LiveData<ScannerViewModel.State> stateLiveData = mutableStateLiveData;

    /* LiveData */

    public class State {

    }
}
