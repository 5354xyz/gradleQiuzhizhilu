package imageShow;

import imageLoad.AsyncImageLoadUtil;
import imageLoad.CallbackImp;

import java.util.ArrayList;
import java.util.List;

import personInfo.ImageModel;


import com.example.textviewanimation.Contacts;
import com.example.textviewanimation.R;
import com.example.utils.ZoomImageView;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;

/** 
 * @author  5354xyz
 * @version 2014-4-20 ����7:17:58 
 * @E5354xyz-mail: xiaoyizong@126.com
 */
public class MyImageViewPagerAdapter extends PagerAdapter
{
	 private List<ImageModel> mListImageModels;
	 private List<View> mListViews =new ArrayList<View>();
	 private Context context;
     public MyImageViewPagerAdapter(List<ImageModel> mListImageModels,Context context) {  
         this.mListImageModels = mListImageModels;//���췽�������������ǵ�ҳ���������ȽϷ��㡣  
         this.context=context;
         initView();
     } 

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mListImageModels.size();
	}
	public void initView()
	{
		for(int k=0;k<mListImageModels.size();k++)
		{
			ZoomImageView defualtImage = new ZoomImageView(context);
			defualtImage.setLayoutParams(new LayoutParams(     
	                   LayoutParams.MATCH_PARENT,     
	                   LayoutParams.WRAP_CONTENT     
	           ));
			defualtImage.setImageResource(R.drawable.black_bg);
			defualtImage.setPadding(2, 2, 2, 2);
			System.out.println("K:"+k);
			mListViews.add(defualtImage);
		}
	}
	@Override  
    public Object instantiateItem(ViewGroup container, int position) {  //�����������ʵ����ҳ��
		
		container.addView(mListViews.get(position), position);//���ҳ�� 
		System.out.println("path:"+Contacts.BaseURL_IMAGE+mListImageModels.get(position).getImg_path());
		loadImage(Contacts.BaseURL_IMAGE+mListImageModels.get(position).getImg_path(), (ZoomImageView)container.getChildAt(position));
         
         return mListViews.get(position);  
    }
	@Override  
    public void destroyItem(ViewGroup container, int position, Object object)   {     
        container.removeView(mListViews.get(position));//ɾ��ҳ��  
    }
	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		// TODO Auto-generated method stub
		return arg0==arg1;//�ٷ���ʾ����д
	}
	public Drawable loadImage(final String imageUrl,ZoomImageView imageView)
	{
		AsyncImageLoadUtil loader=new AsyncImageLoadUtil();
		Drawable imagefromweb=null;
		//����һ������ͼƬ�Ļص��ӿ�ʵ����
		CallbackImp callbackImp=new CallbackImp(imageView);
		//����Ǵӻ�����ȡ����
		imagefromweb=loader.loadImage(imageUrl, callbackImp,true,context);
		//������治Ϊ�գ�������callbackֱ�����ú��ˣ��������˲���
		if(imagefromweb !=null)
		{
			callbackImp.imageLoadedSet(imagefromweb);
		}
		
		return imagefromweb;
	}
}
