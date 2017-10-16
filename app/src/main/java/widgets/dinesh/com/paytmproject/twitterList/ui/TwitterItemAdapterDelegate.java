package widgets.dinesh.com.paytmproject.twitterList.ui;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

import widgets.dinesh.com.paytmproject.R;
import widgets.dinesh.com.paytmproject.base.widgets.AbstractAdapterDelegate;
import widgets.dinesh.com.paytmproject.viewModels.TwitterEntity;

class TwitterItemAdapterDelegate extends AbstractAdapterDelegate<List<TwitterEntity>> {

    public TwitterItemAdapterDelegate(int viewType, Context context) {
        super(viewType, context);
    }

    @Override
    public boolean isForViewType(@NonNull List<TwitterEntity> items, int position) {
        return true;
    }

    @Override
    public void onBindViewHolder(@NonNull List<TwitterEntity> items, int position, @NonNull RecyclerView.ViewHolder holder) {
        ((TwitterEntityViewholder)holder).bind(items.get(position));
    }

    @Override
    protected RecyclerView.ViewHolder getViewHolder(View itemView) {
        return new TwitterEntityViewholder(context, itemView);
    }

    @Override
    protected int getViewHolderLayout() {
        return R.layout.twitter_entity_card;
    }
}
