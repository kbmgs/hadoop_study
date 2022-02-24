package com.atguigu.mapreduce.partitionandWritableComparable;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * @title: FlowReducer
 * @projectName MapReduceDemo
 * @description: TODO
 * @author kbmgs
 * @date 2022/2/16 22:08
 */
public class FlowReducer extends Reducer<FlowBean, Text, Text, FlowBean> {

    //此时inK是流量，inV是手机号，相同的流量时要进行遍历
    @Override
    protected void reduce(FlowBean key, Iterable<Text> values, Reducer<FlowBean, Text, Text, FlowBean>.Context context) throws IOException, InterruptedException {

        for (Text value : values) {
            context.write(value,key);

        }
    }
}
