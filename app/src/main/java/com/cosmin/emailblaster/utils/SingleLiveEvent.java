package com.cosmin.emailblaster.utils;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import java.util.concurrent.atomic.AtomicBoolean;

public class SingleLiveEvent<T> extends MutableLiveData<T> {
    private final AtomicBoolean mPending = new AtomicBoolean(false);


    @MainThread
    public void observe(@NonNull LifecycleOwner owner, @NonNull Observer<? super T> observer) {
        // Observe the internal MutableLiveData
        super.observe(owner, obs ->{
            if (mPending.compareAndSet(true, false)) {
                observer.onChanged(obs);
            }
        });
    }


    @MainThread
    public void setValue(T t) {
        mPending.set(true);
        super.setValue(t);
    }


    /**
     * Util function for Void implementations.
     */
    public void call() {
        this.setValue( null );
    }
}
