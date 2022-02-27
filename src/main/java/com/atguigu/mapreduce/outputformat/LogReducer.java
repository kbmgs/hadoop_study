package com.atguigu.mapreduce.outputformat;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * @title: LogReducer
 * @projectName MapReduceDemo
 * @description: TODO
 * @author kbmgs
 * @date 2022/2/27 23:50
 */
public class LogReducer extends Reducer<Text, NullWritable, Text, NullWritable> {

    @Override
    protected void reduce(Text key, Iterable<NullWritable> values, Reducer<Text, NullWritable, Text, NullWritable>.Context context) throws IOException, InterruptedException {

        //http://www.baidu.com
        //http://www.baidu.com
        //为防止以上重复Text被输出成一条，需要进行遍历

        for (NullWritable value : values) {
            context.write(key, NullWritable.get());
        }

    }
}
