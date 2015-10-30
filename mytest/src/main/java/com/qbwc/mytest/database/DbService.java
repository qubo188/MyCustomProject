package com.qbwc.mytest.database;

import com.qbwc.mytest.entity.BaseBean;

import de.greenrobot.dao.AbstractDao;

/**
 * 项目名称：MyCustomProject
 * 类描述：数据库的 操作类
 * 创建人：qubo
 * 创建时间：2015/10/14 0014 下午 1:49
 */
public class DbService {


    /**
     * @param bean  实体javaBean
     * @param dao   Bean的管理操作类
     * @param <T1>
     * @param <T2>
     * @return >0 数据插入 成功   <0 数据插入失败
     * Description : 主要用于  Entity into DataBase
     */
    public static <T1 extends BaseBean , T2 extends AbstractDao> long insert(T1 bean , T2 dao){
        return dao.insert(bean);
    }

    /**
     * @param dao   Bean的管理操作类
     * @param <T1>
     * @param <T2>
     * @return 具体查询出来的 对象 。如果无数据 将返回null
     * Description : 主要用于  select Database to bean
     */
    public static <T1 extends BaseBean , T2 extends AbstractDao> T1 queryLastOne(T2 dao, String tableName){
        return (T1)dao.queryRaw("order by _id desc").get(0);
    }
}
