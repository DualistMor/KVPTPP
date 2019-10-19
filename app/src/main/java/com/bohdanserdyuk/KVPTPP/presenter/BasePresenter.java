package com.bohdanserdyuk.KVPTPP.presenter;

import android.os.Bundle;

import com.bohdanserdyuk.KVPTPP.contract.BaseContract;
import com.bohdanserdyuk.KVPTPP.view.BaseActivity;
import com.bohdanserdyuk.KVPTPP.viewModel.BaseModelViewModel;

import org.jetbrains.annotations.NotNull;

import androidx.annotation.CallSuper;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.lifecycle.ViewModelProviders;

public abstract class BasePresenter<V extends BaseContract.View, M extends BaseContract.Models> implements LifecycleObserver, BaseContract.Presenter<V> {

    public M model;
    private V view;
    private Bundle stateBundle;

    public BasePresenter(M model) {
        this.model = model;
    }

    @OnLifecycleEvent(value = Lifecycle.Event.ON_CREATE)
    public void onCreate() {
        BaseModelViewModel<M> modelViewModel = ((view instanceof Fragment)
            ? ViewModelProviders.of((Fragment) view)
            : ViewModelProviders.of((BaseActivity) view)).get(BaseModelViewModel.class);
        if (modelViewModel.getModels() == null) {
            modelViewModel.setModels(model);
        }
        else {
            model = modelViewModel.getModels();
        }
    }

    @Override
    final public V getView() {
        return view;
    }

    @Override
    final public <T extends BaseContract.Model> T getModel(@NonNull Class<T> modelClass) {
        for (BaseContract.Model m : model.getModels()) {
            if (modelClass.isInstance(m)) {
                return (T) m;
            }
        }
        return null;
    }

    @NotNull
    @Override
    final public BaseContract.Model[] getModels() {
        return model.getModels();
    }

    @Override
    final public void attachLifecycle(Lifecycle lifecycle) { lifecycle.addObserver(this); }

    @Override
    final public void detachLifecycle(Lifecycle lifecycle) {
        lifecycle.removeObserver(this);
    }

    @Override
    final public void attachView(V view) {
        this.view = view;
    }

    @Override
    final public void detachView() {
        view = null;
    }

    @Override
    final public void detachModels() {
        model = null;
    }

    @Override
    final public boolean isViewAttached() {
        return view != null;
    }

    @Override
    final public Bundle getStateBundle() {
        return stateBundle == null ? stateBundle = new Bundle() : stateBundle;
    }

    @CallSuper
    @Override
    final public void onPresenterDestroy() {
        if (stateBundle != null && !stateBundle.isEmpty()) {
            stateBundle.clear();
        }
    }
}