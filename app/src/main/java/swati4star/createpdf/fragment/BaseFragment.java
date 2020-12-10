package swati4star.createpdf.fragment;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import swati4star.createpdf.providers.viewModel.Provider;
import swati4star.createpdf.providers.viewModel.ViewModelProviderFactory;

public abstract class BaseFragment<T extends ViewModel> extends Fragment {
    private final Class<T> viewModelClass;

    protected T viewModel;

    public BaseFragment(Class<T> viewModelClass, Provider<T> provider) {
        this.viewModelClass = viewModelClass;

        ViewModelProviderFactory.addProvider(viewModelClass, provider);
    }

    @Override
    @CallSuper
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(this, ViewModelProviderFactory.getInstance()).get(viewModelClass);
    }

    protected <D> void observe(LiveData<D> liveData, Observer<D> observer) {
        liveData.observe(getViewLifecycleOwner(), observer);
    }
}
