package frameworkandroid.tan.com.bottomframework.customview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import frameworkandroid.tan.com.bottomframework.R;

/**
 * ��̬ˢ�ºͼ�������ListView
 *
 * @author RobinTang
 *
 */
public class DynamicListView extends ListView implements OnScrollListener {

	/**
	 * ������ �����ؼ���ˢ�»��߼��ظ����¼� ���е���Ŀ�¼�������һ��ƫ������Ҳ����positionӦ�ü�1�������������е���Ŀ
	 *
	 * @author RobinTang
	 *
	 */
	public interface DynamicListViewListener {
		/**
		 *
		 * @param dynamicListView
		 * @param isRefresh
		 *            Ϊtrue��ʱ��������ˢ�£�Ϊfalse��ʱ�������Ǽ��ظ���
		 * @return true:ˢ�»��߼��ظ��ද����ɣ�ˢ�»��߼��ظ���Ķ����Զ���ʧ
		 *         false:ˢ�»��߼��ظ���Ϊ��ɣ���Ҫ�����ݼ������֮��ȥ���ÿؼ���doneRefresh
		 *         ()����doneMore()����
		 */
		public boolean onRefreshOrMore(DynamicListView dynamicListView,
									   boolean isRefresh);
	}

	/**
	 * ״̬�ؼ���StatusView���б�ͷ�Ϻ͵׶˵ģ���״̬ö��
	 *
	 * @author RobinTang
	 *
	 */
	enum RefreshStatus {
		none, normal, willrefresh, refreshing
	}

	/**
	 * ״̬�ؼ�
	 *
	 * @author RobinTang
	 *
	 */
	class StatusView extends LinearLayout {
		public int height;
		public int width;
		private ProgressBar progressBar = null;
		private TextView textView = null;
		private RefreshStatus refreshStatus = RefreshStatus.none;
		private String normalString = "����ˢ��";
		private String willrefreshString = "�ɿ�ˢ��";
		private String refreshingString = "����ˢ��";

		public StatusView(Context context, AttributeSet attrs) {
			super(context, attrs);
			initThis(context);
		}

		public StatusView(Context context) {
			super(context);
			initThis(context);
		}

		private void initThis(Context context) {
			this.setOrientation(LinearLayout.HORIZONTAL);
			this.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);

			progressBar = new ProgressBar(context);
			progressBar.setLayoutParams(new LayoutParams(30, 30));
			textView = new TextView(context);
			textView.setTextSize(18);
			textView.setPadding(5, 0, 0, 0);
			textView.setTextColor(context.getResources().getColor(R.color.typeface));

			setCacheColorHint(0);
			this.setGravity(Gravity.CENTER);

			this.addView(progressBar);
			this.addView(textView);

			int w = MeasureSpec.makeMeasureSpec(0,
					MeasureSpec.UNSPECIFIED);
			int h = MeasureSpec.makeMeasureSpec(0,
					MeasureSpec.UNSPECIFIED);
			this.measure(w, h);

			height = this.getMeasuredHeight();
			width = this.getMeasuredWidth();

			this.setRefreshStatus(RefreshStatus.normal);
		}

		public RefreshStatus getRefreshStatus() {
			return refreshStatus;
		}

		public void setRefreshStatus(RefreshStatus refreshStatus) {
			if (this.refreshStatus != refreshStatus) {
				this.refreshStatus = refreshStatus;
				if (refreshStatus == RefreshStatus.refreshing) {
					this.progressBar.setVisibility(View.VISIBLE);
				} else {
					this.progressBar.setVisibility(View.GONE);
				}
				refreshStatusString();
				this.invalidate();
			}
		}

		private void refreshStatusString() {
			switch (refreshStatus) {
			case normal:
				textView.setText(normalString);
				progressBar.setProgress(0);
				break;
			case willrefresh:
				textView.setText(willrefreshString);
				break;
			case refreshing:
				textView.setText(refreshingString);
				break;
			default:
				break;
			}
		}

