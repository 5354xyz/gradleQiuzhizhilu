package pulltorefresh_listview;

public interface OnRefreshListener {

	/**
	 * ����ˢ�µĽӿ�
	 */
	public void onRefresh();
	
	/**
	 * �������Ľӿ�
	 */
	public void onLoadMoring();
	
	/**
	 * ��listview���µ�item�����ڿ��ӷ�Χ�ڵ�ʱ��
	 * 2014-4-23
	 * 
	 * @author:5354xyz
	 */
	public void onOutitem(int itemNum,int from);
}
