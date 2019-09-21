package com.bohdanserdyuk.KVPTPP.presenter;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

import com.bohdanserdyuk.KVPTPP.contract.BaseContract;
import com.bohdanserdyuk.KVPTPP.di.DependencyInjector;
import com.bohdanserdyuk.KVPTPP.di.DependencyInjectorImpl;
import com.bohdanserdyuk.KVPTPP.view.BaseActivity;
import com.bohdanserdyuk.KVPTPP.viewModel.BaseModelViewModel;

import org.jetbrains.annotations.NotNull;

public abstract class BasePresenter<V extends BaseContract.View> implements LifecycleObserver, BaseContract.Presenter<V> {

    private V view;
    private BaseContract.Model[] models;
    private Bundle stateBundle;
    public DependencyInjector dependencyInjector = new DependencyInjectorImpl();

    @OnLifecycleEvent(value = Lifecycle.Event.ON_CREATE)
    public void onCreate() {
        BaseModelViewModel<BaseContract.Model> modelViewModel = ((view instanceof Fragment)
            ? ViewModelProviders.of((Fragment) view)
            : ViewModelProviders.of((BaseActivity) view)).get(BaseModelViewModel.class);
        if (modelViewModel.getModels() == null) {
            modelViewModel.setModels(initModels());
        }
        models = modelViewModel.getModels();
    }

    @Override
    final public V getView() {
        return view;
    }

    @Override
    final public <T extends BaseContract.Model> T getModel(@NonNull Class<T> modelClass) {
        for (BaseContract.Model m : models) {
            if (modelClass.isInstance(m)) {
                return (T) m;
            }
        }
        return null;
    }

    @NotNull
    @Override
    final public BaseContract.Model[] getModels() {
        return models;
    }

    @Override
    final public void attachLifecycle(Lifecycle lifecycle) {
        lifecycle.addObserver(this);
    }

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
    public abstract BaseContract.Model[] initModels();

    @Override
    final public void detachModels() {
        models = null;
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