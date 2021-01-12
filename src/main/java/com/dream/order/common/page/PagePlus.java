package com.dream.order.common.page;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;

import java.sql.Statement;
import java.util.Properties;

/**
 * @author jiangll01
 * @Date: 2020/8/20 9:16
 * @Description: 自定义插件
 * 通过 MyBatis 提供的强大机制，使用插件是非常简单的，
 * 只需实现 Interceptor 接口，并指定想要拦截的方法签名即可。
 * MyBatis 允许使用插件来拦截的方法调用包括：
 * Executor (update, query, flushStatements, commit, rollback, getTransaction, close, isClosed)
 * ParameterHandler (getParameterObject, setParameters)
 * ResultSetHandler (handleResultSets, handleOutputParameters)
 * StatementHandler (prepare, parameterize, batch, update, query)
 * 1、实现Interceptor接口，重写方法
 * 2、添加mybatis @Intercepts注解，把插件添加到spring容器进行管理
 * 3、@Intercepts可以对多个类和方法进行拦截
 */
@Slf4j
@Intercepts({@Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class}),
        @Signature(type = StatementHandler.class, method = "query", args = {Statement.class, ResultHandler.class})})
public class PagePlus implements Interceptor {
    private String dialect = "";

    private String pageSqlId = "";

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        log.info("执行前进行Executor拦截:", invocation.toString());
        // implement pre processing if need
        Object returnObject = invocation.proceed();
        // implement post processing if need
        return returnObject;
    }

    //进行动态代理，将传入的对象进行封装、包装
    @Override
    public Object plugin(Object o) {
        return Plugin.wrap(o, this);
    }

    @Override
    public void setProperties(Properties properties) {
        this.dialect = properties.getProperty("dialect");
        this.pageSqlId = properties.getProperty("pageSqlId");
    }
}
