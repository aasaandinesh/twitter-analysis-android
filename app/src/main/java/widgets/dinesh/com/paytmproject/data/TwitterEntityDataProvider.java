package widgets.dinesh.com.paytmproject.data;

import android.util.Log;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import widgets.dinesh.com.paytmproject.APIService;
import widgets.dinesh.com.paytmproject.base.data.BaseDataProvider;
import widgets.dinesh.com.paytmproject.base.data.ExecutionThread;
import widgets.dinesh.com.paytmproject.base.data.PostExecutionThread;
import widgets.dinesh.com.paytmproject.data.disc.TwitterEntityDAO;
import widgets.dinesh.com.paytmproject.viewModels.TwitterEntity;

/**
 * Created by ajmac1005 on 15/10/17.
 */

public class TwitterEntityDataProvider extends BaseDataProvider implements TwitterEntityDataSource {
    private final TwitterEntityDAO twitterEntityDAO;
    private final APIService apiService;

    @Inject
    public TwitterEntityDataProvider(TwitterEntityDAO twitterEntityDAO, APIService apiService,
                                     ExecutionThread executionThread,
                                     PostExecutionThread postExecutionThread) {
        super(executionThread, postExecutionThread);
        this.twitterEntityDAO = twitterEntityDAO;
        this.apiService = apiService;
    }

    /*
        As and when the data is requested, we return everything from DB immediately so that the UI is
        not blocked at all. At the same time, we make a request to the server to get fresh data and
        save that data in the DB. On update of which the UI will get notified.
         */
    @Override
    public Flowable<List<TwitterEntity>> getData() {
        apiService.getTwitterEntities().
                subscribeOn(executionThread.getScheduler()).
                observeOn(postExecutionThread.getScheduler())


                .subscribe(twitterEntities -> {
                    Observable.fromCallable(() -> {
                        twitterEntityDAO.insert(twitterEntities);
                                return twitterEntities;
                    })
                    .subscribeOn(executionThread.getScheduler())
                    .observeOn(postExecutionThread.getScheduler())
                    .subscribe();
                }, error -> Log.e("API Error", "Something went wrong"));
        return twitterEntityDAO.getAll();

    }

    @Override
    public Observable<TwitterEntity> addData(TwitterEntity twitterEntity) {
        return apiService.addTwitterEntity(twitterEntity).
                subscribeOn(executionThread.getScheduler())
                .observeOn(postExecutionThread.getScheduler());
    }
}
