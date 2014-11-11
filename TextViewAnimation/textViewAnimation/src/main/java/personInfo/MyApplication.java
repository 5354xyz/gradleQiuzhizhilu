package personInfo;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import android.app.Activity;
import android.app.Application;

/**
 * @author 5354xyz
 * @version 2013-11-22 ����11:05:30
 * @Eacer-mail: xiaoyizong@126.com
 */
// MyApplication�������洢ÿһ��activity����ʵ�ֹر�����activity�Ĳ���
public class MyApplication extends Application {

	// ������activity����
	private List activityList = new LinkedList();
	private static MyApplication instance;

	private MyApplication() {
	}

	// �������ģʽ��ȡ��Ψһ��MyApplicationʵ��
	public static MyApplication getInstance() {
		if (instance == null)
			instance = new MyApplication();
		return instance;
	}

	// ���activity��������
	public void addActivity(Activity activity) {
		activityList.add(activity);
	}

	// �������е�activity��finish
	public void exitApp() {
		for (Iterator iterator = activityList.iterator(); iterator.hasNext();) {
			Activity type = (Activity) iterator.next();
			if (type != null)
				type.finish();
		}
		//System.exit(0);
	}

	// ��ջ���
	@Override
	public void onLowMemory() {
		super.onLowMemory();
		//System.gc();
	}

}
