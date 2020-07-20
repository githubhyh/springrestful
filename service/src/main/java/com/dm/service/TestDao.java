package com.dm.service;

import com.dm.domain.Test;

import java.util.List;

public interface TestDao {
    final  static int i = 0;

    default void test() {
        System.out.println(i);
    }
    /**
     * 新增数据
     * */
    public int insert(Test test);

    /**
     * 查找test集合
     * @param test 查询参数
     * */
    List<Test> find(Test test);

    /**
     * 模拟抢票
     * @param tickets 购买票数
     * */
    void orderTickets(int tickets);
}
