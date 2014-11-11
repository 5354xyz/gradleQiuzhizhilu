package com.example.utils;



import java.util.List;

import personInfo.PushNews;

import com.example.textviewanimation.Contacts;
import com.example.textviewanimation.ContentActivity;
import com.example.textviewanimation.R;

import android.app.Activity;
import android.app.Service;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Vibrator;
import android.text.format.Time;

public class ShakeListenerUtils implements SensorEventListener  
{  
    private Activity context; 
    Vibrator vibrator = null; 
    PushNews pushNews=null;
    int type=0;
    Handler thishandler=null;
    
  
    long time=System.currentTimeMillis();
    public ShakeListenerUtils(Activity context,PushNews pushNews,int type,Handler handler)  
    {  
        super();  
        this.context = context; 
        this.pushNews=pushNews;
        this.type=type;
        this.thishandler=handler;
       
        vibrator = (Vibrator) context.getSystemService(Service.VIBRATOR_SERVICE); 
    }  
  
    @Override  
    public void onSensorChanged(SensorEvent event)  
    {  
        int sensorType = event.sensor.getType();  
        //values[0]:X�ᣬvalues[1]��Y�ᣬvalues[2]��Z��    
        float[] values = event.values;  
  
        if (sensorType == Sensor.TYPE_ACCELEROMETER)  
        {  
  
            /*��������£���������ֵ������9.8~10֮�䣬ֻ����ͻȻҡ���ֻ�  
              ��ʱ��˲ʱ���ٶȲŻ�ͻȻ�������١�   ������һ��ļ��ٶȴ���17���� 
            */
        	
            if ((Math.abs(values[0]) > 17 || Math.abs(values[1]) > 17 || Math  
                    .abs(values[2]) > 17))  
            {  
            	long time2=System.currentTimeMillis();
            	System.out.println(time+"|"+time2);
            	if(time2-time>250 && type==0){
            		time=time2;
            		Intent toContentIntent = new Intent(context, ContentActivity.class);  
            		Bundle mBundle = new Bundle(); 
            		mBundle.putParcelable("foculsNews", pushNews);
            		toContentIntent.putExtras(mBundle);
                
            		context.startActivity(toContentIntent);
            		context.overridePendingTransition(R.anim.zoomin,  
            				R.anim.zoomout); 
            		//ҡ���ֻ����ٰ�������ʾ~~  
            		vibrator.vibrate(100);
            		List<PushNews> pushNewss= Contacts.MessageShow;
            		int ramNewsLocation = (int)(Math.random()*Contacts.MessageShow.size());
            		pushNews=pushNewss.get(ramNewsLocation);
            		Message msg=new Message();
            		msg.arg1 = Contacts.SHAKE_OK_M;
            		thishandler.sendMessage(msg);
            		//context.finish();  
                }else if(time2-time>250 && type==1)
                {
                	time=time2;
                	Message msg=new Message();
            		msg.arg1 = Contacts.SHAKE_OK_C;
            		msg.obj=pushNews;
            		thishandler.sendMessage(msg);
            		//ҡ���ֻ����ٰ�������ʾ~~  
            		vibrator.vibrate(100);
            		List<PushNews> pushNewss= Contacts.MessageShow;
            		int ramNewsLocation = (int)(Math.random()*Contacts.MessageShow.size());
            		pushNews=pushNewss.get(ramNewsLocation);
                }
            }  
        }  
    }  
  
    @Override  
    public void onAccuracyChanged(Sensor sensor, int accuracy)  
    {  
        //�����������ȸı�ʱ�ص��÷�����Do nothing.   
    }  
  
}  