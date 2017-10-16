package widgets.dinesh.com.paytmproject.viewModels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.LiveDataReactiveStreams;
import android.arch.lifecycle.ViewModel;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import widgets.dinesh.com.paytmproject.data.TwitterEntityDataSource;

public class TwitterEntitiesViewModel extends ViewModel {
    private final TwitterEntityDataSource dataSource;

    private LiveData<List<TwitterEntity>> twitterLiveData;

    @Inject
    public TwitterEntitiesViewModel(TwitterEntityDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Observable<TwitterEntity> addTwitterEntity(TwitterEntity twitterEntity){
        return dataSource.addData(twitterEntity);

    }

    public LiveData<List<TwitterEntity>> getTwitterLiveData() {
        if(twitterLiveData == null){
            twitterLiveData = LiveDataReactiveStreams.fromPublisher(dataSource.getData());
        }
        return twitterLiveData;
    }
}
