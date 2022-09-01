package com.academy.model2app.model.repository;

import org.apache.ibatis.session.SqlSession;

import com.academy.model2app.domain.Notice;
import com.academy.model2app.mybatis.MybatisConfigManager;

/*
 * MVC 중 모델 영역인 DAO를 정의
 */
public class NoticeDAO {
	MybatisConfigManager configManager= MybatisConfigManager.getInstance();//configmanager가 sql문을 연결 한다.
	
	public int insert(Notice notice) {
		int result =0;
		SqlSession sqlSession = configManager.getSqlSession();
		result=sqlSession.insert("Notice.insert",notice);
		sqlSession.commit();//myBatis는 자동 커밋이 안됨 
		configManager.closeSqlSession(sqlSession);
		return result;
	}
}
