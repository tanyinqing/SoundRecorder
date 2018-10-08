package frameworkandroid.tan.com.bottomframework.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import frameworkandroid.tan.com.bottomframework.R;
import frameworkandroid.tan.com.bottomframework.util.PublicUtil;

/**
 * ѡ����õ� �Զ������Բ���
 */
public class MenuBarView extends LinearLayout {

	private OnMenuBarListener menuBarListener = null;

	public MenuBarView(Context context) {
		super(context);
	}
	
	/****************** ���ǹ�����******************************/
	public MenuBarView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
    /*************************************************************/


	//����XML���ּ����ļ���������������ͼʱ���ص��������
	@Override
	protected void onFinishInflate() {
		super.onFinishInflate();
		init();
	}

	private void init() {
		OnClickListener listener = new OnClickListener() {

			@Override
			public void onClick(View v) {
				setSelectedView(v);
			}
		};
		//�ж��ж��ٸ���Ԫ��
		int count = this.getChildCount();
		for (int i = 0; i < count; i++) {
			//��ÿһ����Ԫ�ض��������
			View child = this.getChildAt(i);
			child.setOnClickListener(listener);
		}
	}

	public boolean isSelectedByIndex(int index) {
		boolean isSelected = false;
		if (index >= 0) {
			View child = this.getChildAt(index);
			isSelected = child.isSelected(); //���Ӧ�õ���View�еķ��� TextView��View������
		}
		return isSelected;
	}

	public void setSelectedIndex(int index) {
		if (index >= 0 && index < this.getChildCount()) //�����ViewGroup�еķ���  �õ��ӿؼ��ĸ���
			setSelectedView(this.getChildAt(index));
	}
//�����Ƿ�ѡ��
	private void setSelectedView(View view) {
//����Ԫ�ر����ʱ   �ж���Ԫ���Ƿ�ѡ��
		if (view.isSelected())
			return;

		int count = this.getChildCount();
		int menuIndex = 0;
		for (int i = 0; i < count; i++) {
			View child = this.getChildAt(i);
			//�ж�������ͼ��ͬһ����ͼ
			if (child == view) {
				child.setSelected(true);
			/*TextView textView= (TextView) child.findViewById(R.id.tv_name);
//				textView.setSelected(true);
				textView.setTextColor(PublicUtil.getResColor(R.color.themeColor_169bfb));*/
				menuIndex = i;
			} else
				child.setSelected(false);   //View�еķ���  �����ÿؼ�δ��ѡ��
			/*TextView textView= (TextView) child.findViewById(R.id.tv_name);
//			textView.setSelected(false);
			textView.setTextColor(PublicUtil.getResColor(R.color.textColor_333333));*/
		}
		if (menuBarListener != null)
			menuBarListener.onMenuBarItemSelected(menuIndex);
	}

	/**
	 * ���ò˵�ҳ��ļ����¼�   �ⲿ���õĽӿ�ע�����
	 */
	public void setOnMenuBarListener(OnMenuBarListener listener) {
		menuBarListener = listener;//���ⲿ�Ľӿ�ע�����
	}
	public interface OnMenuBarListener {      //�ӿ�
		//����ǽӿ��µķ�������Ҫ�����ɺ���ϸд��
		public void onMenuBarItemSelected(int menuIndex);
	}
}
