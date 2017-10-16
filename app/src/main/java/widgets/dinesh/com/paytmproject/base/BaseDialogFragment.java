package widgets.dinesh.com.paytmproject.base;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;


public abstract class BaseDialogFragment extends AppCompatDialogFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutResource(),container,false);
        ButterKnife.bind(this,view);
        return view;
    }

    public <T extends View> T findView(@IdRes int id) {
        //noinspection ConstantConditions
        return ButterKnife.findById(getView(),id);
    }

    protected abstract int getLayoutResource();


    public void showLoading() {

    }


    public void hideLoading() {

    }


    public void showError(PaytmException exception) {

    }
}
