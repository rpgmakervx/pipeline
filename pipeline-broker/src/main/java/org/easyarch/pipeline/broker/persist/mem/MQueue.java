package org.easyarch.pipeline.broker.persist.mem;

import java.util.List;

/**
 * Created by xingtianyu on 17-5-20
 * 下午3:35
 * description:
 */

public interface MQueue<E>{

    /**
     * 查看队列顶部一个消息
     * @return 返回消息体
     */
    E get();

    /**
     * 入队一个元素
     * @param element
     */
    void push(E element);

    /**
     * 批量入队
     * @param elements
     */
    void pushAll(E... elements);

    /**
     * 取出一个消息并在队列中删除
     * @return 返回消息体
     */
    E fetch();


    /**
     * 从顶部获取指定个数元素，并从队列移除
     * @param count 个数
     * @return
     */
    List<E> top(int count);



}
