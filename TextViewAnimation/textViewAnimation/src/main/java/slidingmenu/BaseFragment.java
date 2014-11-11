package slidingmenu;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.TextView;

/* 
 *Fragment��ΪActivity�����һ������ɳ���
 *������һ��Activity��ͬʱ���ֶ��Fragment�����ң�һ��Fragment����ڶ��Activity��ʹ�á�
 *��Activity���й����У�������ӡ��Ƴ������滻Fragment��add()��remove()��replace()��
 *Fragment������Ӧ�Լ��������¼����������Լ����������ڣ���Ȼ�����ǵ���������ֱ�ӱ���������
 *����activity����������Ӱ�졣
 * ����
 * */
public class BaseFragment extends Fragment implements View.OnClickListener{

	public Context context;
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		this.context = this.getActivity();
	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}

	public void exit(){
		this.getActivity().finish();
	}
	public void exitToHome(){
		ActivityUtils.finishAll();
	}
	
	public static final void setTextStyle(TextView view, boolean bold){
		view.getPaint().setFakeBoldText(bold);
	}
}
