package widgets.dinesh.com.paytmproject.base;


import android.os.Bundle;
import android.support.annotation.Nullable;

import widgets.dinesh.com.paytmproject.R;


public class LoaderDialog extends BaseDialogFragment {

    public static final LoaderDialog INSTANCE = new LoaderDialog();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCancelable(false);
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.loader;
    }
}
