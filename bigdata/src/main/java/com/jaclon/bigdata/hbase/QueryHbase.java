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

public class QueryHbase {

    public static void main(String[] args) {

        try {

            String result = queryOverdue("55841999,65574637,61265451,213214213").toString();
            String result2 = queryBlack("1599867579,13800138000,18664593742,123456789").toString();
            System.out.println(result);
            System.out.println(result2);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static List<String> queryBlack(String parameter) throws IOException {

        String hive_black_phone = "hive_black_phone";

        //配置hbase链接
        Configuration conf = HBaseConfiguration.create();
        conf.set("hbase.zookeeper.quorum", "172.18.0.130,172.18.0.131,172.18.0.132");
        conf.set("hbase.zookeeper.property.clientPort", "2181");
        conf.set("hbase.master", "172.18.0.130:16000");
        //conf.set(TableInputFormat.INPUT_TABLE, hive_black_phone);

        List<String> list = new ArrayList<String>();
        List<Get> getList = new ArrayList();

        //连接到hbase中对应表
        Connection conn = ConnectionFactory.createConnection(conf);
        Table table = conn.getTable(TableName.valueOf(hive_black_phone));

        try {
            List<String> rowkeyList = Arrays.asList(parameter.split(","));

            if (rowkeyList.size() == 0 || rowkeyList.isEmpty()) {
                return null;
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
                        list.add(row);
                    }
                }
            }

        } catch (IllegalArgumentException e) {
            System.out.print("java.lang.IllegalArgumentException: Row length is 0");
            e.printStackTrace();
        }

        return list;
    }

    public static List<String> queryOverdue(String parameter) throws IOException {

        String hive_overdue_customer = "hive_overdue_customer";

        //配置hbase链接
        Configuration conf = HBaseConfiguration.create();
        conf.set("hbase.zookeeper.quorum", "172.18.0.130,172.18.0.131,172.18.0.132");
        conf.set("hbase.zookeeper.property.clientPort", "2181");
        conf.set("hbase.master", "172.18.0.130:16000");
        //conf.set(TableInputFormat.INPUT_TABLE, hive_overdue_customer);

        List<String> list = new ArrayList<String>();
        List<Get> getList = new ArrayList();

        //连接到hbase中对应表
        Connection conn = ConnectionFactory.createConnection(conf);
        Table table = conn.getTable(TableName.valueOf(hive_overdue_customer));
        try {
            List<String> rowkeyList = Arrays.asList(parameter.split(","));

            if (rowkeyList.size() == 0 || rowkeyList.isEmpty()) {
                return null;
            } else {
                //将查询的rowkey list转为getList
                for (String rowkey : rowkeyList) {
                    if (rowkey.length() != 0) {
                        Get get = new Get(Bytes.toBytes(rowkey));
                        getList.add(get);
                    }
                }

                //将getList在hbase表中查询返回bytes result
                Result[] results = table.get(getList);
                System.out.println(results);
                for (Result result : results) {

                    //获取rowkey对应的value，判断value的值并将符合条件的结果解析成list返回
                    String row = Bytes.toString(result.getRow());
                    if(row != null){
                        int value  = Integer.valueOf(Bytes.toString(result.getValue(Bytes.toBytes("params"), Bytes.toBytes("overdue_days"))));

                        if (row != null && value > 0) {
                            list.add(row);
                        }
                    }

                }
            }

        } catch (IllegalArgumentException e) {
            System.out.print("java.lang.IllegalArgumentException: Row length is 0");
            e.printStackTrace();
        }
        return list;

    }
}
