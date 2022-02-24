package com.atguigu.mapreduce.partitionandWritableComparable;

import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * @title: FlowBean
 * @projectName MapReduceDemo
 * @description: 1.定义类，实现Writable接口
 *               2.重写序列化和反序列化方法
 *               3.重写空参构造
 *               4.重写toString方法，用于打印输出
 *
 *               计算号码的 上行流量，下行流量，总流量
 *
 *               额外需求：对总流量进行排序操作
 *               
 * @author kbmgs
 * @date 2022/2/16 20:58
 */

//对总流量进行排序

public class FlowBean implements WritableComparable<FlowBean> {

    private long upFlow; // 上行流量
    private long downFlow; // 下行流量
    private long sumFlow; // 总流量

    // 空参构造
    public FlowBean() {
    }

    // get和set方法
    public long getUpFlow() {
        return upFlow;
    }

    public void setUpFlow(long upFlow) {
        this.upFlow = upFlow;
    }

    public long getDownFlow() {
        return downFlow;
    }

    public void setDownFlow(long downFlow) {
        this.downFlow = downFlow;
    }

    public long getSumFlow() {
        return sumFlow;
    }

    public void setSumFlow(long sumFlow) {
        this.sumFlow = sumFlow;
    }

    // 重载一个无参的set方法，因为后续不会传递sumFlow，总流量由相加得到
    public void setSumFlow() {
        this.sumFlow = this.upFlow + this.downFlow;
    }

    // 序列化方法
    @Override
    public void write(DataOutput out) throws IOException {
        out.writeLong(upFlow);
        out.writeLong(downFlow);
        out.writeLong(sumFlow);
    }

    // 反序列化方法
    @Override
    public void readFields(DataInput in) throws IOException {
        this.upFlow = in.readLong();
        this.downFlow = in.readLong();
        this.sumFlow = in.readLong();
    }

    // 重写toString方法: 上行流量 + \t + 下行流量 + \t + 总流量
    @Override
    public String toString() {
        return upFlow + "\t" + downFlow + "\t" + sumFlow;
    }

    @Override
    public int compareTo(FlowBean o) {

        // 按照总流量的倒序排序
        if (this.sumFlow > o.sumFlow) {
            return -1;
        } else if (this.sumFlow < o.sumFlow) {
            return 1;
        } else {
            // 总流量一样时，按照上行流量排序正序排序 （二次排序）
            if (this.upFlow > o.upFlow) {
                return 1;
            } else if (this.upFlow > o.upFlow) {
                return -1;
            } else {
                // 总流量一样时，按照下行流量排序正序排序 （三次排序）
                if (this.downFlow > o.downFlow) {
                    return 1;
                } else if (this.downFlow > o.downFlow) {
                    return -1;
                } else {
                    return 0;
                }
            }
        }
    }
}
