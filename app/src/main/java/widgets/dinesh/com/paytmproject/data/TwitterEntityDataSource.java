package widgets.dinesh.com.paytmproject.data;

import java.util.List;

import io.reactivex.Flowable;
import widgets.dinesh.com.paytmproject.viewModels.TwitterEntity;

/**
 * Created by ajmac1005 on 15/10/17.
 */

public interface TwitterEntityDataSource {
    Flowable<List<TwitterEntity>> getData();
    io.reactivex.Observable<TwitterEntity> addData(TwitterEntity twitterEntity);
}
