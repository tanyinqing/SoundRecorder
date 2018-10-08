package frameworkandroid.tan.com.bottomframework.activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import frameworkandroid.tan.com.bottomframework.Constant;
import frameworkandroid.tan.com.bottomframework.R;
import frameworkandroid.tan.com.bottomframework.ServiceApplication;
import frameworkandroid.tan.com.bottomframework.util.DabaCopy;


/**
 *
 *
 * 标题的内容添加左右标题的代码
 *  mLinearTitleContent.addView(mButtonLeft);
 *  mLinearTitleContent.addView(mImgeTitle);
 *  mLinearTitleContent.addView(mButtonRight);
 *
 *  如果有的页面实在标题不行，可以隐藏掉标题，自己写一个xml的标题
 */
public abstract class TitleBarActivity extends BaseActivity{
    protected LinearLayout mLinearTitle;//代表了标题部分
    protected LinearLayout mLinearDivider;//这个是分割线
    private LinearLayout mLinearDivider2;//这个是系统栏的部分
    private LinearLayout mMainView;//代表了界面的内容部分 采用了
    private FrameLayout mContentView;//代表了全部内容
    private LayoutInflater mLayoutInflater;//界面转化器
    public TextView mTextViewRight;

    // 这个是左右按钮 中间标题栏 用一个相对布局进行包裹
    protected RelativeLayout mLinearTitleContent;//代表标题中的内容部分
    private ImageButton mButtonLeft;
    private TextView mImgeTitle;
    protected  ImageButton mButtonRight;
    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }
    protected void createView() {
        System.out.println(" -----> initView");
        mMainView = new LinearLayout(this);
        mMainView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        mMainView.setOrientation(LinearLayout.VERTICAL);

        initTitleView();
        mMainView.addView(mLinearTitle);
        mContentView = (FrameLayout) getWindow().getDecorView().findViewById(android.R.id.content);
        View v = null;
        if (mContentView.getChildCount() > 0) {
            v = mContentView.getChildAt(0);
            ViewGroup parent = (ViewGroup) v.getParent();
            if (parent != null) {
                parent.removeView(v);
            }
            mMainView.addView(v);
        }
        setContentView(mMainView, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));

    }

    /**
     * 设置title
     */
    public void setTitle(String resid) {
        mLinearTitleContent.setVisibility(View.VISIBLE);
        mLinearDivider.setVisibility(View.VISIBLE);
        mImgeTitle.setText(resid);
    }
    /**
     * 隐藏标题栏
     */
    public void setNoTitle() {
        mLinearTitleContent.setVisibility(View.GONE);
        mLinearDivider.setVisibility(View.GONE);

    }

    /**
     * 设置左侧按钮
     *
     * @param content
     * @param resid
     * @param listener
     */
    public void setButtonLeft(String content, int resid, View.OnClickListener listener) {
//        mButtonLeft.setText(content);
        mButtonLeft.setImageResource(resid);
        mButtonLeft.setOnClickListener(listener);
    }

    /**
     * 设置左侧按钮
     *
     * @param content
     * @param
     */
    public void setButtonLeft(int content) {
        if (content == 1) {
            mButtonLeft.setVisibility(View.GONE);
        }else {
            mButtonLeft.setImageResource(content);
            mButtonLeft.setVisibility(View.VISIBLE);
        }
        //mButtonLeft.setText(content);
    }

    /**
     * 设置右侧按钮
     *
     * @param content
     * @param resid
     */
    public void setButtonRight(String content, int resid) {
//        mButtonRight.setText(content);
        if (resid == 1) {
            mButtonRight.setVisibility(View.GONE);
        }else {
            mButtonRight.setImageResource(resid);
            mButtonRight.setVisibility(View.VISIBLE);
        }

    }

    /**
     * 设置右侧按钮
     *
     * @param content
     * @param
     */
    public void setButtonRight(String content) {
        if ("".equals(content)) {
            mButtonRight.setVisibility(View.GONE);
        } else {
         //   mButtonRight.setText(content);
            mButtonRight.setVisibility(View.VISIBLE);
        }
    }

    void initTitleView() {
        if (mLinearTitle == null) {
//        if (convertView != null) {
//            convertView=mLayoutInflater.inflate(R.layout.base_title, null);
            mLayoutInflater= LayoutInflater.from(this);
            mLinearTitle= (LinearLayout) mLayoutInflater.inflate(R.layout.base_title, null);
            mLinearDivider= (LinearLayout) mLinearTitle.findViewById(R.id.mLinearDivider);
            mLinearTitleContent= (RelativeLayout) mLinearTitle.findViewById(R.id.mLinearTitleContent);
            mLinearDivider2= (LinearLayout) mLinearTitle.findViewById(R.id.mLinearDivider2);
            mButtonLeft= (ImageButton) mLinearTitle.findViewById(R.id.mButtonLeft);
            mButtonLeft.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View arg0) {
                    LeftButtonClicked();
                }
            });
            mButtonRight= (ImageButton) mLinearTitle.findViewById(R.id.mButtonRight);
            mButtonRight.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View arg0) {
                    RightButtonClicked();
                }
            });
            mTextViewRight= (TextView) mLinearTitle.findViewById(R.id.mTextViewRight);
            mImgeTitle= (TextView) mLinearTitle.findViewById(R.id.mImgeTitle);
            //按钮加入 3次点击的监听
            mImgeTitle.setOnClickListener(new View.OnClickListener() {
                //需要监听几次点击事件数组的长度就为几
                //如果要监听双击事件则数组长度为2，如果要监听3次连续点击事件则数组长度为3...
                long[] mHints = new long[2];//初始全部为0

                @Override
                public void onClick(View v) {
                    //将mHints数组内的所有元素左移一个位置
                    System.arraycopy(mHints, 1, mHints, 0,
                            mHints.length - 1);
                    //获得当前系统已经启动的时间
                    mHints[mHints.length - 1] =
                            SystemClock.uptimeMillis();
                    if (SystemClock.uptimeMillis() - mHints[0] <= 300) {
                        //跳转到自定义的开发者页面
//                      startActivityByClass(Develope.class);
                        //复制数据库
//                        DabaCopy.copy(TitleBarActivity.this, Constant.program_name);
                        if (Constant.CeShiBan_true) {
                          /*  Constant.CeShiBan_true =false;
                            ServiceApplication.getInstance().getPreferenceUtil().putSetting("CeShiBan_true", false);
                            Toast.makeText(TitleBarActivity.this, "正式版打开", Toast.LENGTH_SHORT).show();*/
                        }else  {
                           /* Constant.CeShiBan_true =true;
                            ServiceApplication.getInstance().getPreferenceUtil().putSetting("CeShiBan_true", true);
                            Toast.makeText(TitleBarActivity.this, "测试版打开", Toast.LENGTH_SHORT).show();*/
                        }
                        startActivityByClass(DevelopActivity.class);
//                        startActivityByClass(MyWidgetTextActivity.class);
                    }
                }
            });
        }
      /*  *//* title *//*
        mLinearTitle = new LinearLayout(this);
        LayoutParams mTitleLayoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, DensityUtil.dip2px(this, 69));
        mLinearTitle.setLayoutParams(mTitleLayoutParams);
        mLinearTitle.setOrientation(LinearLayout.VERTICAL);
        mLinearTitle.setBackgroundColor(PublicUtil.getResColor(R.color.background3));

        *//* start title divider  这个是分割线，高位1像素*//*
        mLinearDivider = new LinearLayout(this);
        LayoutParams mTitleDividerParams = new LayoutParams(LayoutParams.MATCH_PARENT, DensityUtil.dip2px(this, 1));
        mLinearDivider.setLayoutParams(mTitleDividerParams);
        mLinearDivider.setBackgroundColor(PublicUtil.getResColor(R.color.background3));
        *//* end title divider*//*
        *//* start title divider  这个是系统栏分割线*//*
        mLinearDivider2 = new LinearLayout(this);
        LayoutParams mTitleDividerParams2 = new LayoutParams(LayoutParams.MATCH_PARENT, DensityUtil.dip2px(this, 20));
        mLinearDivider2.setLayoutParams(mTitleDividerParams2);
        mLinearDivider2.setBackgroundColor(PublicUtil.getResColor(R.color.background2));
        *//* end title divider*//*

        *//* start title content  这个是标题内容的布局  高被限制在48像素高*//*
        mLinearTitleContent = new RelativeLayout(this);
        RelativeLayout.LayoutParams mTitleContentLayoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, DensityUtil.dip2px(this, 48));
        // mTitleContentLayoutParams.gravity = Gravity.CLIP_VERTICAL;
        // mLinearTitleContent.setOrientation(LinearLayout.HORIZONTAL);
        mLinearTitleContent.setLayoutParams(mTitleContentLayoutParams);
        // mLinearTitleContent.setBackgroundColor(Color.DKGRAY);
        // mLinearTitleContent.setBackgroundColor(PublicUtil.getResColor(R.color.saletoexamineaar_biaoTiLan_color));
        mLinearTitleContent.setGravity(Gravity.CENTER_VERTICAL);

//这个是左边的按钮
        LayoutParams buttonParamsLeft = new LayoutParams(LayoutParams.WRAP_CONTENT, DensityUtil.dip2px(this, 48));
        buttonParamsLeft.gravity=Gravity.CENTER;

        mButtonLeft = new ImageButton(this);
//        mButtonLeft = new ImageButton(this);
        mButtonLeft.setLayoutParams(buttonParamsLeft);
        mButtonLeft.setBackground(null);
//        mButtonLeft.setGravity(Gravity.CENTER);
      //  mButtonLeft.setImageResource(R.mipmap.ic_launcher);
        *//*mButtonLeft.setText("左侧");
        mButtonLeft.setTextColor(PublicUtil.getResColor(R.color.app_color));
        *//*
        //mButtonLeft.setBackgroundResource(R.drawable.back_selector);
        // mButtonLeft.setBackgroundColor(PublicUtil.getResColor(R.color.saletoexamineaar_biaoTiLan_color));
        // mButtonLeft.setImageResource(R.drawable.left_arrow);
        mButtonLeft.setVisibility(View.GONE);
        mButtonLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                LeftButtonClicked();
            }
        });

        //这个是中间的标题
        mImgeTitle = new TextView(this);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, DensityUtil.dip2px(this,49));
        params.addRule(RelativeLayout.CENTER_IN_PARENT);//标题在相对布局中居中
        mImgeTitle.setLayoutParams(params);
        mImgeTitle.setTextSize(18);
        mImgeTitle.setTextColor(PublicUtil.getResColor(R.color.textColor1));
        mImgeTitle.setGravity(Gravity.CENTER);//内容居中

        //这个是右边的按钮
        RelativeLayout.LayoutParams buttonParamsRight = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, DensityUtil.dip2px(this,49));
        buttonParamsRight.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);//局右
        buttonParamsRight.setMargins(0,0,10,0);//设置周围的边距
        //buttonParamsRight.setMarginEnd(10);


        mButtonRight = new ImageButton(this);
        mButtonRight.setLayoutParams(buttonParamsRight);
        mButtonRight.setPadding(10,0,20,0);
        //mButtonRight.setText("右侧按钮");
       // mButtonRight.setTextSize(16);
        // mButtonRight.setTextColor(PublicUtil.getResColor(R.color.saletoexamineaar_biaoTiLan_ziti_color));
//        mButtonRight.setGravity(Gravity.CENTER);
        mButtonRight.setVisibility(View.INVISIBLE);
        //  mButtonRight.setBackgroundResource(R.color.saletoexamineaar_biaoTiLan_color);
        mButtonRight.getBackground().setAlpha(0);//0~255透明度值  0全透明
        mButtonRight.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                RightButtonClicked();
            }
        });

        //按钮加入 3次点击的监听
        mImgeTitle.setOnClickListener(new View.OnClickListener() {
            //需要监听几次点击事件数组的长度就为几
            //如果要监听双击事件则数组长度为2，如果要监听3次连续点击事件则数组长度为3...
            long[] mHints = new long[2];//初始全部为0

            @Override
            public void onClick(View v) {
                //将mHints数组内的所有元素左移一个位置
                System.arraycopy(mHints, 1, mHints, 0,
                        mHints.length - 1);
                //获得当前系统已经启动的时间
                mHints[mHints.length - 1] =
                        SystemClock.uptimeMillis();
                if (SystemClock.uptimeMillis() - mHints[0] <= 500) {
                     Toast.makeText(TitleBarActivity.this, "点击了2次", Toast.LENGTH_SHORT).show();
                    //跳转到自定义的开发者页面
                     startActivityByClass(DevelopActivity.class);
                }
            }
        });
// 内容部分增加了三个控件
        mLinearTitleContent.addView(mButtonLeft);
        mLinearTitleContent.addView(mImgeTitle);
        mLinearTitleContent.addView(mButtonRight);

        // mLinearTitleContent.setBackgroundColor(Color.WHITE);
        *//* end title content*//*
//标题增加了内容和下划线
        mLinearTitle.addView(mLinearDivider2);
        mLinearTitle.addView(mLinearTitleContent);
        mLinearTitle.addView(mLinearDivider);*/
    }

    /**
     * 左侧按钮事件
     */
    protected abstract void RightButtonClicked();

    /**
     * 右侧按钮事件
     */
    protected abstract void LeftButtonClicked();
}
