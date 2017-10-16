package widgets.dinesh.com.paytmproject.base.widgets;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import butterknife.BindView;
import widgets.dinesh.com.paytmproject.R;
import widgets.dinesh.com.paytmproject.base.BaseActivity;

/**
 * Created by ajmac1005 on 28/07/17.
 */

public abstract class FullScreenModalActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initToolbar();
    }

    private void initToolbar() {
        setSupportActionBar(this.toolbar);
        //noinspection ConstantConditions

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(getResources().getString(getActivityTitle()));

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    protected abstract int getActivityTitle();
}
