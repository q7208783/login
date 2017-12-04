package com.zcc.login.common.interceptor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.scripting.defaults.DefaultParameterHandler;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zcc.login.common.contextholder.DataSourceContextHolder;
import com.zcc.login.common.contextholder.DynamicDataSource;
import com.zcc.login.model.PageInfo;

/**
 * Created by ZhangChicheng on 2017/12/4.
 */
@Intercepts({
	@Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class,
		ResultHandler.class})
})
public class PageInfoInterceptor implements Interceptor {

	@Autowired
	DynamicDataSource dynamicDataSource;

	@Override
	public Object intercept(Invocation invocation) throws Throwable {
//		MappedStatement mappedStatement=(MappedStatement)invocation.getArgs()[0];
//		Object parameter = invocation.getArgs()[1];
//		if(parameter instanceof PageInfo){
//			BoundSql boundSql = mappedStatement.getBoundSql(parameter);
//			String originalSql = boundSql.getSql().trim();
//			Object parameterObject = boundSql.getParameterObject();
//
//			String countSql = getTotalRowSql(originalSql);
//			Connection connection=mappedStatement.getConfiguration().getEnvironment().getDataSource().getConnection();
//			PreparedStatement countStmt = connection.prepareStatement(countSql);
//
//			BoundSql countBS = copyFromBoundSql(mappedStatement, boundSql, countSql);
//
//			DefaultParameterHandler parameterHandler = new DefaultParameterHandler(mappedStatement, parameterObject, countBS);
//			parameterHandler.setParameters(countStmt);
//			ResultSet rs = countStmt.executeQuery();
//			int totalRows=0;
//			if (rs.next()) {
//				totalRows = rs.getInt(1);
//			}
//			rs.close();
//			countStmt.close();
//			connection.close();
//
//			//分页计算
//			((PageInfo)parameter).setTotalRows(totalRows);
//
//			int offset = (((PageInfo)parameter).getPageNo() - 1) * ((PageInfo)parameter).getPageSize();
//			StringBuffer sb = new StringBuffer();
//			sb.append(originalSql).append(" limit ").append(offset).append(",").append(((PageInfo)parameter).getPageSize());
//			BoundSql newBoundSql = copyFromBoundSql(mappedStatement, boundSql, sb.toString());
//			MappedStatement newMs = copyFromMappedStatement(mappedStatement,new BoundSqlSqlSource(newBoundSql));
//			invocation.getArgs()[0]= newMs;
//		}
		return invocation.proceed();
	}

	private String getTotalRowSql(String originSql){
		return "SELECT COUNT(*) FROM (" + originSql + ") aliasForPage";
	}

	/**
	 * 复制BoundSql对象
	 */
	private BoundSql copyFromBoundSql(MappedStatement ms, BoundSql boundSql, String sql) {
		BoundSql newBoundSql = new BoundSql(ms.getConfiguration(),sql, boundSql.getParameterMappings(), boundSql.getParameterObject());
		for (ParameterMapping mapping : boundSql.getParameterMappings()) {
			String prop = mapping.getProperty();
			if (boundSql.hasAdditionalParameter(prop)) {
				newBoundSql.setAdditionalParameter(prop, boundSql.getAdditionalParameter(prop));
			}
		}
		return newBoundSql;
	}

	/**
	 * 复制MappedStatement对象
	 */
	private MappedStatement copyFromMappedStatement(MappedStatement ms,SqlSource newSqlSource) {
		MappedStatement.Builder builder = new MappedStatement.Builder(ms.getConfiguration(),ms.getId(),newSqlSource,ms.getSqlCommandType());

		builder.resource(ms.getResource());
		builder.fetchSize(ms.getFetchSize());
		builder.statementType(ms.getStatementType());
		builder.keyGenerator(ms.getKeyGenerator());
		if(ms.getKeyProperties()!=null){
			for(String keyProperty:ms.getKeyProperties()){
				builder.keyProperty(keyProperty);
			}
		}
		builder.timeout(ms.getTimeout());
		builder.parameterMap(ms.getParameterMap());
		builder.resultMaps(ms.getResultMaps());
		builder.resultSetType(ms.getResultSetType());
		builder.cache(ms.getCache());
		builder.flushCacheRequired(ms.isFlushCacheRequired());
		builder.useCache(ms.isUseCache());

		return builder.build();
	}

	public class BoundSqlSqlSource implements SqlSource {
		BoundSql boundSql;
		public BoundSqlSqlSource(BoundSql boundSql) {
			this.boundSql = boundSql;
		}
		public BoundSql getBoundSql(Object parameterObject) {
			return boundSql;
		}
	}

	@Override
	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	@Override
	public void setProperties(Properties properties) {

	}


}
