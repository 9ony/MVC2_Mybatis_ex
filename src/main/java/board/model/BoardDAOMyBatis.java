package board.model;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class BoardDAOMyBatis {
	//세션팩토리에서 세션을 얻어오는 방법 -----------
	//xml에 네임스페이스 등록 필수!!! NS 저장
	private final String NS="board.model.BoardMapper";
	private String resource="common/config/mybatis-config.xml";//설계도같은 느낌
	private SqlSession ses;
	
	public SqlSessionFactory getSessionFactory() {
		try {
		InputStream is = Resources.getResourceAsStream(resource);
		SqlSessionFactory factory=new SqlSessionFactoryBuilder().build(is);
		return factory;
		}catch(IOException e) {
			e.printStackTrace();
			return null;
		}
	}//-------------세션팩토리에서 세션을 얻어오는 방법끝 -----------
	
	public int getTotalCount() {
		ses = this.getSessionFactory().openSession(); //sql세션을 세션 팩토리에서 얻어온다
		int count=ses.selectOne(NS+".totalCount");
		if(ses!=null) ses.close();
		return count;
	}
	
	public int insertBoard(BoardVO vo) {
		ses=this.getSessionFactory().openSession();
		int n = ses.insert(NS+".insertBoard",vo);
		if(n>0) {
			ses.commit();
		}else {
			ses.rollback();
		}
		return n;
	}
}
