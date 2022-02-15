package com.atguigu.mapreduce.wordcount2;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * @title: WordCountReducer
 * @projectName MapReduceDemo
 * @description: TODO
 * @author kbmgs
 * @date 2022/2/7 21:54
 */

/**
 * KEYIN, reduce阶段输入的key的类型：Text（map阶段的输出）
 * VALUEIN, reduce阶段输入的value类型：IntWritable（map阶段的输出）
 * KEYOUT, reduce阶段输出的key类型：Text
 * VALUEOUT reduce阶段输出的value类型：IntWritable
 */

public class WordCountReducer extends Reducer<Text, IntWritable, Text, IntWritable> {

    private IntWritable outV = new IntWritable();

    @Override
    protected void reduce(Text key, Iterable<IntWritable> values, Reducer<Text, IntWritable, Text, IntWritable>.Context context) throws IOException, InterruptedException {

        int sum = 0;
        // 对传进来的values进行遍历 一次循环得到：atguigu,(1,1)
        // 累加
        for (IntWritable value : values) {
            sum += value.get(); //IntWritable类型.get()
        }

        outV.set(sum);

        // 写出
        context.write(key,outV);

    }
}
