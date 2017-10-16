package widgets.dinesh.com.paytmproject.info;

import widgets.dinesh.com.paytmproject.R;
import widgets.dinesh.com.paytmproject.base.widgets.FullScreenModalActivity;

public class InfoActivity extends FullScreenModalActivity {
    @Override
    protected int getLayoutRes() {
        return R.layout.activity_info;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }


    @Override
    protected int getActivityTitle() {
        return R.string.info_activity_title;
    }
}
