package board.model;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	
	public int getTotalCount(String type, String keyword) {
		
		Map<String, String> map=new HashMap<>();
		map.put("findType", type);
		map.put("findKeyword", keyword);
		
		ses = this.getSessionFactory().openSession(); //sql세션을 세션 팩토리에서 얻어온다
		int count=ses.selectOne(NS+".totalCount" , map);
		if(ses!=null) ses.close();
		return count;
	}
	
	public int insertBoard(BoardVO vo) {
		ses=this.getSessionFactory().openSession();
		System.out.println("insert: vo의 num(Mapper에 selectkey적용 전)  : "+vo.getNum());
		int n = ses.insert(NS+".insertBoard",vo);
		System.out.println("insert: vo의 num(Mapper에 selectkey적용 후)  : "+vo.getNum());
		if(n>0) {
			ses.commit();
		}else {
			ses.rollback();
		}
		return n;
	}

	public List<BoardVO> listBoard(int start,int end, String type, String keyword) {
		// TODO Auto-generated method stub
		ses=this.getSessionFactory().openSession();
		Map<String,String> map = new HashMap<>();
		map.put("start",String.valueOf(start));
		map.put("end",end+"");
		map.put("findType", type);
		map.put("findKeyword", keyword);
		List<BoardVO> arr=ses.selectList(NS+".listBoard", map);
		if(ses!=null) ses.close();
		return arr;
	}
	//단일행 selectOne
	//다중행 selectList

	public BoardVO viewBoard(int num) {
		try {
			ses=this.getSessionFactory().openSession();
			BoardVO b_view =ses.selectOne(NS+".viewBoard",num);
			return b_view;
		}finally {
			close();
		}
	}
	public int deleteBoard(int num) {
		try {
			ses=this.getSessionFactory().openSession(true); //자동커밋으로바꿈
			int n = ses.delete(NS+".deleteBoard",num);
			return n;
		}finally {
			close();
		}
	}
	public int updateBoard(BoardVO vo) {
		try {
			ses=this.getSessionFactory().openSession(true);
			int n=ses.update(NS+".updateBoard", vo);
			return n;
		}finally{
			close();
		}
	}
	
	public void close() {
		if(ses!=null) ses.close();
	}



}
