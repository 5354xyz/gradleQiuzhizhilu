package personInfo;

import android.os.Parcel;
import android.os.Parcelable;

public class PushNews implements Parcelable
{
	//������Ϣ�ĳ�Ա����
	private String pushNews_id;//id
	private String pushNews_content;//����
	private String pushNews_up;//��
	private String pushNews_down;//��ש
	private String pushNews_title;//����
	
	private int hasClick =0;
	
	   public int getHasClick() {
		return hasClick;
	}
	public void setHasClick(int hasClick) {
		this.hasClick = hasClick;
	}
	// 1.����ʵ��Parcelable.Creator�ӿ�,�����ڻ�ȡPerson���ݵ�ʱ�򣬻ᱨ�����£�
	   // android.os.BadParcelableException:
	   // Parcelable protocol requires a Parcelable.Creator object called  CREATOR on class  personInfo.PushNews
	   // 2.����ӿ�ʵ���˴�Percel������ȡPerson���ݣ�������Person������߼���ʹ��
	   // 3.ʵ��Parcelable.Creator�ӿڶ���������ΪCREATOR������ͬ���ᱨ���������ᵽ�Ĵ�
	   // 4.�ڶ�ȡParcel������������£����밴��Ա����������˳���ȡ���ݣ���Ȼ����ֻ�ȡ���ݳ���
       // 5.�����л�����
	 public static final Parcelable.Creator<PushNews> CREATOR = new Creator<PushNews>(){
		  
		          @Override
		          public PushNews createFromParcel(Parcel source) {
		              // TODO Auto-generated method stub
		              // ���밴��Ա����������˳���ȡ���ݣ���Ȼ����ֻ�ȡ���ݳ���
		        	  PushNews p = new PushNews();
		              p.setPushNews_id(source.readString());
		              p.setPushNews_content(source.readString());
		              p.setPushNews_up(source.readString());
		              p.setPushNews_down(source.readString());
		              p.setPushNews_title(source.readString());
		              
		              return p;
		          }
		  
		          @Override
		          public PushNews[] newArray(int size) {
		              // TODO Auto-generated method stub
		              return new PushNews[size];
		          }
		      };
	public String getPushNews_title() {
		return pushNews_title;
	}
	public void setPushNews_title(String pushNews_title) {
		this.pushNews_title = pushNews_title;
	}
	public String getPushNews_id() {
		return pushNews_id;
	}
	public void setPushNews_id(String pushNews_id) {
		this.pushNews_id = pushNews_id;
	}
	public String getPushNews_content() {
		return pushNews_content;
	}
	public void setPushNews_content(String pushNews_content) {
		this.pushNews_content = pushNews_content;
	}
	public String getPushNews_up() {
		return pushNews_up;
	}
	public void setPushNews_up(String pushNews_up) {
		this.pushNews_up = pushNews_up;
	}
	public String getPushNews_down() {
		return pushNews_down;
	}
	public void setPushNews_down(String pushNews_down) {
		this.pushNews_down = pushNews_down;
	}
	@Override
	public String toString() {
		return "pushNews_id:" + pushNews_id + "\npushNews_content:"
				+ pushNews_content + "\npushNews_up:" + pushNews_up
				+ "\npushNews_down:" + pushNews_down + "\npushNews_title:"
				+ pushNews_title + "\n";
	}
	
	
	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub
		// 1.���밴��Ա����������˳���װ���ݣ���Ȼ����ֻ�ȡ���ݳ���
		// 2.���л�����
		dest.writeString(pushNews_id);  
		dest.writeString(pushNews_content);  
		dest.writeString(pushNews_up); 
		dest.writeString(pushNews_down);  
		dest.writeString(pushNews_title);  
	}


	
}
