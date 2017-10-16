package widgets.dinesh.com.paytmproject.addEntity.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import com.jakewharton.rxbinding2.view.RxView;

import javax.inject.Inject;

import butterknife.BindView;
import widgets.dinesh.com.paytmproject.PaytmApplication;
import widgets.dinesh.com.paytmproject.R;
import widgets.dinesh.com.paytmproject.addEntity.di.AddEntityModule;
import widgets.dinesh.com.paytmproject.addEntity.di.DaggerAddEntityComponent;
import widgets.dinesh.com.paytmproject.base.BaseActivity;
import widgets.dinesh.com.paytmproject.base.di.RxModule;
import widgets.dinesh.com.paytmproject.base.widgets.RxDialogFragment;
import widgets.dinesh.com.paytmproject.info.InfoActivity;
import widgets.dinesh.com.paytmproject.viewModels.TwitterEntitiesViewModel;
import widgets.dinesh.com.paytmproject.viewModels.TwitterEntity;

/**
 * An Activity to add Twitter Keywords.
 */
public class AddTwitterEntityActivity extends BaseActivity {
    @Inject
    TwitterEntitiesViewModel twitterEntitiesViewModel;

    @BindView(R.id.et_keyword)
    EditText etKeyword;

    @BindView(R.id.bt_add_entity)
    Button addEntity;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_add_twitter_entity;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DaggerAddEntityComponent
                .builder()
                .applicationComponent(PaytmApplication.getComponent())
                .addEntityModule(new AddEntityModule(this))
                .rxModule(new RxModule())
                .build()
                .inject(this);
        handleAddClick();
        setTitle("Add Entity");

    }

    private void handleAddClick() {


        disposables.add(RxView.clicks(addEntity)
                .filter(o -> etKeyword.getText()!=null)
                .switchMap(o -> {
                    TwitterEntity twitterEntity = new TwitterEntity();
                    twitterEntity.setKeyword(etKeyword.getText().toString());
                    return twitterEntitiesViewModel.addTwitterEntity(twitterEntity);
                })
                .switchMap(twitterEntity -> {
                    EntityAddedDialog entityAddedDialog = new EntityAddedDialog();
                    entityAddedDialog.show(getSupportFragmentManager(),"sds");
                    return entityAddedDialog.lifecycle();
                })
                .filter(lifecycleEvent -> lifecycleEvent== RxDialogFragment.LifecycleEvent.STOP)
                .subscribe(lifecycleEvent -> closeActivity())
        );
    }

    private void closeActivity() {
        finish();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
            case R.id.action_info:
                openInfoActivity();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void openInfoActivity() {
        Intent intent = new Intent(this, InfoActivity.class);
        startActivity(intent);
    }
}
