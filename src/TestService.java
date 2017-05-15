import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class TestService {

	public static void main(String[] args) {

		CloseableHttpClient httpClient = HttpClients.createDefault();
		try {
			HttpPost post = new HttpPost("http://59.203.153.116:8090/robot/certificate/save");
			// 创建参数列表
			List<NameValuePair> list = new ArrayList<NameValuePair>();
			list.add(new BasicNameValuePair("idcard", "340223199808230010"));
			list.add(new BasicNameValuePair("name", "张腾飞"));
			list.add(new BasicNameValuePair("sex", "男"));
			list.add(new BasicNameValuePair("relation", "户主"));
			list.add(new BasicNameValuePair("address", "桃花县桃花镇桃花村108号"));
			list.add(new BasicNameValuePair("community", "桃花社区"));
			
			// url格式编码
			UrlEncodedFormEntity uefEntity = new UrlEncodedFormEntity(list, "UTF-8");
			post.setEntity(uefEntity);
			// 执行请求
			CloseableHttpResponse httpResponse = httpClient.execute(post);
			try {
				HttpEntity entity = httpResponse.getEntity();
				if (null != entity) {
					System.out.println("================"+EntityUtils.toString(entity, "UTF-8"));
				}
			} finally {
				httpResponse.close();
			}

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (httpClient != null) {
					httpClient.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

}
