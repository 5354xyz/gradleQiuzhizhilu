package com.example.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Vector;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.util.EntityUtils;
import org.xmlpull.v1.XmlPullParserException;

import personInfo.Result_spilt_request;

import android.os.AsyncTask;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.example.textviewanimation.Contacts;
import com.example.textviewanimation.PostActivity;
import com.example.textviewanimation.RegisterActivity;

//4.0���ϵ�ϵͳ���������߳���ʹ��http�����������첽
public class HttpProcess extends AsyncTask<List<NameValuePair>,Integer,String>
{

	private HttpResponse httpResponse = null;
	private HttpEntity httpReguestEntity = null;
	private HttpEntity httpResposeEntity = null;
	InputStream ins = null;
	HttpGet httpGet = null;
	HttpPost httpPost = null;
	HttpClient httpClient = null;
	String responseFromServer = "";
	Handler thisHandler =null;
	String RequestType=null;//����������ע��
	/**
	 * ������������Ƭ�������ȵ�SD��·��
	 */
	Vector <String> attachment =null;
	public HttpProcess(Handler thisHandler,String RequestType,Vector <String> attachment)
	{
		this.thisHandler=thisHandler;
		this.RequestType=RequestType;
		this.attachment=attachment;
	}
	// get�������������������
	public String httpGetProcess(final String Url) 
	{
		// 4.0���ϵ�ϵͳ���������߳��з��ʣ������첽

				// ��Ҫִ�еķ���
				try {
					// ����һ������
					httpGet = new HttpGet(Url);
					// ����һ���ͻ���
					httpClient = new DefaultHttpClient();
					// ʹ��http�ͻ��˷���һ���������

					httpResponse = httpClient.execute(httpGet);// �õ���������Ӧ
					httpResposeEntity = httpResponse.getEntity();// ����Ӧ����ȡ��ʵ��

					ins = httpResposeEntity.getContent();

					BufferedReader buff = new BufferedReader(
							new InputStreamReader(ins,Contacts.Encoding));

					String line = null;
					//responseFromServer="";
					while ((line = buff.readLine()) != null) {
						responseFromServer += line;
					}
					//System.out.println(responseFromServer);
					ins.close();
				} catch (Exception e) {
					e.printStackTrace();
				}

		return responseFromServer;
	}

	// post�������������������
	public String httpPostProcess(HttpPost httpPost) {
		// 4.0���ϵ�ϵͳ���������߳��з��ʣ������첽

				//System.out.println("qwe");
				// ��Ҫִ�еķ���
				try {
					System.out.println("responseFromServe!@#$%%%%r");
					// ����һ���ͻ���
					httpClient = new DefaultHttpClient();
					 // ����ʱ
					httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 15 * 1000);
	                // ��ȡ��ʱ
					httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 15 * 1000);
					httpResponse = httpClient.execute(httpPost);// �õ���������Ӧ

					httpResposeEntity = httpResponse.getEntity();// ����Ӧ����ȡ��ʵ��

					ins = httpResposeEntity.getContent();

					BufferedReader buff = new BufferedReader(
							new InputStreamReader(ins,Contacts.Encoding));//ע�����Contacts.Encoding

