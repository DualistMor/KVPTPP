package com.bohdanserdyuk.KVPTPP.view;

import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.bohdanserdyuk.KVPTPP.contract.BaseContract;
import com.bohdanserdyuk.KVPTPP.di.DependencyInjector;
import com.bohdanserdyuk.KVPTPP.di.DependencyInjectorImpl;
import com.lucky_apps.RainViewer.viewLayer.viewModel.BasePresenterViewModel;

public abstract class BaseActivity<V extends BaseContract.View, P extends BaseContract.Presenter<V>> extends AppCompatActivity implements BaseContract.View {

    private final LifecycleRegistry lifecycleRegistry = new LifecycleRegistry(this);
    public final DependencyInjector dependencyInjector = new DependencyInjectorImpl();

    protected P presenter;

    @CallSuper
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BasePresenterViewModel<V, P> presenterViewModel =
            ViewModelProviders.of(this).get(BasePresenterViewModel.class);
        if (presenterViewModel.getPresenter() == null) {
            presenterViewModel.setPresenter(initPresenter());
        }
        presenter = presenterViewModel.getPresenter();
        presenter.attachLifecycle(getLifecycle());
        presenter.attachView((V)this);
    }

    @Override
    public LifecycleRegistry getLifecycle() {
        return lifecycleRegistry;
    }

    @CallSuper
    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachLifecycle(getLifecycle());
        presenter.detachView();
        presenter.detachModels();
    }

    protected abstract P initPresenter();
}