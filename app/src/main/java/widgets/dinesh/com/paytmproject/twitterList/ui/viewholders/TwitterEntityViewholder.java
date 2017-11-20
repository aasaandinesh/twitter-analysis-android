package widgets.dinesh.com.paytmproject.twitterList.ui.viewholders;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import widgets.dinesh.com.paytmproject.R;
import widgets.dinesh.com.paytmproject.base.widgets.ReactiveViewHolder;
import widgets.dinesh.com.paytmproject.viewModels.TwitterEntity;

public class TwitterEntityViewholder extends ReactiveViewHolder<TwitterEntity> {
    @BindView(R.id.tv_twitter_handle)
    TextView tvTwitterHandle;

    public TwitterEntityViewholder(Context context, View itemView) {
        super(context, itemView);
    }

    @Override
    protected void bindData(TwitterEntity data) {

        tvTwitterHandle.setText(data.getKeyword());
    }
}
