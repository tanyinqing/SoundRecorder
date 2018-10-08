package frameworkandroid.tan.com.bottomframework.myzxing.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.graphics.Bitmap;
import android.hardware.Camera;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;


import com.google.zxing.BarcodeFormat;
import com.google.zxing.Result;

import java.io.IOException;
import java.util.Vector;

import frameworkandroid.tan.com.bottomframework.R;
import frameworkandroid.tan.com.bottomframework.myzxing.camera.CameraManager;
import frameworkandroid.tan.com.bottomframework.myzxing.decoding.CaptureActivityHandler;
import frameworkandroid.tan.com.bottomframework.myzxing.decoding.InactivityTimer;
import frameworkandroid.tan.com.bottomframework.myzxing.view.ViewfinderView;
import frameworkandroid.tan.com.bottomframework.util.MyLog;
import frameworkandroid.tan.com.bottomframework.widget.MyDialog;


/**
 * Initial the camera
 * @author Ryan.Tang
 */
public class CaptureActivity extends Activity implements Callback {

	private CaptureActivityHandler handler;
	private ViewfinderView viewfinderView;
	private boolean hasSurface;
	private Vector<BarcodeFormat> decodeFormats;
	private String characterSet;
	private InactivityTimer inactivityTimer;
	private MediaPlayer mediaPlayer;
	private boolean playBeep;
	private static final float BEEP_VOLUME = 0.10f;
	private boolean vibrate;
	private ImageButton cancelScanButton;
	//打开手电筒
	private Camera.Parameters parameters;
	private boolean mIsLight = false;
	private Camera camera = null;
	private ImageButton btnOpenLight;
	private Button btnOpenLight1;
	private Bundle bundle;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.camera);
		//ViewUtil.addTopView(getApplicationContext(), this, R.string.scan_card);
		CameraManager.init(getApplication());
		viewfinderView = (ViewfinderView) findViewById(R.id.viewfinder_view);
		cancelScanButton = (ImageButton) this.findViewById(R.id.btn_cancel_scan);
		btnOpenLight1 = (Button) this.findViewById(R.id.btnOpenLight1);
		hasSurface = false;
		inactivityTimer = new InactivityTimer(this);

		btnOpenLight = (ImageButton) this.findViewById(R.id.btnOpenLight);
		btnOpenLight.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				OpenLight();
			}
		});

		bundle=getIntent().getExtras();

	}
