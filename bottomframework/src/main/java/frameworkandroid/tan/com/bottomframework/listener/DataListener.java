package frameworkandroid.tan.com.bottomframework.listener;

/**
 * 数据相关
 * @author 志强
 *
 * @param <T>
 */
public class DataListener<T> {

	public void onSuccess() {
	};

	public void onSuccess(T data) {
	};

	public void onFailed() {
	};

	public void onFailed(T data) {
	};
}
