package com.atguigu.mapreduce.outputformat;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

/**
 * @title: LogDriver
 * @projectName MapReduceDemo
 * @description: TODO
 * @author kbmgs
 * @date 2022/2/28 0:24
 */
public class LogDriver {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {

        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf);

        job.setJarByClass(LogDriver.class);
        job.setMapperClass(LogMapper.class);
        job.setReducerClass(LogReducer.class);

        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(NullWritable.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(NullWritable.class);

        //设置自定义的outputformat
        job.setOutputFormatClass(LogOutputFormat.class);

        FileInputFormat.setInputPaths(job, new Path("F:\\BaiduNetdiskDownload\\尚硅谷大数据技术之Hadoop3.x\\资料\\资料\\11_input\\inputoutputformat"));
        //虽然我们自定义了outputformat，但是因为我们的outputformat继承自fileoutputformat
        //而fileoutputformat要输出一个_SUCCESS文件，所以在这还得指定一个输出目录
        FileOutputFormat.setOutputPath(job, new Path("F:\\BaiduNetdiskDownload\\尚硅谷大数据技术之Hadoop3.x\\资料\\资料\\output_outputformat\\Log1"));

        boolean b = job.waitForCompletion(true);
        System.exit(b ? 0 : 1);
    }
}
