package imageLoad;

import java.io.File;
import java.lang.ref.SoftReference;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import com.example.textviewanimation.Contacts;
import com.example.utils.File_SD_utils;
import com.example.utils.ImageTools;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;

/** 
 * @author  5354xyz
 * @version 2013-11-28 ����9:29:24 
 * @Eacer-mail: xiaoyizong@126.com
 */

//SoftReference�����ã����ڴ治��ʱ���Ա�����
public class AsyncImageLoadUtil {
	//ͼƬ���棬����ͼƬ��url��ֵ����SoftReference���󣬸ö���ָ��һ��Drawable����
	private Map<String ,SoftReference<Drawable>> imageCache=new
			HashMap<String ,SoftReference<Drawable>>();
	public Drawable loadImage(final String imageUrl,final ImageCallBack imageCallBack,final boolean savetosd,Context context)
	{
		System.out.println(imageCache.isEmpty()+"3");
		final File_SD_utils futil=new File_SD_utils();
		//��ѯ�ڴ棬����ǰ��Ҫ��ͼƬ�Ƿ���ڻ��浱��
		if(imageCache.containsKey(imageUrl))
		{
			SoftReference<Drawable> softReference=imageCache.get(imageUrl);
			if(softReference.get() != null)
			{
				System.out.println("i have loaded :"+imageUrl);
				return softReference.get();
			}
		}else if(savetosd && futil.ExistSDCard() && futil.getSDFreeSize() > 10)
		{
			String [] name =ImageTools.getpathname(imageUrl);
			System.out.println(name[0]+"!!!!"+name[1]);
			if(futil.isExitFile(Contacts.LocalBufferImageFolder, name[1]))
			{
				Bitmap bitmap = ImageTools.getPhotoFromSDCard(Environment.getExternalStorageDirectory().getAbsolutePath()+File.separator+Contacts.LocalBufferImageFolder, name[1]);
				Drawable drawable = ImageTools.bitmapToDrawable(context,bitmap);
				System.out.println("i have loaded :"+imageUrl +" from sdcard");
				if(drawable != null)
					return drawable;
				else
					System.out.println(" null !!!");
			}
		}
		final Handler handler=new Handler()
		{

			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				super.handleMessage(msg);
				System.out.println("i have not loaded :"+imageUrl+"  so i need to set");
				imageCallBack.imageLoadedSet((Drawable)msg.obj);
			}
			
		};
		
		new Thread()
		{
			public void run()
			{
				//����ͼƬ
				Drawable imageDrawable = loadImageFromUrl(imageUrl);
				
				//����һ��SoftReference<Drawable>ָ�������ص�imageDrawable
				System.out.println(imageCache.isEmpty()+"1");
				imageCache.put(imageUrl, new SoftReference<Drawable>(imageDrawable));
				System.out.println(imageCache.isEmpty()+"2");
				
				//���浽SD��
				if(savetosd && futil.ExistSDCard() && futil.getSDFreeSize() > 10){
						String [] name =ImageTools.getpathname(imageUrl);
						Bitmap imageBitmap = ImageTools.drawableToBitmap(imageDrawable);
						ImageTools.savePhotoToSDCard(imageBitmap,  Environment.getExternalStorageDirectory().getAbsolutePath()+File.separator+Contacts.LocalBufferImageFolder, name[1]);
					}
				Message message=handler.obtainMessage(0, imageDrawable);
				handler.sendMessage(message);
			}
		}.start();
		
		return null;
	}
	
	

	//����URL����·������ͼƬ
	protected Drawable loadImageFromUrl(String imageUrl)
	{
		try
		{
			return Drawable.createFromStream(new URL(imageUrl).openStream(), "src");
			
		}catch(Exception e)
		{
			throw new RuntimeException(e);
		}
	}
	
	//����һ���ڲ��ӿ�
	public interface ImageCallBack
	{
		public void imageLoadedSet(Drawable drawable);
	}
	
	
}
