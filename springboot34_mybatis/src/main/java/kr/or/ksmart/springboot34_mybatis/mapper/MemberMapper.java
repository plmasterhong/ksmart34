package kr.or.ksmart.springboot34_mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ksmart.springboot34_mybatis.domain.Member;

@Mapper
public interface MemberMapper {
	

	public List<Member> getMemberList(); 

	public List<Member> getSearchMemberList(String sk, String sv);
	
	public int mInsert(Member member);
	
	public Member selectForUpdate(String mId);
	
	public int mUpdate(Member member);

	public int mDelete(String mId, String mPw);
	
}
