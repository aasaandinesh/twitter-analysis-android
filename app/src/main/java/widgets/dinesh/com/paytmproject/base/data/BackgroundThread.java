package widgets.dinesh.com.paytmproject.base.data;

import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;

public class BackgroundThread implements ExecutionThread {
    @Override
    public Scheduler getScheduler() {
        return Schedulers.newThread();
    }
}
