package com.atguigu.mapreduce.writable;

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
public class FlowReducer extends Reducer<Text, FlowBean, Text, FlowBean> {
    // 定义输出的FlowBean类型对象--outV
    private FlowBean outV = new FlowBean();

    // 重写reduce方法，Iterable<FlowBean> 是一个集合，存储是相同手机号的对应的FlowBean。需要将其进行累加。
    @Override
    protected void reduce(Text key, Iterable<FlowBean> values, Reducer<Text, FlowBean, Text, FlowBean>.Context context) throws IOException, InterruptedException {

        // 定义流量的累加值
        long totalUp = 0;
        long totalDown = 0;

        // 1.遍历集合，累加值
        for (FlowBean value : values) {
            totalUp += value.getUpFlow();
            totalDown += value.getDownFlow();
        }

        // 2.封装outK,outV
        outV.setUpFlow(totalUp);
        outV.setDownFlow(totalDown);
        outV.setSumFlow();

        // 3.context写出
        context.write(key, outV);
    }
}