					String line = null;
					int i=0;
					while ((line = buff.readLine()) != null) {
						//System.out.println(line);
						if(i==0)
							responseFromServer = line;
						else
							responseFromServer += line;
						i++;
					}
					System.out.println(responseFromServer+"responseFromServer");
					ins.close();
					httpClient.getConnectionManager().shutdown();  
				}catch(ConnectTimeoutException e){
                  return responseFromServer;
                } catch (ClientProtocolException e) {
                   
                    e.printStackTrace();
                } catch (IOException e) {
                   
                    e.printStackTrace();
                }


		return responseFromServer;
	}
	

	   
	 @Override
     protected void onPreExecute() {
             
     }

	    /** 
	     * upLoadByAsyncHttpClient:��HttpClient4�ϴ� 
	     *  
	     * @return void 
	     * @throws IOException 
	     * @throws ClientProtocolException 
	     * @throws 
	     * @since CodingExample��Ver 1.1 
	     */  
	    private void upLoadByHttpClient4(File file,HttpPost httppost) throws ClientProtocolException,  
	            IOException {  
	        HttpClient httpclient = new DefaultHttpClient();  
	        Log.i("mylog","������-->1");
	        httpclient.getParams().setParameter(  
	                CoreProtocolPNames.PROTOCOL_VERSION, HttpVersion.HTTP_1_1);   
	        Log.i("mylog","������-->2");
	        MultipartEntity entity = new MultipartEntity();  
	        FileBody fileBody = new FileBody(file);  
	        entity.addPart("uploadfile", fileBody);
	        Log.i("mylog","������-->3");
	        httppost.setEntity(entity);  
	        Log.i("mylog","������-->4");
	        HttpResponse response = httpclient.execute(httppost); 
	        Log.i("mylog","������-->5");
	        HttpEntity resEntity = response.getEntity();  
	        Log.i("mylog","������-->6");
	        if (resEntity != null) {  
	            Log.i(RequestType, EntityUtils.toString(resEntity));  
	        }  
	        if (resEntity != null) {  
	            resEntity.consumeContent();  
	        }  
	        httpclient.getConnectionManager().shutdown();  
	    }  
	@Override
	protected String doInBackground(List<NameValuePair>... arg0) {
		// TODO Auto-generated method stub
		List<NameValuePair> nameValuePairs=arg0[0];
		String result="";
		//�����httpEntity
		try {
			//�����ע�ᣬRequestType��ͷ���·��
			if(attachment != null)
			{
				try {
			    	//Log.i("mylog_url","������-->uploadUrl:"+RegisterActivity.uploadUrl);
					HttpPost httppost=null;
			    	if(attachment.size() > 0)
			    	{
			    		for(int i=0;i<attachment.size();i++){
			    			File file = new File(attachment.get(i));
							if(RequestType.equals(Contacts.RequestRegister)){
								httppost = new HttpPost(RegisterActivity.uploadUrl);
					    	}else if(RequestType.equals(Contacts.RequestPushSpilt))
					    	{
					    		httppost = new HttpPost(PostActivity.uploadUrl);
					    	}
							//////////////////////////�����ټ�/////////////////////////////
							
			    			if(file.exists() && httppost != null)
			    			{
			    				System.out.println("imageFile.exists()");
			    				upLoadByHttpClient4(file,httppost);
			    			}else
			    			{
			    				System.out.println("imageFile is not exists");
			    			}
			    		}
			    	}
			    	
				} catch (ClientProtocolException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					Log.i("upLoadByAsyncHttpClient", "ClientProtocolException");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					Log.i("upLoadByAsyncHttpClient", "IOException");
				}
			}
			//Log.i("mylog","������-->7");
			httpReguestEntity = new UrlEncodedFormEntity(
					nameValuePairs, "utf-8");
			System.out.println("httpprocess doInBackground");
			httpPost = new HttpPost(Contacts.BaseURL);
			httpPost.setEntity(httpReguestEntity);
			
			result=httpPostProcess(httpPost);
			return result;
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}

	@Override
	protected void onPostExecute(String result) {
		// TODO Auto-generated method stub
		//System.out.println(result+"result");
		Message message = new Message();
		message.obj=result;//objΪ���󷵻صĽ��
		message.what=1;
		 if ("" == result) 
		 {
			 
			 System.out.println("message.obj="+message.obj);
			 message.arg2=Contacts.TIME_OUT;
		 }
		
		 message.arg1 = Integer.valueOf(RequestType).intValue();//arg1δ��������
		
		thisHandler.sendMessage(message);
		super.onPostExecute(result);
	}
	
	

}
