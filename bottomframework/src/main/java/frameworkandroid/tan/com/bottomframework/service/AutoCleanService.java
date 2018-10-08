package frameworkandroid.tan.com.bottomframework.service;
import java.util.List;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;

import frameworkandroid.tan.com.bottomframework.entity.TaskInfo;
import frameworkandroid.tan.com.bottomframework.util.MyLog;
import frameworkandroid.tan.com.bottomframework.util.TaskInfoProvider;


/**
 * 处理锁屏服务  锁屏后和屏幕解锁后 杀死进程
 * @author qiuzhidi
 *
 */
public class AutoCleanService extends Service {
	private ScreenOffReceiver receiver;
	private ScreenOnReceiver receiverOn;
	private ActivityManager am;
	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}
	@Override
	public void onCreate() {
		am  = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
		receiver = new ScreenOffReceiver();
		receiverOn = new ScreenOnReceiver();
		registerReceiver(receiver,new IntentFilter(Intent.ACTION_SCREEN_OFF));
		registerReceiver(receiverOn,new IntentFilter(Intent.ACTION_SCREEN_ON));
		super.onCreate();
	}
	@Override
	public void onDestroy() {
		unregisterReceiver(receiver);
		receiver = null;
		unregisterReceiver(receiverOn);
		receiverOn = null;
		super.onDestroy();
	}
	//屏幕锁屏
	private class ScreenOffReceiver extends BroadcastReceiver{
		@Override
		public void onReceive(Context context, Intent intent) {
			for (TaskInfo taskInfo: TaskInfoProvider.getTaskInfos(AutoCleanService.this)){
				if (taskInfo.isUserTask()) {
					MyLog.ShowLog("锁屏关闭进程" + taskInfo.toString());
					ActivityManager activityManager= (ActivityManager) AutoCleanService.this.getSystemService(Context.ACTIVITY_SERVICE);
					activityManager.killBackgroundProcesses(taskInfo.getPackname());
				}
			}
		}
	}
	//屏幕解锁
	private class ScreenOnReceiver extends BroadcastReceiver{
		@Override
		public void onReceive(Context context, Intent intent) {
			for (TaskInfo taskInfo: TaskInfoProvider.getTaskInfos(AutoCleanService.this)){
				if (taskInfo.isUserTask()) {
					MyLog.ShowLog("解锁关闭进程" + taskInfo.toString());
					ActivityManager activityManager= (ActivityManager)AutoCleanService.this.getSystemService(Context.ACTIVITY_SERVICE);
					activityManager.killBackgroundProcesses(taskInfo.getPackname());
				}
			}
		}
	}
}