		/**
		 * ����״̬�ַ���
		 *
		 * @param normalString
		 *            ƽʱ���ַ���
		 * @param willrefreshString
		 *            �ɿ���ˢ�£�����أ����ַ���
		 * @param refreshingString
		 *            ����ˢ�£�����أ����ַ���
		 */
		public void setStatusStrings(String normalString,
				String willrefreshString, String refreshingString) {
			this.normalString = normalString;
			this.willrefreshString = willrefreshString;
			this.refreshingString = refreshingString;
			this.refreshStatusString();
		}
	}

	private StatusView refreshView;
	private StatusView moreView;
	private int itemFlag = -1;
	private boolean isRecorded = false;
	private int downY = -1;
	private final float minTimesToRefresh = 1.5f;
	private final static int ITEM_FLAG_FIRST = 1;
	private final static int ITEM_FLAG_NONE = 0;
	private final static int ITEM_FLAG_LAST = -1;

	// ����������
	private DynamicListViewListener onRefreshListener;
	private DynamicListViewListener onMoreListener;
	// �������Ͷ˵�ʱ���Ƿ��Զ����ظ���
	private boolean doMoreWhenBottom = false;

	public DynamicListView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initThis(context);
	}

	public DynamicListView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initThis(context);
	}

	public DynamicListView(Context context) {
		super(context);
		initThis(context);
	}

	private void initThis(Context context) {
		refreshView = new StatusView(context);
		moreView = new StatusView(context);
		refreshView.setStatusStrings("��������ˢ������", "�ɿ�֮��ˢ������", "����ˢ������");
		moreView.setStatusStrings("����������������", "�ɿ�֮���������", "���ڼ�������");
		//this.addHeaderView(refreshView, null, false);
		//this.addFooterView(moreView, null, false);
		this.setOnScrollListener(this);
		doneRefresh();
		doneMore();
	}

	/**
	 * ���������Ҫ������
	 */
	public void setRefreshMoreData()
	{
		this.removeHeaderView(refreshView);
		refreshView.setStatusStrings("����������������", "�ɿ�֮���������", "���ڼ�������");
		this.addHeaderView(refreshView, null, false);
	}

	// ����������
	public DynamicListViewListener getOnRefreshListener() {
		return onRefreshListener;
	}

	public void setOnRefreshListener(DynamicListViewListener onRefreshListener) {
		this.onRefreshListener = onRefreshListener;

		this.addHeaderView(refreshView, null, false);
	}

	public DynamicListViewListener getOnMoreListener() {
		return onMoreListener;
	}

	public void setOnMoreListener(DynamicListViewListener onMoreListener) {
		this.onMoreListener = onMoreListener;

		this.addFooterView(moreView, null, false);
	}

	// ����
	public boolean isDoMoreWhenBottom() {
		return doMoreWhenBottom;
	}

	public void setDoMoreWhenBottom(boolean doMoreWhenBottom) {
		this.doMoreWhenBottom = doMoreWhenBottom;
	}

	@Override
	public void onScroll(AbsListView l, int t, int oldl, int count) {
		// log("%d %d %d", t, oldl, count);
		if (t == 0)
			itemFlag = ITEM_FLAG_FIRST;
		else if ((t + oldl) == count) {
			itemFlag = ITEM_FLAG_LAST;
			if (doMoreWhenBottom && onMoreListener != null
					&& moreView.getRefreshStatus() != RefreshStatus.refreshing) {
				doMore();
			}
		} else {
			itemFlag = ITEM_FLAG_NONE;
			// isRecorded = false;
		}
	}

	@Override
	public void onScrollStateChanged(AbsListView arg0, int arg1) {

	}

	@SuppressLint("ClickableViewAccessibility")
	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		switch (ev.getAction()) {
		case MotionEvent.ACTION_DOWN:
			if (isRecorded == false
					&& (itemFlag == ITEM_FLAG_FIRST
							&& onRefreshListener != null
							&& refreshView.getRefreshStatus() == RefreshStatus.normal || itemFlag == ITEM_FLAG_LAST
							&& onMoreListener != null
							&& moreView.getRefreshStatus() == RefreshStatus.normal)) {
				downY = (int) ev.getY(0);
				isRecorded = true;
				// log("���£���¼��%d flag:%d", downY, itemFlag);
			}
			break;
		case MotionEvent.ACTION_UP: {
			isRecorded = false;
			if (onRefreshListener != null
					&& refreshView.getRefreshStatus() == RefreshStatus.willrefresh) {
				doRefresh();
			} else if (refreshView.getRefreshStatus() == RefreshStatus.normal) {
				refreshView.setPadding(0, -1 * refreshView.height, 0, 0);
			}

			if (onMoreListener != null
					&& moreView.getRefreshStatus() == RefreshStatus.willrefresh) {
				doMore();
			} else if (moreView.getRefreshStatus() == RefreshStatus.normal) {
				moreView.setPadding(0, 0, 0, -1 * moreView.height);
			}
			break;
		}
		case MotionEvent.ACTION_MOVE: {
			if (isRecorded == false
					&& (itemFlag == ITEM_FLAG_FIRST
							&& onRefreshListener != null
							&& refreshView.getRefreshStatus() == RefreshStatus.normal || itemFlag == ITEM_FLAG_LAST
							&& onMoreListener != null
							&& moreView.getRefreshStatus() == RefreshStatus.normal)) {
				downY = (int) ev.getY(0);
				isRecorded = true;
				// log("���£���¼��%d flag:%d", downY, itemFlag);
			} else if (isRecorded) {
				int nowY = (int) ev.getY(0);
				int offset = nowY - downY;
				if (offset > 0 && itemFlag == ITEM_FLAG_FIRST) {
					// ����
					setSelection(0);
					if (offset >= (minTimesToRefresh * refreshView.height)) {
						refreshView.setRefreshStatus(RefreshStatus.willrefresh);
					} else {
						refreshView.setRefreshStatus(RefreshStatus.normal);
					}

					refreshView.setPadding(0, -1
							* (refreshView.height - offset), 0, 0);
				} else if (itemFlag == ITEM_FLAG_LAST) {
					// ����
					setSelection(this.getCount());
					if (offset <= -1 * (minTimesToRefresh * moreView.height)) {
						moreView.setRefreshStatus(RefreshStatus.willrefresh);
					} else {
						moreView.setRefreshStatus(RefreshStatus.normal);
					}
					moreView.setPadding(0, 0, 0, -1
							* (moreView.height + offset));
				}
				// log("λ��:%d", offset);
			}
			break;
		}
		default:
			break;
		}
		return super.onTouchEvent(ev);
	}

	/**
	 * ��ʼˢ��
	 */
	private void doRefresh() {
		// log("��ʼˢ��");
		refreshView.setRefreshStatus(RefreshStatus.refreshing);
		refreshView.setPadding(0, 0, 0, 0);
		if (onRefreshListener.onRefreshOrMore(this, true))
			doneRefresh();
	}

	/**
	 * ��ʼ���ظ���
	 */
	private void doMore() {
		// log("���ظ���");
		moreView.setRefreshStatus(RefreshStatus.refreshing);
		moreView.setPadding(0, 0, 0, 0);
		if (onMoreListener.onRefreshOrMore(this, false))
			doneMore();
	}

	/**
	 * ˢ�����֮����ã�����ȡ��ˢ�µĶ���
	 */
	public void doneRefresh() {
		// log("ˢ�����!");
		refreshView.setRefreshStatus(RefreshStatus.normal);
		refreshView.setPadding(0, -1 * refreshView.height, 0, 0);
	}

	/**
	 * ���ظ������֮����ã�����ȡ�����ظ���Ķ���
	 */
	public void doneMore() {
		// log("�������!");
		moreView.setRefreshStatus(RefreshStatus.normal);
		moreView.setPadding(0, 0, 0, -1 * moreView.height);
	}

	/**
	 * ��ȡˢ�µ�״̬
	 *
	 * @return һ�� ��Ҫˢ�� ˢ�����
	 */
	public RefreshStatus getRefreshStatus() {
		return refreshView.getRefreshStatus();
	}

	/**
	 * ��ȡ���ظ����״̬
	 *
	 * @return һ�� ��Ҫ���� �������
	 */
	public RefreshStatus getMoreStatus() {
		return moreView.getRefreshStatus();
	}
}