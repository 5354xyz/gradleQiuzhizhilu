package com.example.utils;

import com.example.textviewanimation.R;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Scroller;

/**
 * �Զ�����Ի�����RelativeLayout, ������IOS�Ļ���ɾ��ҳ��Ч����������Ҫʹ��
 * �˹��ܵ�ʱ����Ҫ����Activity�Ķ��㲼������ΪSildingFinishLayout
 * Ҫע�⣺android �����¼��ַ����أ������View������ViewGroup���滹���¼���������Ҫ���ų���
 * 
 * @email  xiaoyizong@126.com
 * 
 */
public class SildingFinishLayout extends RelativeLayout{
	/**
	 * SildingFinishLayout���ֵĸ�����
	 */
	private ViewGroup mParentView;
	/**
	 * ��������С����
	 */
	private int mTouchSlop;
	/**
	 * ���µ��X����
	 */
	private int downX;
	/**
	 * ���µ��Y����
	 */
	private int downY;
	/**
	 * ��ʱ�洢X����
	 */
	private int tempX;
	/**
	 * ������
	 */
	private Scroller mScroller;
	/**
	 * SildingFinishLayout�Ŀ��
	 */
	private int viewWidth;
	
	private boolean isSilding;
	
	private OnSildingFinishListener onSildingFinishListener;
	private boolean isFinish;
	private int direction = -1;
	/**
	 * �Ƿ������ĳ���������
	 */
	private boolean isSetLeft = false;
	private boolean isSetRight = false;
	private boolean isSetLeft_Right = false;
	/**
	 * �Ƿ����˹�������ԭ���ĵط�
	 */
	private boolean byPeople = false;
	

	public SildingFinishLayout(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public SildingFinishLayout(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);

		mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
		mScroller = new Scroller(context);
	}
	/**
	 * 1 �������󻬶�
	 * 2��������
	 * 3��������
	 * @param direction
	 */
	public void setSlidingDirection(int direction)
	{
		switch(direction){
			case 1:isSetLeft=true;break;
			case 2:isSetRight=true;break;
			case 3:isSetLeft_Right=true;break;
			default:return;
		}
			
	}
	/**
	 * �¼����ز���
	 */
	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		
		switch (ev.getAction()) {
		case MotionEvent.ACTION_DOWN:
			downX = tempX = (int) ev.getRawX();
			downY = (int) ev.getRawY();
			break;
		case MotionEvent.ACTION_MOVE:
			int moveX = (int) ev.getRawX();
			//�������������SildingFinishLayout���������touch�¼�
			if (Math.abs(moveX - downX) > mTouchSlop
					&& Math.abs((int) ev.getRawY() - downY) < mTouchSlop 
					&& !checkIndisbootchoose(ev) && (isSetLeft||isSetRight||isSetLeft_Right)) {
				System.out.println("���������touch�¼�");
				return true;
			}
			break;
		}
		
