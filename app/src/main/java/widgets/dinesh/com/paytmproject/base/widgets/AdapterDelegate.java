package widgets.dinesh.com.paytmproject.base.widgets;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

public interface AdapterDelegate<T> {

    int getItemViewType();

    boolean isForViewType(@NonNull T items, int position);

    @NonNull
    RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent);

    void onBindViewHolder(@NonNull T items, int position, @NonNull RecyclerView.ViewHolder holder);
}
