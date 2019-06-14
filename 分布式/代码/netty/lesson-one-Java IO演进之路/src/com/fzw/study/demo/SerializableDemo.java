package com.fzw.study;

/**
 * @author : fangzw
 * @ClassName SerializableDemo
 * @Description :  序列化测试Demo
 * @date: 2019/6/4 16:59
 */
public class SerializableDemo {

    /**
     * 对象反序列化时，如果父类未实现序列化接口，则反序列出的对象会再次调用父类的构造函数来完成属于父类那部分内容的初始化。
     * 1、当将一个父类没有实现序列化的对象son使用ObjectOutputStream流写到本地文件中时，没有能将该对象中属于父类的部分写入到文件，因为ObjectOutputStream流不能将一个没有实现序列化的类的对象写入文件中。当将本地文件中保存的son对象通过ObjectInputStream流反序列化到程序中，由于缺少属于父类的部分信息，则需要再次调用父类的构造器来完成初始化。
     *
     */
    public static void main(String[] args) throws Exception{
        
    }
}
