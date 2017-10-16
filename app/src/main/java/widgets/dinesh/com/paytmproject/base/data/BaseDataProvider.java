package widgets.dinesh.com.paytmproject.base.data;



public class BaseDataProvider {
    protected final ExecutionThread executionThread;
    protected final PostExecutionThread postExecutionThread;

    public BaseDataProvider(ExecutionThread executionThread, PostExecutionThread postExecutionThread) {
        this.executionThread = executionThread;
        this.postExecutionThread = postExecutionThread;
    }
}
