package com.atguigu.mapreduce.partitionandWritableComparable;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

/**
 * @title: ProvincePartitioner
 * @projectName MapReduceDemo
 * @description: TODO
 * @author kbmgs
 * @date 2022/2/24 22:44
 */
public class ProvincePartitioner extends Partitioner<FlowBean, Text> {

    @Override
    public int getPartition(FlowBean flowBean, Text text, int numPartitions) {

        String phone = text.toString();

        String prePhone = phone.substring(0, 3);

//        int partition;
        // 返回int类型的分区号
        if ("136".equals(prePhone)) {
            return 1;
        } else if ("137".equals(prePhone)) {
            return 2;
        } else if ("138".equals(prePhone)) {
            return 3;
        } else if ("139".equals(prePhone)) {
            return 4;
        } else {
            return 0;
        }
    }
}
