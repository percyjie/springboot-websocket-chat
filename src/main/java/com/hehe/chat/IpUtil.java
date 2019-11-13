package com.hehe.chat;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * Created with percy.
 * Date: 2019/11/12
 */
public class IpUtil {


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
            System.out.println("ip："+ip);
            String object = HttpUtil.get("http://ip.taobao.com/service/getIpInfo.php?ip=" + ip);
            if(StringUtils.isNotBlank(object)){
                IpVo ipVo = JSON.parseObject(object, IpVo.class);
                // XX表示内网
                if (ipVo != null && ipVo.getCode() == 0 && !ipVo.getData().getRegion().equals("XX")) {
                    System.out.println(ipVo.getData().getRegion());
                    System.out.println(ipVo.getData().getCity());
                    return ipVo.getData().getRegion() + ipVo.getData().getCity();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";

    }

}
