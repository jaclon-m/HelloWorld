/**
 * Juzifenqi.com Inc.
 * Copyright (c) 2019-2029 All Rights Reserved.
 */
package com.jaclon.bigdata.hbase;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Classname HbaseTest
 * @Description TODO
 *
 * @author jaclon
 * @date 2019/12/31
 */
public class HbaseTest {
    /**
     * 查询黑名单关键词
     */
    public static final String HIVE_BLACK_PHONE = "hive_black_phone";
    /**
     * 查询逾期用户关键词
     */
    public static final String HIVE_OVERDUE_CUSTOMER = "hive_overdue_customer";



    public static void main(String[] args) throws IOException {


        String str = "123456777";

        System.setProperty("hadoop.home.dir","/opt/cloudera/parcels/CDH-6.1.1-1.cdh6.1.1.p0.875250/lib/hadoop");

        //配置hbase链接
        Configuration conf = HBaseConfiguration.create();
        conf.set("hbase.zookeeper.quorum", "172.18.0.21,172.18.0.22,172.18.0.23");
        conf.set("hbase.zookeeper.property.clientPort", "2181");
        conf.set("hbase.master", "172.18.0.11:16000");
        //conf.set(TableInputFormat.INPUT_TABLE, hive_black_phone);

        List<Get> getList = new ArrayList();
        StringBuffer blackPhoneBuffer = new StringBuffer();

        //连接到hbase中对应表
        Connection conn = ConnectionFactory.createConnection(conf);
        Table table = conn.getTable(TableName.valueOf(HIVE_BLACK_PHONE));

        try {
            List<String> rowkeyList = Arrays.asList(str.split(","));

            if (rowkeyList.size() == 0 || rowkeyList.isEmpty()) {
            } else {
                //将查询的rowkey list转为getList
                for (String rowkey : rowkeyList) {
                    if (rowkey.length() != 0) {
                        Get get = new Get(Bytes.toBytes(rowkey));
                        getList.add(get);
                    }
                }

                //将getList在hbase表中查询返回bytes result，并将结果解析成list返回
                Result[] results = table.get(getList);
                for (Result result : results) {
                    String row = Bytes.toString(result.getRow());
                    if (row != null) {
                        blackPhoneBuffer.append(row).append("#");
                    }
                }
            }

        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        System.out.println(blackPhoneBuffer.toString() + "================================================");
    }
}
