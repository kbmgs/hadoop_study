package com.atguigu.mapreduce.writableComparable;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

/**
 * @title: FlowDriver
 * @projectName MapReduceDemo
 * @description: 1.获取job对象
 * @author kbmgs
 * @date 2022/2/16 22:25
 */
public class FlowDriver {
    public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException {
        // 1.获取job对象
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf);

        // 2.设置jar包
        job.setJarByClass(FlowDriver.class);

        // 3.关联mapper和reducer
        job.setMapperClass(FlowMapper.class);
        job.setReducerClass(FlowReducer.class);

        // 4.设置map输出的key和value类型
        // 将map的outK和outV互换位置
        job.setMapOutputKeyClass(FlowBean.class);
        job.setMapOutputValueClass(Text.class);

        // 5.设置最终输出的key和value类型
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(FlowBean.class);

        // 6.设置数据的输入路径和输出路径
        // 输入数据为上一个案例(writable)的结果
        // 输出结果为总流量的倒序
        FileInputFormat.setInputPaths(job, new Path("F:\\BaiduNetdiskDownload\\尚硅谷大数据技术之Hadoop3.x\\资料\\资料\\11_input\\output4"));
        FileOutputFormat.setOutputPath(job, new Path("F:\\BaiduNetdiskDownload\\尚硅谷大数据技术之Hadoop3.x\\资料\\资料\\11_input\\output5_3"));

        // 7.提交job
        boolean result = job.waitForCompletion(true);
        System.exit(result ? 0 : 1);
    }


}
