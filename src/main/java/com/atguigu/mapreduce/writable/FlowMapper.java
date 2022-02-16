package com.atguigu.mapreduce.writable;

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
public class FlowMapper extends Mapper<LongWritable, Text, Text, FlowBean> {

    private Text outK = new Text();
    private FlowBean outV = new FlowBean();

    //重写map方法
    @Override
    protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, FlowBean>.Context context)
            throws IOException, InterruptedException {
        // 1.获取一行
        // 1	13736230513	192.196.100.1	www.atguigu.com	2481	24681	200
        String line = value.toString();

        // 2.切割文本（\t）
        // 文本被放入了String[]数组中：1,13736230513,192.196.100.1,www.atguigu.com,2481,24681,200
        String[] split = line.split("\t");

        // 3.抓取想要的数据：手机号13736230513，上行流量2481，下行流量24681
        String phone = split[1];
        String up = split[split.length - 3];
        String down = split[split.length - 2];

        // 4.封装（输出的对应的key和value(Text和FlowBean)）
        outK.set(phone);
        outV.setUpFlow(Long.parseLong(up)); //String转为long类型
        outV.setDownFlow(Long.parseLong(down));
        outV.setSumFlow(); //使用无参构造器，自动相加sum

        // 5.写出
        context.write(outK, outV);
    }
}
