package com.bohdanserdyuk.KVPTPP.view;

import android.os.Bundle;

import com.bohdanserdyuk.KVPTPP.KVPTPPAplication;
import com.bohdanserdyuk.KVPTPP.contract.BaseContract;
import com.bohdanserdyuk.KVPTPP.viewModel.BasePresenterViewModel;
import com.squareup.leakcanary.RefWatcher;

import javax.inject.Inject;

import androidx.annotation.CallSuper;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

public abstract class BaseActivity<V extends BaseContract.View, P extends BaseContract.Presenter<V>> extends AppCompatActivity implements BaseContract.View {
    @Inject
    public P presenter;

    @CallSuper
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BasePresenterViewModel<V, P> presenterViewModel =
            ViewModelProviders.of(this).get(BasePresenterViewModel.class);
        if (presenterViewModel.getPresenter() == null) {
            presenterViewModel.setPresenter(presenter);
        }
        else {
            presenter = presenterViewModel.getPresenter();
        }
        presenter.attachLifecycle(getLifecycle());
        presenter.attachView((V) this);
    }

    @Override
    public void fillSupportActionBar(int stringId, boolean isBackArrow) {}

    @CallSuper
    @Override
    protected void onDestroy() {
        super.onDestroy();
        RefWatcher refWatcher = KVPTPPAplication.Companion.getRefWatcher(getApplicationContext());
        refWatcher.watch(this);

        presenter.detachLifecycle(getLifecycle());
        presenter.detachView();
        presenter.detachModels();
    }

}