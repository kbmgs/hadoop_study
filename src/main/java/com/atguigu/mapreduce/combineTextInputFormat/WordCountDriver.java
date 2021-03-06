package com.atguigu.mapreduce.combineTextInputFormat;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.CombineTextInputFormat;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

/**
 * @title: WordCountDriver
 * @projectName MapReduceDemo
 * @description: TODO
 * @author kbmgs
 * @date 2022/2/7 21:54
 */
public class WordCountDriver {
    public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException {
        // 1.获取job
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf);

        // 2.设置jar包路径
        job.setJarByClass(WordCountDriver.class);

        // 3.关联mapper和reducer
        job.setMapperClass(WordCountMapper.class);
        job.setReducerClass(WordCountReducer.class);
        //driver mapper reducer产生了对应的联系

        // 4.设置map输出的kv类型
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);

        // 5.设置最终输出的kV类型
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        // 章节3.1.6 CombineTextInputFormat案例实操
        // 如果不设置InputFormat，它默认用的是TextInputFormat.class
        job.setInputFormatClass(CombineTextInputFormat.class);

        // 虚拟存储切片最大值设置4m（最终结果为切3片）
        //CombineTextInputFormat.setMaxInputSplitSize(job, 4194304);

        //虚拟存储切片最大值设置20m
        CombineTextInputFormat.setMaxInputSplitSize(job, 20971520);

        // 6.设置输入路径和输出路径
        FileInputFormat.setInputPaths(job, new Path("F:\\BaiduNetdiskDownload\\尚硅谷大数据技术之Hadoop3.x\\资料\\资料\\11_input\\inputcombinetextinputformat"));
        // 在mapreduce中，如果输出路径存在，则程序报错
        FileOutputFormat.setOutputPath(job, new Path("F:\\BaiduNetdiskDownload\\尚硅谷大数据技术之Hadoop3.x\\资料\\资料\\outputCombine3_0222"));

        // 7.提交job
        boolean result = job.waitForCompletion(true);

        System.exit(result ? 0 : 1);

    }


}
