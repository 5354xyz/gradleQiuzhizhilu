package com.example.utils;

import com.example.textviewanimation.R;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

/** 
 * @author  5354xyz
 * @version 2013-11-13 ����9:57:57 
 * @Eacer-mail: xiaoyizong@126.com
 */
public class MyDialog {
	/** 
     * �õ��Զ����progressDialog 
     * @param context 
     * @param msg 
     * @return 
     */  
	
    public static Dialog createLoadingDialog(Context context, int theme,String msg) {  
  
        LayoutInflater inflater = LayoutInflater.from(context);  
        View v = inflater.inflate(R.layout.loading, null);// �õ�����view  
        LinearLayout layout = (LinearLayout) v.findViewById(R.id.dialog_view);// ���ز���  
        // main.xml�е�ImageView  
        ImageView spaceshipImage = (ImageView) v.findViewById(R.id.img);  
        TextView tipTextView = (TextView) v.findViewById(R.id.tipTextView);// ��ʾ����  
        // ���ض���  
        Animation hyperspaceJumpAnimation = AnimationUtils.loadAnimation(  
                context, R.anim.loading_animation);  
        // ʹ��ImageView��ʾ����  
        spaceshipImage.startAnimation(hyperspaceJumpAnimation);  
        tipTextView.setText(msg);// ���ü�����Ϣ  
  
        Dialog loadingDialog = new Dialog(context, R.style.custom_dialog);// �����Զ�����ʽdialog  
  
        loadingDialog.setCancelable(false);// �������á����ؼ���ȡ��  
        loadingDialog.setContentView(layout, new LinearLayout.LayoutParams(  
                LinearLayout.LayoutParams.MATCH_PARENT,  
                LinearLayout.LayoutParams.MATCH_PARENT));// ���ò���  
        return loadingDialog;  
  
    }
    
    public static Dialog createInterestDialog(Context context, int theme) {  
    	  
    	LayoutInflater inflater = LayoutInflater.from(context);  
        View v = inflater.inflate(R.layout.intrest_dialog, null);// �õ�����view  
        Dialog interestDialog = new Dialog(context,theme);// �����Զ�����ʽdialog 
        Window window = interestDialog.getWindow();
		window.setGravity(Gravity.TOP);//�ڵײ�����
		window.setWindowAnimations(R.style.InterestDialog);
  
        interestDialog.setCancelable(false);// �������á����ؼ���ȡ��  
        interestDialog.setContentView(v);// ���ò���  
        return interestDialog;  
  
    }
    
    public static Dialog createAboutDialog(Context context, int theme) {  
  	  
    	LayoutInflater inflater = LayoutInflater.from(context);  
        View v = inflater.inflate(R.layout.about_dialog, null);// �õ�����view  
        Dialog interestDialog = new Dialog(context,theme);// �����Զ�����ʽdialog 
        Window window = interestDialog.getWindow();
		window.setGravity(Gravity.BOTTOM);//�ڵײ�����
		window.setWindowAnimations(R.style.InterestDialog);
		interestDialog.setCanceledOnTouchOutside(true);
        interestDialog.setCancelable(true);// �������á����ؼ���ȡ��  
        interestDialog.setContentView(v);// ���ò���  
        return interestDialog;  
  
    }
   
    public static Dialog createAccountDialog(Context context, int theme) {  
    	  
    	LayoutInflater inflater = LayoutInflater.from(context);  
        View v = inflater.inflate(R.layout.account_dialog, null);// �õ�����view  
        Dialog interestDialog = new Dialog(context,theme);// �����Զ�����ʽdialog 
        Window window = interestDialog.getWindow();
		window.setGravity(Gravity.BOTTOM);//�ڵײ�����
		window.setWindowAnimations(R.style.InterestDialog);
		interestDialog.setCanceledOnTouchOutside(true);
        interestDialog.setCancelable(true);// �������á����ؼ���ȡ��  
        interestDialog.setContentView(v);// ���ò���  
        return interestDialog;  
  
    }
}
