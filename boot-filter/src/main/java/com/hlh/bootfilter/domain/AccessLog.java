package com.hlh.bootfilter.domain;

import com.hlh.bootfilter.util.FormatUtils;
import lombok.Data;

import java.util.Date;

@Data
public class AccessLog {
    private String username;
    private String url;
    private Integer duration;
    private String httpMethod;
    private Integer httpStatus;
    private String ip;
    private Date createTime;

    @Override
    public String toString(){
        return "{" + "username=" + this.username + "," + "url=" + this.url + "," + "duration=" + this.duration + "," + "httpStatus" + this.httpStatus + "," + "ip=" + this.ip + "," + "createTime=" + FormatUtils.forTime(this.createTime) + "," + "}";
    }
}
