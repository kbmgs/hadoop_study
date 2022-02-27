package com.atguigu.mapreduce.outputformat;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * @title: LogMapper
 * @projectName MapReduceDemo
 * @description: TODO
 * @author kbmgs
 * @date 2022/2/27 23:42
 */
public class LogMapper extends Mapper<LongWritable, Text, Text, NullWritable> {

    @Override
    protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, NullWritable>.Context context) throws IOException, InterruptedException {
        //输入：
        //http://www.baidu.com
        //http://www.google.com
        //期望输出：
        //(http://www.baidu.com, NullWritable)

        //map阶段不做任何处理，来一行写一行，按照context.write()所指定格式
        context.write(value, NullWritable.get());

    }
}
