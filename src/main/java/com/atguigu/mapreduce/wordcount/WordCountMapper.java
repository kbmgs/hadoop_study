package com.atguigu.mapreduce.wordcount;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * @title: WordCountMapper
 * @projectName MapReduceDemo
 * @description: TODO
 * @author kbmgs
 * @date 2022/2/7 21:54
 */

/**
 * KEYIN, map阶段输入的key的类型：偏移量，LongWritable类型
 * VALUEIN, map阶段输入的value类型：Text(String)
 * KEYOUT, map阶段输出的key类型：Text
 * VALUEOUT map阶段输出的value类型：IntWritable
 */

/**
 * 输入数据：
 * atguigu atguigu
 * ss ss
 * cls cls
 * jiao
 * banzhang
 * xue
 * hadoop
 */

//继承hadoop包中的Mapper方法
public class WordCountMapper extends Mapper<LongWritable, Text, Text, IntWritable> {

    private Text outK = new Text();
    // map阶段不进行聚合，只切割单词并计数为1
    private IntWritable outV = new IntWritable(1);

    //重写Mapper中的map方法
    @Override
    protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, IntWritable>.Context context) throws IOException, InterruptedException {
        // 1.获取一行
        // value转为toString，toString下有更丰富的字符串处理
        String line = value.toString();

        // 2.切割
        // 每一行按空格切分的单词，都放进了words数组中，获取的一行字符串变为：
        //atguigu
        //atguigu
        String[] words = line.split(" ");

        // 3.循环写出
        // 遍历words
        for (String word : words) {

            // 封装outK
            outK.set(word);

            // 写出
            //write(KEYOUT key, VALUEOUT value)
            context.write(outK, outV);
        }


    }
}
