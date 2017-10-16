package widgets.dinesh.com.paytmproject.analysis.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;

import javax.inject.Inject;

import widgets.dinesh.com.paytmproject.PaytmApplication;
import widgets.dinesh.com.paytmproject.R;
import widgets.dinesh.com.paytmproject.analysis.di.AnalysisModule;
import widgets.dinesh.com.paytmproject.analysis.di.DaggerAnalysisComponent;
import widgets.dinesh.com.paytmproject.analysis.viewModels.SentimentalAnalysisData;
import widgets.dinesh.com.paytmproject.analysis.viewModels.SentimentalAnalysisViewModel;
import widgets.dinesh.com.paytmproject.base.BaseActivity;
import widgets.dinesh.com.paytmproject.base.di.RxModule;
import widgets.dinesh.com.paytmproject.info.InfoActivity;

public class MainActivity extends BaseActivity implements OnMapReadyCallback {

    @Inject
    SentimentalAnalysisViewModel viewModel;

    private static final int MAX_RADIUS = 200000;
    private static final float MAX_COUNT = 999;
    private static final int MAX_COLOR = 255;
    private static final int MIN_SCORE = -1;
    private static final int MAX_SCORE = 1;
    private String keyword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DaggerAnalysisComponent
                .builder()
                .applicationComponent(PaytmApplication.getComponent())
                .analysisModule(new AnalysisModule(this))
                .rxModule(new RxModule())
                .build()
                .inject(this);
        keyword  = getIntent().getStringExtra("keyword");
        setTitle(String.format("Result for %s", keyword));
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    private GoogleMap mMap;



    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(21.1458, 79.0882)));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(5));
        viewModel.getAnalysisLiveData(keyword)
                .observe(this, sentimentalAnalysisData -> {
                    if (sentimentalAnalysisData != null) {
                        for (SentimentalAnalysisData d : sentimentalAnalysisData) {
                            float radius = getNormalizedRadius(d);
                            int color = getNormalizedColor(d);
                            LatLng latLng = new LatLng(d.getLatitude(), d.getLongitude());
                            CircleOptions circleOptions = new CircleOptions().center(latLng).
                                    radius(radius).strokeColor(color).fillColor(color);
                            mMap.addCircle(circleOptions);
                        }
                    }
                });

    }

    private int getNormalizedColor(SentimentalAnalysisData d) {
        float red=0,green=0,blue=0;

        if(d.getScore()>0){
            green =  (d.getScore()/MAX_SCORE)*MAX_COLOR;
        }
        if(d.getScore()<0){
            red =  (d.getScore()/MAX_SCORE)*MAX_COLOR;
        }
        if(d.getScore()==0){
            blue = 0;
        }


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            return Color.rgb(red, green, blue);
        }
        else {
            return Color.rgb((int) red, (int) green, (int)blue);
        }
    }

    private float getNormalizedRadius(SentimentalAnalysisData d) {
        return (d.getCount()/MAX_COUNT)*MAX_RADIUS;
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

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_main;
    }

    private void openInfoActivity() {
        Intent intent = new Intent(this, InfoActivity.class);
        startActivity(intent);
    }
}
