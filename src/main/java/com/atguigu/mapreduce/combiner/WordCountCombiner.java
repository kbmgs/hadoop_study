package com.atguigu.mapreduce.combiner;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * @title: WordCountCombiner
 * @projectName MapReduceDemo
 * @description: TODO
 * @author kbmgs
 * @date 2022/2/27 22:40
 */
public class WordCountCombiner extends Reducer<Text, IntWritable, Text, IntWritable> {

    private IntWritable outV = new IntWritable();
    // Combiner类继承的是Reducer类,重写reduce方法
    @Override
    protected void reduce(Text key, Iterable<IntWritable> values, Reducer<Text, IntWritable, Text, IntWritable>.Context context) throws IOException, InterruptedException {
        int sum = 0;
        for (IntWritable value : values) {
            sum += value.get(); // IntWritable.get()获取int类型数据
        }

        outV.set(sum);

        context.write(key,outV);
    }
}
