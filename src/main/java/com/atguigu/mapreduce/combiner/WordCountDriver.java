package com.atguigu.mapreduce.combiner;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
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


        // combiner实现方法1：单独写Combiner类，将Combiner与driver关联
//        job.setCombinerClass(WordCountCombiner.class);

        // WordCountCombiner与WordCountReducer其实完全一致，因此可以直接设setCombinerClass()为Reducer
        // combiner实现方法2：将Reducer作为Combiner在Driver驱动类中指定
        job.setCombinerClass(WordCountReducer.class);

        // 6.设置输入路径和输出路径
        FileInputFormat.setInputPaths(job, new Path("F:\\BaiduNetdiskDownload\\尚硅谷大数据技术之Hadoop3.x\\资料\\资料\\11_input\\inputword"));
        // 在mapreduce中，如果输出路径存在，则程序报错
        FileOutputFormat.setOutputPath(job, new Path("F:\\BaiduNetdiskDownload\\尚硅谷大数据技术之Hadoop3.x\\资料\\资料\\output\\output_combiner_3"));

        // 7.提交job
        boolean result = job.waitForCompletion(true);

        System.exit(result ? 0 : 1);

    }


}
