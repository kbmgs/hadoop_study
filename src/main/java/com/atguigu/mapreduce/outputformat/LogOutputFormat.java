package com.atguigu.mapreduce.outputformat;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.RecordWriter;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

/**
 * @title: LogOutputFormat
 * @projectName MapReduceDemo
 * @description: TODO
 * @author kbmgs
 * @date 2022/2/27 23:54
 */

// 泛型来源为：reduce输出的KV
public class LogOutputFormat extends FileOutputFormat<Text, NullWritable> {

    // 需要重写getRecordWriter()方法，输出的是RecordWriter<Text, NullWritable>
    @Override
    public RecordWriter<Text, NullWritable> getRecordWriter(TaskAttemptContext job) throws IOException, InterruptedException {

        // 不存在LogRecordWriter对象类，此时AltEnter自定义创建
        LogRecordWriter lrw = new LogRecordWriter(job); // 将job传入LogRecordWriter()，此时AltEnter新建带参构造器
        return lrw;
    }
}
