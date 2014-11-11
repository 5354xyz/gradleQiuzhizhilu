package com.example.utils;

import android.content.Context;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;

/** 
 * @author  5354xyz
 * @version 2014-2-28 ����4:14:43 
 * @Eacer-mail: xiaoyizong@126.com
 */

//GestureDetector��Ҫ��View����дonTouchEvent�¼�
public class GestureListener extends SimpleOnGestureListener  
{  
	private int screen_width;//�����Ļ�Ŀ��
	private OnFlingListener listener_onfling;//�����ļ�����
	public GestureListener(Context context)
	{
		DisplayMetrics dm = context.getResources().getDisplayMetrics();
		screen_width = dm.widthPixels;
	}
	
	public void setOnFlingListener(OnFlingListener listener) {
		
			this.listener_onfling=listener;
		}
	public static abstract class OnFlingListener {
		public abstract void OnFlingLeft();
		public abstract void OnFlingRight();
	}
	
	

	//˫���ĵڶ���Touch downʱ���� 
    @Override  
    public boolean onDoubleTap(MotionEvent e)  
    {  
        // TODO Auto-generated method stub  
        Log.i("TEST", "onDoubleTap");  
        return super.onDoubleTap(e);  
    }  

    //ֻҪ�Ӵ���Ļ���ᴥ������¼�
    @Override  
    public boolean onDown(MotionEvent e)  
    {  
        // TODO Auto-generated method stub  
        Log.i("TEST", "onDown");  
        return super.onDown(e);  
    }  

    //Touch�˻���һ������upʱ������ 
    @Override  
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,  
            float velocityY)  
    {  
        // TODO Auto-generated method stub  
       // Log.i("TEST", "onFling:velocityX = " + velocityX + " velocityY" + velocityY);  
    	// X�������λ�ƴ���FLING_MIN_DISTANCE�����ƶ��ٶȴ���FLING_MIN_VELOCITY������/��
    	final int FLING_MIN_DISTANCE = (int) (screen_width / 3.0f), FLING_MIN_VELOCITY = 200;
    	if (e1.getX() - e2.getX() > FLING_MIN_DISTANCE && Math.abs(velocityX) > FLING_MIN_VELOCITY) {
    	listener_onfling.OnFlingLeft();
    	} else if (e2.getX() - e1.getX() > FLING_MIN_DISTANCE && Math.abs(velocityX) > FLING_MIN_VELOCITY) {
    	listener_onfling.OnFlingRight();
    	}
        return super.onFling(e1, e2, velocityX, velocityY);  
    }  

    //Touch�˲��ƶ�һֱTouch downʱ���� 
    @Override  
    public void onLongPress(MotionEvent e)  
    {  
        // TODO Auto-generated method stub  
        Log.i("TEST", "onLongPress");  
        System.out.println("������Ļ");
        super.onLongPress(e);  
    }  

    //Touch�˻���ʱ������
    @Override  
    public boolean onScroll(MotionEvent e1, MotionEvent e2,  
            float distanceX, float distanceY)  
    {  
        // TODO Auto-generated method stub  
        //Log.i("TEST", "onScroll:distanceX = " + distanceX + " distanceY = " + distanceY);  
        return super.onScroll(e1, e2, distanceX, distanceY);  
    }  

    //��������Ļ
    @Override  
    public boolean onSingleTapUp(MotionEvent e)  
    {  
        // TODO Auto-generated method stub  
        Log.i("TEST", "onSingleTapUp");  
        return super.onSingleTapUp(e);  
    }  
      
}  
