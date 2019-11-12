package com.hehe.chat;

/**
 * Created with percy.
 * Date: 2019/11/12
 */

import java.io.Serializable;

/**
 * @Author: nelson
 * @Description: get city by ip
 * @Date: created in 2018/03/31/17:40
 */
public class IpVo implements Serializable{
    private Integer code;
    private Address data;

    public class Address implements Serializable {
        private String ip;
        private String region;
        private String city;

        public String getIp() {
            return ip;
        }

        public void setIp(String ip) {
            this.ip = ip;
        }

        public String getRegion() {
            return region;
        }

        public void setRegion(String region) {
            this.region = region;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Address getData() {
        return data;
    }

    public void setData(Address data) {
        this.data = data;
    }
}



