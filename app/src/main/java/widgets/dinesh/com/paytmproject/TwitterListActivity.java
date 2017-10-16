package widgets.dinesh.com.paytmproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import widgets.dinesh.com.paytmproject.addEntity.ui.AddTwitterEntityActivity;
import widgets.dinesh.com.paytmproject.analysis.ui.MainActivity;
import widgets.dinesh.com.paytmproject.base.BaseActivity;
import widgets.dinesh.com.paytmproject.base.di.RxModule;
import widgets.dinesh.com.paytmproject.di.DaggerTwitterListComponent;
import widgets.dinesh.com.paytmproject.di.TwitterListModule;
import widgets.dinesh.com.paytmproject.info.InfoActivity;
import widgets.dinesh.com.paytmproject.twitterList.ui.TwitterListAdapter;
import widgets.dinesh.com.paytmproject.viewModels.TwitterEntitiesViewModel;
import widgets.dinesh.com.paytmproject.viewModels.TwitterEntity;

public class TwitterListActivity extends BaseActivity {



    @BindView(R.id.rv_twitter_list)
    RecyclerView rvTwitterList;

    @OnClick(R.id.fab_add_entity)
    void onFabClicked(){
        Intent intent = new Intent(this, AddTwitterEntityActivity.class);
        startActivity(intent);
    }

    @Inject
    TwitterEntitiesViewModel twitterEntitiesViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        //getSupportFragmentManager().beginTransaction().add(R.id.container,TwitterListFragment.newInstance("","")).commit();
        DaggerTwitterListComponent
                .builder()
                .applicationComponent(PaytmApplication.getComponent())
                .rxModule(new RxModule())
                .twitterListModule(new TwitterListModule(this))
                .build()
                .inject(this);
                setTitle("Keywords");
        twitterEntitiesViewModel.getTwitterLiveData()

                .observe(this, this::updateData)
        ;




    }

    private void updateData(List<TwitterEntity> twitterEntities) {
        List<Object> items = new ArrayList<>();
        items.addAll(twitterEntities);
        TwitterListAdapter twitterListAdapter = new TwitterListAdapter(this, items);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvTwitterList.setLayoutManager(layoutManager);
        rvTwitterList.setAdapter(twitterListAdapter);
        rvTwitterList.getAdapter().notifyDataSetChanged();
        ((TwitterListAdapter)rvTwitterList.getAdapter()).getViewClickedObservable()
        .subscribe(obj -> {
            TwitterEntity twitterEntity = (TwitterEntity)obj;
            Intent intent = new Intent(TwitterListActivity.this, MainActivity.class);
            intent.putExtra("keyword", twitterEntity.getKeyword());
            startActivity(intent);
        });
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_twitter_list;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

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
