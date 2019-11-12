package com.hehe.chat;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created with percy.
 * Date: 2019/11/12
 */
public class IpUtil {

    public static String getV4IP() {
        String ip = "";
        String chinaz = "http://ip.chinaz.com";

        StringBuilder inputLine = new StringBuilder();
        String read = "";
        URL url = null;
        HttpURLConnection urlConnection = null;
        BufferedReader in = null;
        try {
            url = new URL(chinaz);
            urlConnection = (HttpURLConnection) url.openConnection();
            in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "UTF-8"));
            while ((read = in.readLine()) != null) {
                inputLine.append(read + "\r\n");
            }
            //System.out.println(inputLine.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }


        Pattern p = Pattern.compile("\\<dd class\\=\"fz24\">(.*?)\\<\\/dd>");
        Matcher m = p.matcher(inputLine.toString());
        if (m.find()) {
            String ipstr = m.group(1);
            ip = ipstr;
            //System.out.println(ipstr);
        }
        return ip;

    }


    public static String getAddress(HttpServletRequest request) {
        try {

            String ip = request.getHeader("x-forwarded-for");
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("Proxy-Client-IP");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("WL-Proxy-Client-IP");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getRemoteAddr();
            }
            if (ip != null && ip.length() != 0 && !"unknown".equalsIgnoreCase(ip)) {
                // 多次反向代理后会有多个ip值，第一个ip才是真实ip
                if (ip.indexOf(",") != -1) {
                    ip = ip.split(",")[0];
                }
            }
            String object = HttpUtil.get("http://ip.taobao.com/service/getIpInfo.php?ip=" + ip);
            IpVo ipVo = JSON.parseObject(object, IpVo.class);
            // XX表示内网
            if (ipVo != null && ipVo.getCode() == 0 && !ipVo.getData().getRegion().equals("XX")) {
                System.out.println(ipVo.getData().getRegion());
                System.out.println(ipVo.getData().getCity());
                return ipVo.getData().getRegion() + ipVo.getData().getCity();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";

    }

//    public static void main(String[] args) {
//        String object =HttpUtil.get("http://ip.taobao.com/service/getIpInfo.php?ip=" + "60.176.91.64");
//        IpVo ipVo = JSON.parseObject(object, IpVo.class);
//         //XX表示内网
//        if (ipVo.getCode() == 0 && !ipVo.getData().getRegion().equals("XX")) {
//            System.out.println(ipVo.getData().getRegion());
//            System.out.println(ipVo.getData().getCity());
//        }
//    }

}
