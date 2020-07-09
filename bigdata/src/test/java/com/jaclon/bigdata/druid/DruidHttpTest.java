/**
 * Juzifenqi.com Inc.
 * Copyright (c) 2019-2029 All Rights Reserved.
 */
package com.jaclon.bigdata.druid;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URI;

/**
 * @Classname DruidHttpTest
 * @Description TODO
 *
 * @author jaclon
 * @date 2019/11/27
 */
public class DruidHttpTest {

    public static void main(String[] args) {
        queryDruid();
    }
    public static String queryDruid(){

        String sql = "{\"query\":\" select certCardNo,customerId,customerName,mobile,channelId,phone1,phone2,receiptPhone,companyPhone,registCode,registAddress,receiptAddress,homeAddress,consigneeGps,gpsLocation,deviceCode,wifiIp,ssid,wifiMac,__time from druid_order_flow where registCode = 'y7538' \"}";
        String url = "http://172.18.0.24:8082/druid/v2/sql/?pretty";
        /*"http://es3:8082/druid/v2/sql/?pretty"
        String msg = MyHttpResponse.postResponse(url,sql,"{\"Content-Type\": \"application/json\"}");*/
//        String sql = "{\"query\":\"SELECT systemId,COUNT(1) AS CNT,AVG(case when DURING_TIME>1800000 then 1800000 else DURING_TIME end) AS AVG_TIME FROM (SELECT orderNo, systemId,TIMESTAMP_TO_MILLIS(MAX(__time))-TIMESTAMP_TO_MILLIS(MIN(__time)) AS DURING_TIME FROM exception_warning WHERE  __time > '2019-11-21 15:53:03' and orderNo not in('','null','123456') GROUP BY orderNo, systemId)WHERE DURING_TIME <> 0 GROUP BY systemId\"}";
        HttpPost httpPost = new HttpPost();
        httpPost.setHeader("Content-Type","application/json; charset=UTF-8");
        httpPost.setURI(URI.create(url));
        httpPost.setEntity(new StringEntity(sql,"UTF-8"));
        String str = response(httpPost);
        System.out.println(str);
        return null;
    }

    private static String response(HttpUriRequest request){
        CloseableHttpClient httpClient = HttpClients.createDefault();
        try {
            CloseableHttpResponse response = httpClient.execute(request);
            String res = EntityUtils.toString(response.getEntity());
            return res;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
