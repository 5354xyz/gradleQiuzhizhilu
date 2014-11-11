package com.example.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;

import android.net.NetworkInfo.State;
import android.os.Handler;
import android.os.Message;


/** 
 * @author  5354xyz
 * @version 2013-11-7 ����4:18:04 
 * @Eacer-mail: xiaoyizong@126.com
 */
public class NetCheckReceiver extends BroadcastReceiver{
	Handler handler=null;
	
	public NetCheckReceiver(Handler handler)
	{
		this.handler=handler;
	}
	

	
    //android ������仯ʱ������Intent������
    private static final String netACTION="android.net.conn.CONNECTIVITY_CHANGE";
    @Override
    public void onReceive(Context context, Intent intent) {

    	State wifiState = null;  
        State mobileState = null;
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);  
        wifiState = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState();  
        mobileState = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState();  
        if (wifiState != null && mobileState != null  
                && State.CONNECTED != wifiState  
                && State.CONNECTED == mobileState) {  
            // �ֻ��������ӳɹ�  
        	Message message = new Message();
        	message.arg1=1;
        	handler.sendMessage(message);
        } else if (wifiState != null && mobileState != null  
                && State.CONNECTED != wifiState  
                && State.CONNECTED != mobileState) {  
            // �ֻ�û���κε�����  
        	Message message = new Message();
        	message.arg1=0;
        	handler.sendMessage(message);
        	
        } else if (wifiState != null && State.CONNECTED == wifiState) {  
        	Message message = new Message();
            // �����������ӳɹ�  
        	message.arg1=2;
        	handler.sendMessage(message);
        }  
  
    }
    
    
}
