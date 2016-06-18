package com.security.manage.common;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import net.sf.json.JSONObject;

import com.security.manage.model.LoginResult; 

public class UserLogin {

	public static LoginResult login(String account, String password) {
		// TODO Auto-generated method stub
		LoginResult loginResult = new LoginResult();
		try {

			StringBuilder resultJson = new StringBuilder(); 

			String path = "http://服务器域名(端口)/index.php/API/Police/policeLogin?pc_num="
					+ account + "&pc_pwd=" + password;
			URL url = new URL(path);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();

			conn.setRequestMethod("POST");
			conn.setConnectTimeout(5 * 1000);// 设置连接超时时间为5秒
			conn.setReadTimeout(20 * 1000);// 设置读取超时时间为20秒
			// 使用 URL 连接进行输出，则将 DoOutput标志设置为 true
			conn.setDoOutput(true);
			conn.setUseCaches(false);
			conn.setInstanceFollowRedirects(true);
			conn.setRequestProperty("Content-Type", "application/x-javascript");
			conn.connect();
			DataOutputStream out = new DataOutputStream(conn.getOutputStream());
//			String content = "pc_num=" + user.getAccount() + "&pc_pwd="
//					+ user.getPassword();
//			out.writeBytes(content);
			out.flush();
			out.close(); // flush and close
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					conn.getInputStream()));
			String msg = "";
			while ((msg = reader.readLine()) != null) {
				resultJson.append(msg);
			}
			reader.close();
			conn.disconnect();
			if (resultJson.length() > 0) {
				loginResult = (LoginResult) JSONObject.toBean(
						JSONObject.fromObject(resultJson.toString()),
						LoginResult.class);
				if (loginResult != null) {
					if (loginResult.getStatus().equals("200")) {
						if (loginResult.getPoliceman() == null) {  
							loginResult.setStatus("201");
							loginResult.setMsg("调用登陆接口方法失败，返回警员数据为空");
						}
					} else if (loginResult.getStatus().equals("201")) {
						loginResult.setStatus("201");
						loginResult.setMsg("调用登陆接口方法失败，返回数据为空");
					} else if (loginResult.getStatus().equals("202")) {
						loginResult.setStatus("202");
						loginResult.setMsg("调用登陆接口方法失败，参数错误");
					} else {
					}
				} else {
					loginResult = new LoginResult();
					loginResult.setStatus("201");
					loginResult.setMsg("调用登陆接口方法失败，返回数据为空");
				}
			} else {
				loginResult.setStatus("201");
				loginResult.setMsg("调用登陆接口方法失败，返回数据为空");
			}
		} catch (Exception ex) {
		}
		return loginResult;
	}

}
