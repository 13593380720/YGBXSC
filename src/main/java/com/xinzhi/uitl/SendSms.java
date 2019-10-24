package com.xinzhi.uitl;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.xinzhi.servlet.RegisterServlet;

/*
pom.xml
<dependency>
  <groupId>com.aliyun</groupId>
  <artifactId>aliyun-java-sdk-core</artifactId>
  <version>4.0.3</version>
</dependency>
*/
public class SendSms {
    public static int getSjs() {
        return sjs;
    }

    public static void setSjs(int sjs) {
        SendSms.sjs = sjs;
    }

    /*public static int main(String[] args) {
            RegisterServlet rs = new RegisterServlet();
            DefaultProfile profile = DefaultProfile.getProfile
            ("cn-hangzhou", "LTAI4FkZ2zbTeCRcfLjkYtJj", "a0iuwt5WXBBwgajNZfsZ2Edv3PhJ6c");
            IAcsClient client = new DefaultAcsClient(profile);

            CommonRequest request = new CommonRequest();
            request.setMethod(MethodType.POST);
            request.setDomain("dysmsapi.aliyuncs.com");
            request.setVersion("2017-05-25");
            request.setAction("SendSms");
            request.putQueryParameter("RegionId", "cn-hangzhou");
            request.putQueryParameter("PhoneNumbers", "18234813623");
            request.putQueryParameter("SignName", "阳光保险");
            request.putQueryParameter("TemplateCode", "SMS_175532400");
            request.putQueryParameter("TemplateParam", "{\"code\":" + sjs + "}");
            try {
                CommonResponse response = client.getCommonResponse(request);
                System.out.println(response.getData());
            } catch (ServerException e) {
                e.printStackTrace();
            } catch (ClientException e) {
                e.printStackTrace();
            }
            return sjs;
        }
    }*/
    private static int sjs = (int) ((Math.random() * 9 + 1) * 1000);
    public static int SendCode(String uphone) {
        DefaultProfile profile = DefaultProfile.getProfile
                ("cn-hangzhou", "LTAI4FkZ2zbTeCRcfLjkYtJj", "a0iuwt5WXBBwgajNZfsZ2Edv3PhJ6c");
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        //request.setProtocol(ProtocolType.HTTPS);
        request.setMethod(MethodType.POST);
        //阿里云对应发送短信的服务器地址
        request.setDomain("dysmsapi.aliyuncs.com");
        //对应的版本号
        request.setVersion("2017-05-25");

        request.setAction("SendSms");
        request.putQueryParameter("PhoneNumbers", uphone);
        request.putQueryParameter("SignName", "阳光保险");
        request.putQueryParameter("TemplateCode", "SMS_175532400");
        request.putQueryParameter("TemplateParam", "{\"code\":"+sjs+"}");
        try {
            CommonResponse response = client.getCommonResponse(request);
            System.out.println(response.getData());
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
        return sjs;
    }


}

