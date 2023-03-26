package org.fd.util;

import com.system.supercommon.util.spring.SpringUtils;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import org.springframework.util.ObjectUtils;

import java.util.List;

/**
 * @Description: sql工具类
 * @Author: Mr. Dai
 * @Date: 2023/3/26 14:04
 **/
public class SqlUtils {

    static final Logger log = LoggerFactory.getLogger(SqlUtils.class);


    /**
     * @Author: Mr. Dai
     * @Description:
     * @Date: 19:06 2023/3/26
     * @param list 批量操作的集合数据
     * @param mapper 操作的mapper
     * @param function 要做的操作
     **/
    public  static <T,M> void execute(List<T> list, Class<M> mapper, BatchExecuteFace<T,M> function){
        SqlSession sqlSession =null;
        try {
            SqlSessionFactory sqlSessionFactory = SpringUtils.getBean(SqlSessionFactory.class);
            sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH);
            M m = sqlSession.getMapper(mapper);

            for (T t : list) {
                function.execute(t,m);
            }

            sqlSession.commit(!TransactionSynchronizationManager.isSynchronizationActive());
            sqlSession.clearCache();

        }catch (Exception e){
            if (!ObjectUtils.isEmpty(sqlSession)) {
                sqlSession.rollback();
            }
            log.error(String.format("批量执行sql失败 mapper:%s des: %s",mapper.getName(),e.getMessage()));
        }finally {
            if (!ObjectUtils.isEmpty(sqlSession)) {
                sqlSession.close();
            }
        }
    }


    @FunctionalInterface
    public interface BatchExecuteFace<T,M>{
        public void execute(T r,M t);
    }
}
