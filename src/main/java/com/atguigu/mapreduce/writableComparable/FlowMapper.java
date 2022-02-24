package com.atguigu.mapreduce.writableComparable;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * @title: FlowMapper
 * @projectName MapReduceDemo
 * @description: TODO
 * @author kbmgs
 * @date 2022/2/16 21:31
 */

/**
 * 输出：Text, FlowBean（手机号，flowbean包含上行，下行，总流量）
 */

// FlowBean和Text互换位置
public class FlowMapper extends Mapper<LongWritable, Text, FlowBean, Text> {

    // 与writable时互换key与value
    private FlowBean outK = new FlowBean();
    private Text outV = new Text();

    @Override
    protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, FlowBean, Text>.Context context)
            throws IOException, InterruptedException {

        // 获取一行
        String line = value.toString();

        // 切割
        String[] split = line.split("\t");

        // 封装 此时outV为手机号
        outV.set(split[0]);
        outK.setUpFlow(Long.parseLong(split[1]));
        outK.setDownFlow(Long.parseLong(split[2]));
        outK.setSumFlow(); //自动累加

        // 写出
        context.write(outK, outV);

    }
}

