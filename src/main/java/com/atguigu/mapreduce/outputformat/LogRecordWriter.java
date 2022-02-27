package com.atguigu.mapreduce.outputformat;

import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.RecordWriter;
import org.apache.hadoop.mapreduce.TaskAttemptContext;

import java.io.IOException;

/**
 * @title: LogRecordWriter
 * @projectName MapReduceDemo
 * @description: TODO
 * @author kbmgs
 * @date 2022/2/27 23:57
 */
public class LogRecordWriter extends RecordWriter<Text, NullWritable> {

    // 去除final
    private FSDataOutputStream atguiguOut;
    private FSDataOutputStream otherOut;

    public LogRecordWriter(TaskAttemptContext job) {
        // 创建两条流
        try {
            // FileSystem.get()需要传入Configuration
            // 不要直接new Configuration，而是直接使用job中的.getConfiguration()
            FileSystem fileSystem = FileSystem.get(job.getConfiguration());

            // 开始创建流
            // 创建好输出流对象后，ctrl alt f 将两条流(内部变量)升级为全局变量
            atguiguOut = fileSystem.create(new Path("F:\\BaiduNetdiskDownload\\尚硅谷大数据技术之Hadoop3.x" +
                    "\\资料\\资料\\output_outputformat\\atguigu.log"));
            otherOut = fileSystem.create(new Path("F:\\BaiduNetdiskDownload\\尚硅谷大数据技术之Hadoop3.x\\" +
                    "资料\\资料\\output_outputformat\\other.log"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void write(Text key, NullWritable value) throws IOException, InterruptedException {
        // 创建两条输出流 -> 写出内容到对应的流
        // Text类型的key，转换为String
        String log = key.toString();
        // 具体写
        if (log.contains("atguigu")) {
            atguiguOut.writeBytes(log + "\n"); // 补充换行符
        } else {
            otherOut.writeBytes(log + "\n");
        }
    }

    @Override
    public void close(TaskAttemptContext context) throws IOException, InterruptedException {

        // 关流
        IOUtils.closeStream(atguiguOut);
        IOUtils.closeStream(otherOut);
    }
}

