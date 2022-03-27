package com.hlh.boot.util;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.aliyun.dysmsapi20170525.Client;
import com.aliyun.dysmsapi20170525.models.SendSmsRequest;
import com.aliyun.dysmsapi20170525.models.SendSmsResponse;
import com.aliyun.teaopenapi.models.Config;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
@Slf4j
public class SmsUtil {
    @Resource
    private AliyunResource aliyunResource;

    public boolean sendSms(String phone, String code){
        Config config = new Config()
                .setAccessKeyId(aliyunResource.getAccessKeyId())
                .setAccessKeySecret(aliyunResource.getAccessKeySecret());
        config.endpoint = "dysmsapi.aliyuncs.com";
        try{
            Client client = new Client(config);
            SendSmsRequest sendSmsRequest = new SendSmsRequest()
                    .setPhoneNumbers(phone)
                    .setSignName(aliyunResource.getSignName())
                    .setTemplateCode(aliyunResource.getTemplateCode())
                    .setTemplateParam("{\"code\":\"" + code + "\"}");
            SendSmsResponse resp = client.sendSms(sendSmsRequest);
            log.info(resp.getBody().getCode());
//            log.info(resp.getBody().getMessage());
//            log.info(resp.toString());
//            String res = String.valueOf(resp.getBody());
//            log.info(res);
//            JSONObject jsonObj = JSON.parseObject(res);
            if ("OK".equals(resp.getBody().getCode())) {
                return true;
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return false;
    }
}
