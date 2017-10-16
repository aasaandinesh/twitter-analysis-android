package widgets.dinesh.com.paytmproject.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;


import com.hendraanggrian.rx.activity.RxActivity;

import org.parceler.Parcels;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.disposables.CompositeDisposable;




public abstract class BaseFragment extends Fragment {

    private Unbinder unBinder;
    protected CompositeDisposable disposables = new CompositeDisposable();


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutResource(),container,false);
        this.unBinder = ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.unBinder = null;
    }

    protected abstract @LayoutRes int getLayoutResource();

    public  <T extends View> T findView(@IdRes int id) {
        //noinspection ConstantConditions
        return ButterKnife.findById(getView(),id);
    }

    public void showLoading() {
        showDialogLoading();
    }


    public void hideLoading() {
        hideDialogLoading();
    }

    public void showSnackBar(View parentView, @StringRes int res) {
        Snackbar.make(parentView, res, Snackbar.LENGTH_LONG).show();
    }

    public void showSnackBar(View parentView, String msg) {
        Snackbar.make(parentView, msg, Snackbar.LENGTH_LONG).show();
    }

    protected void closeKeyboard() {
        View view = getActivity().getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    protected void hideError(@IdRes int id) {
        TextInputLayout layout = findView(id);
        layout.setError(null);
        layout.setErrorEnabled(false);
    }

    protected void hideError(TextInputLayout layout) {
        layout.setError(null);
        layout.setErrorEnabled(false);
    }


    protected void showDialog(DialogFragment dialogFragment) {
        try {
            dialogFragment.show(getActivity().getSupportFragmentManager(),dialogFragment.getClass().getName());
        }
        catch (IllegalStateException e){
            e.printStackTrace();
        }

    }


    public void showError(PaytmException exception) {
        try {
            Toast.makeText(getContext(),exception.getMessage(),Toast.LENGTH_SHORT).show();
        }
        catch (NullPointerException e){
            e.printStackTrace();
        }

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        RxActivity.onActivityResult(requestCode, resultCode, data);

    }



    protected int getPixelFromDP(int dp) {
        Resources r = getResources();
        Float px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics());
        return px.intValue();
    }



    public void showError(@StringRes int resId) {
        Toast.makeText(getActivity(),resId,Toast.LENGTH_SHORT).show();
    }

    public void hideDialogLoading() {
        LoaderDialog dialog = (LoaderDialog) getActivity().getSupportFragmentManager()
                .findFragmentByTag
                        (LoaderDialog.class.getName());
        if (dialog != null) dialog.dismiss();
    }

    public void showDialogLoading() {
        LoaderDialog loaderDialog = new LoaderDialog();
        loaderDialog.show(getActivity().getSupportFragmentManager(),LoaderDialog.class.getName());
    }

    @Override
    public void onDetach() {
        super.onDetach();
        disposables.dispose();

    }

    protected  <T>void returnResult(T data, String key){
        Intent intent = getActivity().getIntent().putExtra(key, Parcels.wrap(data));
        getActivity().setResult(Activity.RESULT_OK,intent);

        getActivity().finish();

    }


}
