package frameworkandroid.tan.com.bottomframework.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.animation.Transformation;
import android.widget.Gallery;

import frameworkandroid.tan.com.bottomframework.util.MyLog;


public class ImageGallery extends Gallery {
    public ImageGallery(Context context) {
        super(context);
    }


    public ImageGallery(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    public ImageGallery(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }


    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
                           float velocityY) {
        boolean left = isFlingLeft(e1, e2);
        int keyEvent;
        if (left) {
            keyEvent = KeyEvent.KEYCODE_DPAD_LEFT;
        } else {
            keyEvent = KeyEvent.KEYCODE_DPAD_RIGHT;
        }
        onKeyDown(keyEvent, null);
// 返回false或true 即可去除滑动时的惯性
        return false;
    }


    // 设置只要手指在界面上滑动就可以切换内容，不需要滑动一半才换页
    public boolean isFlingLeft(MotionEvent e1, MotionEvent e2) {
        return e1.getX() < e2.getX();
    }





    /*  这个是仓库端采用的版本
    private float mLastMotionX;//滑动过程中，x方向的初始坐标
    private float mLastMotionY;//滑动过程中，y方向的初始坐标
    private int mTouchSlop;//手指大小的距离
    private Boolean Flag=true;//手指大小的距离


     *//*
     * 三个构造函数
     * *//*

    public ImageGallery(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public ImageGallery(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ImageGallery(Context context) {
        super(context);
        init();
    }

     *//*
     * 初始化，设置x方向移动的最小距离为手指大小时，拦截处理事件,可以设置需要的大小
     * *//*

    private void init() {
        final ViewConfiguration configuration = ViewConfiguration
                .get(getContext());
        mTouchSlop = configuration.getScaledTouchSlop()/6;
    }


     *//*
     *   重写的方法，最关键的地方
     *//*

    public boolean onInterceptTouchEvent(MotionEvent ev) {
        final int action = ev.getAction();//获取触摸事件类型
        final float x = ev.getX();//每次触摸事件的x坐标
        final float y = ev.getY();//每次触摸事件的y坐标
        switch (action) {
            case MotionEvent.ACTION_DOWN://按下事件
                MyLog.ShowLog("按下事件");
                mLastMotionX = x;//初始化每次触摸事件的x方向的初始坐标，即手指按下的x方向坐标
                mLastMotionY = y;//初始化每次触摸事件的y方向的初始坐标，即手指按下的y方向坐标
              break;
            case MotionEvent.ACTION_UP://按下事件
                MyLog.ShowLog("抬起事件");
                if (mLastMotionX ==  x&&mLastMotionY==y) {
                    MyLog.ShowLog("抬起事件响应 将此次触摸事件传递给子View");
                    return false;
                }
              break;

            case MotionEvent.ACTION_MOVE:
                MyLog.ShowLog("移动事件");
                final int deltaX = (int) (mLastMotionX - x);//每次滑动事件x方向坐标与触摸事件x方向初始坐标的距离
                final int deltaY = (int) (mLastMotionY - y);//每次滑动事件y方向坐标与触摸事件y方向初始坐标的距离
//               boolean xMoved = Math.abs(deltaX) > mTouchSlop && Math.abs(deltaY/deltaX) < 1;
               boolean xMoved = Math.abs(deltaX) > mTouchSlop;
                MyLog.ShowLog("deltaX="+deltaX+"  mTouchSlop="+mTouchSlop+"  deltaY="+deltaY+"  xMoved="+xMoved);
               if (xMoved) { //Gallery需要消费掉此次触摸事件
                   MyLog.ShowLog("移动事件响应 触摸事件自己使用");
                   //              MyLog.ShowLog("移动事件响应 将此次触摸事件传递给子View");
                   return true;//返回true就不会将此次触摸事件传递给子View了，我的业务中是ListView
                }

                break;
        }
        MyLog.ShowLog("方法响应 将此次触摸事件传递给子View");
        //       MyLog.ShowLog("方法响应 触摸事件自己使用");
           return false;//false 将此次触摸事件传递给子View，即ListView
    }


    public boolean onScroll(MotionEvent paramMotionEvent1, MotionEvent paramMotionEvent2, float paramFloat1, float paramFloat2) {
        float f = 1.1F * paramFloat1;
        return super.onScroll(paramMotionEvent1, paramMotionEvent2, f, paramFloat2);
    }*/





    /*
    这个是原来的版本
    public boolean onScroll(MotionEvent paramMotionEvent1, MotionEvent paramMotionEvent2, float paramFloat1, float paramFloat2) {
        float f = 1.4F * paramFloat1;
        return super.onScroll(paramMotionEvent1, paramMotionEvent2, f, paramFloat2);
    }*/

  /*  @Override
    protected boolean getChildStaticTransformation(View child, Transformation t) {
        return true;
    }*/
/*
    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }*/
}
