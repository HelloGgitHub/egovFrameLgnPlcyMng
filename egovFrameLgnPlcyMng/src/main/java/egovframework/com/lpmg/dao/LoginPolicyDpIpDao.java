package egovframework.com.lpmg.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface LoginPolicyDpIpDao {

	//차단대상IP 목록 조회
	List<HashMap<Object, Object>> selectDpIpList();

	//차단대상IP 기등록여부 확인
	int selectLgPlcyDpIpCnt(Map<Object, Object> param);

	//차단대상IP 등록
	int insertLgPlcyDpIp(Map<Object, Object> param);
	
	//차단대상IP 삭제
	int deleteLgPlcyDpIp(Map<Object, Object> param);
	
}
