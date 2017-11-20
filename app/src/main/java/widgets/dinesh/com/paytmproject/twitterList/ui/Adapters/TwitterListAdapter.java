package widgets.dinesh.com.paytmproject.twitterList.ui.Adapters;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import widgets.dinesh.com.paytmproject.base.widgets.AdapterDelegate;
import widgets.dinesh.com.paytmproject.base.widgets.ReactiveRecyclerAdapter;

public class TwitterListAdapter extends ReactiveRecyclerAdapter {


    private static final int TWITTER_ITEM = 1;

    public TwitterListAdapter(Context context, List<Object> items) {
        super(context);
        this.items = items;
    }

    @Override
    protected List<AdapterDelegate> initAdapterDelegates() {
        List<AdapterDelegate> adapterDelegates = new ArrayList<>();
        adapterDelegates.add(new TwitterItemAdapterDelegate(TWITTER_ITEM, context));
        return adapterDelegates;
    }
}
