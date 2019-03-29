package example.admin.com.myroomdb.needle;

public interface CancelableRunnable extends Runnable {

	void cancel();

	boolean isCanceled();
}