		return super.onInterceptTouchEvent(ev);
	}
	

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (!checkIndisbootchoose(event)) {
			switch (event.getAction()) {
			case MotionEvent.ACTION_MOVE:
				int moveX = (int) event.getRawX();
				int deltaX = tempX - moveX;
				tempX = moveX;
				if (Math.abs(moveX - downX) > mTouchSlop
						&& Math.abs((int) event.getRawY() - downY) < mTouchSlop) {
					isSilding = true;
				}

				if (Math.abs(moveX - downX) >= 0 && isSilding) {
					if (((moveX - downX) > 0 && isSetLeft)
							|| ((moveX - downX) < 0 && isSetRight)
							|| isSetLeft_Right)
						mParentView.scrollBy(deltaX, 0);
				}
				break;
			case MotionEvent.ACTION_UP:
				isSilding = false;
				if (mParentView.getScrollX() <= -viewWidth / 2) {
					isFinish = true;
					direction = 1;
					scrollRight();
				} else if (mParentView.getScrollX() >= viewWidth / 2) {
					isFinish = true;
					direction = 2;
					scrollLeft();
				} else {
					isFinish = false;
					direction = 0;
					scrollOrigin(false);

				}
				break;
			}
		}
		return true;
	}
	

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		super.onLayout(changed, l, t, r, b);
		if (changed) {
			// ��ȡSildingFinishLayout���ڲ��ֵĸ�����
			mParentView = (ViewGroup) this.getParent();
			viewWidth = this.getWidth();
		}
	}

	/**
	 * ����OnSildingFinishListener, ��onSildingFinish()������finish Activity
	 * 
	 * @param onSildingFinishListener
	 */
	public void setOnSildingFinishListener(
			OnSildingFinishListener onSildingFinishListener) {
		this.onSildingFinishListener = onSildingFinishListener;
	}


	/**
	 * �������ҹ���������
	 */
	private void scrollRight() {
		final int delta = (viewWidth + mParentView.getScrollX());
		// ����startScroll����������һЩ�����Ĳ�����������computeScroll()�����е���scrollTo������item
		mScroller.startScroll(mParentView.getScrollX(), 0, -delta + 1, 0,
				Math.abs(delta));
		postInvalidate();
	}
	
	/**
	 * �������ҹ���������
	 */
	private void scrollLeft() {
		final int delta = (viewWidth - mParentView.getScrollX());
		// ����startScroll����������һЩ�����Ĳ�����������computeScroll()�����е���scrollTo������item
		mScroller.startScroll(mParentView.getScrollX(), 0, delta - 1, 0,
				Math.abs(delta));
		postInvalidate();
	}

	/**
	 * ��������ʼλ��
	 */
	public void scrollOrigin(boolean byPeople) {
		//getScrollX() ---> Return the scrolled left position of this view(����ƫ������Ļ��߽��Զ  ������(��߽�|)������)
		this.byPeople = byPeople;
		int delta = mParentView.getScrollX();
		mScroller.startScroll(mParentView.getScrollX(), 0, -delta, 0,
				Math.abs(delta));
		postInvalidate();
	}


	@Override
	public void computeScroll() {
		// ����startScroll��ʱ��scroller.computeScrollOffset()����ֵΪboolean��true˵��������δ��ɣ�false˵�������Ѿ����
		if (mScroller.computeScrollOffset()) {
			mParentView.scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
			postInvalidate();

			if (mScroller.isFinished() && isFinish) {

				if (onSildingFinishListener != null) {
					if(!this.byPeople){
						onSildingFinishListener.onSildingFinish(direction);
						}
					this.byPeople = false;
						
				}else{
					//û������OnSildingFinishListener�������������ʵλ��
					scrollOrigin(false);
					isFinish = false;
				}
			}
		}
	}
	

	public interface OnSildingFinishListener {
		/**
		 * �������ҹ��������� type=1
		 * ����������������� type=2
		 * @param type
		 */
		public void onSildingFinish(int type);
	}

	/**
	 * �ж������Ƿ�����һЩ����Ҫִ�����һ������ٵ��������ڣ�����ط�д���ˣ������޸�
	 * 2014-4-13
	 * 
	 * @author:5354xyz
	 */
	public boolean checkIndisbootchoose(MotionEvent event)
	{
		boolean isHit=false;
		Rect rect=null;
		if(getId() == R.id.post_act_sildingFinishLayout){
			isHit=false;
			rect = new Rect();
			findViewById(R.id.ll_facechoose).getHitRect(rect);//��øÿؼ���ռ�ľ�������
			isHit = rect.contains((int) event.getX(), (int) event.getY());//�ж������Ƿ��ھ���������
			return isHit;
		}else if(getId() == R.id.pulltorefresh_sildingFinishLayout)
		{
			
			if(findViewById(R.id.image_show_RelativeLayout).getVisibility() == View.VISIBLE)//��øÿؼ���ռ�ľ�������
				return true;
			else
				return false;
		}
			return false;
	}
}