// 打开照相机
	private void OpenLight() {
	if (!mIsLight) {
			turnOnFlashLight();
			btnOpenLight1.setText("关闭手电筒");
			mIsLight = true;
		}else {
			turnOffFlashLight();
			btnOpenLight1.setText("打开手电筒");
			mIsLight = false;
		}
	}
	private void turnOnFlashLight() {
//		camera = Camera.open();
		camera = CameraManager.getCamera();
		parameters = camera.getParameters();
		parameters.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
		camera.setParameters(parameters);
		camera.startPreview();
	}
	private void turnOffFlashLight() {
		parameters.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
		camera.setParameters(parameters);
		//camera.stopPreview();
	}


	@SuppressWarnings("deprecation")
	@Override
	protected void onResume() {
		super.onResume();
		SurfaceView surfaceView = (SurfaceView) findViewById(R.id.preview_view);
		SurfaceHolder surfaceHolder = surfaceView.getHolder();
		if (hasSurface) {
			initCamera(surfaceHolder);
		} else {
			surfaceHolder.addCallback(this);
			surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
		}
		decodeFormats = null;
		characterSet = null;

		playBeep = true;
		AudioManager audioService = (AudioManager) getSystemService(AUDIO_SERVICE);
		if (audioService.getRingerMode() != AudioManager.RINGER_MODE_NORMAL) {
			playBeep = false;
		}
		initBeepSound();
		vibrate = true;
		
		//quit the scan view
		cancelScanButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				//断开蓝牙连接
//				if(MainCeLingActivity.bluetooth != null){
//					MainCeLingActivity.bluetooth.stop();
//				}
				CaptureActivity.this.finish();
			}
		});
	}

	@Override
	protected void onPause() {
		super.onPause();
		if (handler != null) {
			handler.quitSynchronously();
			handler = null;
		}
		CameraManager.get().closeDriver();
	}

	@Override
	protected void onDestroy() {
		inactivityTimer.shutdown();
		super.onDestroy();
	}
	
	/**
	 * Handler scan result
	 * @param result
	 * @param barcode
	 */
	public void handleDecode(Result result, Bitmap barcode) {
		inactivityTimer.onActivity();
		playBeepSoundAndVibrate();
		String resultString = result.getText();
		MyLog.ShowLog("扫描到的码是" + resultString);
		//FIXME
		if (resultString.equals("")) {
			//Toast.show(CaptureActivity.this, "Scan failed!", Toast.LENGTH_SHORT);
			Toast.makeText(CaptureActivity.this, "扫码失败!", Toast.LENGTH_SHORT);
		}else {
//			System.out.println("Result:"+resultString);
//			Intent resultIntent = new Intent(CaptureActivity.this,ShipmentInfoActivity.class);
			Intent resultIntent = new Intent();
			resultIntent.setAction("com.tyq.warehouse.activity.CompleteDeliver");
			bundle.putString("result", resultString);
			resultIntent.putExtras(bundle);
			//this.setResult(RESULT_OK, resultIntent);
//			this.startActivity(resultIntent);
			this.startActivityForResult(resultIntent, 0);
		}
	//	CaptureActivity.this.finish();
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		switch (resultCode){
			case RESULT_OK:
//				Intent intent=new Intent(CaptureActivity.this,ShipmentInfoActivity.class);
				Intent intent=new Intent();
				intent.setAction("com.tyq.warehouse.activity.ShipmentInfoActivity");
				setResult(RESULT_OK,intent);
				finish();
				break;
		}
	}

	private void initCamera(SurfaceHolder surfaceHolder) {
		if(surfaceHolder == null){
			return;
		}
		try {
			CameraManager.get().openDriver(surfaceHolder);
		} catch (IOException ioe) {
			showCameraExceptionDialog();
		} catch (RuntimeException e) {
			showCameraExceptionDialog();
		}
		if (handler == null) {
			handler = new CaptureActivityHandler(this, decodeFormats,
					characterSet);
		}
	}

	private void showCameraExceptionDialog() {
		MyDialog dialog = new MyDialog(CaptureActivity.this);
		dialog.setTitle("提示");
		dialog.setMessage("抱歉,相机出问题,请您打开权限"); 
		dialog.setPositiveButton(R.string.ok1, new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				finish();
			}
		});

		dialog.create(null);
		dialog.showMyDialog();
	}
	
	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {

	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		if (!hasSurface) {
			hasSurface = true;
			initCamera(holder);
		}

	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		hasSurface = false;

	}

	public ViewfinderView getViewfinderView() {
		return viewfinderView;
	}

	public Handler getHandler() {
		return handler;
	}

	public void drawViewfinder() {
		viewfinderView.drawViewfinder();

	}

	private void initBeepSound() {
		if (playBeep && mediaPlayer == null) {
			// The volume on STREAM_SYSTEM is not adjustable, and users found it
			// too loud,
			// so we now play on the music stream.
			setVolumeControlStream(AudioManager.STREAM_MUSIC);
			mediaPlayer = new MediaPlayer();
			mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
			mediaPlayer.setOnCompletionListener(beepListener);

			AssetFileDescriptor file = getResources().openRawResourceFd(
					R.raw.beep);
			try {
				mediaPlayer.setDataSource(file.getFileDescriptor(),
						file.getStartOffset(), file.getLength());
				file.close();
				mediaPlayer.setVolume(BEEP_VOLUME, BEEP_VOLUME);
				mediaPlayer.prepare();
			} catch (IOException e) {
				mediaPlayer = null;
			}
		}
	}

	private static final long VIBRATE_DURATION = 200L;

	private void playBeepSoundAndVibrate() {
		if (playBeep && mediaPlayer != null) {
			mediaPlayer.start();
		}
		if (vibrate) {
			Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
			vibrator.vibrate(VIBRATE_DURATION);
		}
	}

	/**
	 * When the beep has finished playing, rewind to queue up another one.
	 */
	private final OnCompletionListener beepListener = new OnCompletionListener() {
		public void onCompletion(MediaPlayer mediaPlayer) {
			mediaPlayer.seekTo(0);
		}
	};

}