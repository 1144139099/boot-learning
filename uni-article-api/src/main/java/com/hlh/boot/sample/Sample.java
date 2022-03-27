package com.hlh.boot.sample;


import com.aliyun.dysmsapi20170525.Client;
import com.aliyun.dysmsapi20170525.models.*;
import com.aliyun.teaopenapi.models.*;


public class Sample {
    public static Client createClient(String accessKeyId, String accessKeySecret) throws Exception{
        Config config = new Config()
                .setAccessKeyId(accessKeyId)
                .setAccessKeySecret(accessKeySecret);
        config.endpoint = "dysmsapi.aliyuncs.com";
        return new Client(config);
    }
    public static void main(String[] args) throws Exception {
        Client client = Sample.createClient("LTAI5tLxkY5GPmnp8nHket4w", "dlENm5Tip1XnqYXcEZfoXvHUn5H9vd");
        SendSmsRequest sendSmsRequest = new SendSmsRequest()
                .setSignName("阿里云短信测试")
                .setPhoneNumbers("13851826167")
                .setTemplateCode("SMS_154950909")
                .setTemplateParam("{\"code\":\"1234\"}");
        SendSmsResponse resp = client.sendSms(sendSmsRequest);
        System.out.println(resp);
    }
}
