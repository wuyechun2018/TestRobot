import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class TestByUrlConn {

	public static void main(String[] args) {
		String path="http://59.203.153.116:8090/robot/certificate/save";
		String json="idcard=340223199008230010&name=张鹏飞&sex=男&relation=户主&address=桃园001号&community=桃花社区";
		System.out.println(postDownloadJson(path,json));
	}

	public static String postDownloadJson(String path, String post) {
		URL url = null;
		try {
			url = new URL(path);
			HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
			httpURLConnection.setRequestMethod("POST");// 提交模式
			// conn.setConnectTimeout(10000);//连接超时 单位毫秒
			// conn.setReadTimeout(2000);//读取超时 单位毫秒
			// 发送POST请求必须设置如下两行
			httpURLConnection.setDoOutput(true);
			httpURLConnection.setDoInput(true);
			// 获取URLConnection对象对应的输出流
			PrintWriter printWriter = new PrintWriter(httpURLConnection.getOutputStream());
			// 发送请求参数
			printWriter.write(post);// post的参数 xx=xx&yy=yy
			// flush输出流的缓冲
			printWriter.flush();
			// 开始获取数据
			BufferedInputStream bis = new BufferedInputStream(httpURLConnection.getInputStream());
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			int len;
			byte[] arr = new byte[1024];
			while ((len = bis.read(arr)) != -1) {
				bos.write(arr, 0, len);
				bos.flush();
			}
			bos.close();
			return bos.toString("utf-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
