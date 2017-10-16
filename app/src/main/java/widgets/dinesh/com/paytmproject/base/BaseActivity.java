package widgets.dinesh.com.paytmproject.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.hendraanggrian.rx.activity.RxActivity;

import butterknife.ButterKnife;
import io.reactivex.disposables.CompositeDisposable;


public abstract class BaseActivity extends AppCompatActivity {



    protected CompositeDisposable disposables = new CompositeDisposable();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutRes());
        ButterKnife.bind(this);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    protected abstract @LayoutRes int getLayoutRes();



    public void showError(PaytmException exception){

    }


    public void showLoading() {

    }


    public void hideLoading() {

    }


    public <T extends View> T findView(@IdRes int id) {
        return ButterKnife.findById(this,id);
    }


    public void showSnackBar(View parentView, String msg) {
        Snackbar.make(parentView, msg, Snackbar.LENGTH_LONG).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        RxActivity.onActivityResult(requestCode, resultCode, data);
    }

    public void showError(@StringRes int resId) {
        Toast.makeText(this,resId,Toast.LENGTH_SHORT).show();
    }

    public void hideDialogLoading() {
        LoaderDialog dialog = (LoaderDialog) getSupportFragmentManager().findFragmentByTag(LoaderDialog.class.getName());
        if (dialog != null) dialog.dismiss();
    }

    public void showDialogLoading() {
        LoaderDialog loaderDialog = new LoaderDialog();
        loaderDialog.show(getSupportFragmentManager(),LoaderDialog.class.getName());

    }


    protected void hideError(@IdRes int id) {
        TextInputLayout layout = findView(id);
        layout.setError(null);
        layout.setErrorEnabled(false);
    }

    protected void showErrorInTil(@IdRes int viewId, String error) {
        TextInputLayout textInputLayout = findView(viewId);
        textInputLayout.setErrorEnabled(true);
        textInputLayout.setError(error);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        disposables.dispose();
    }
}
