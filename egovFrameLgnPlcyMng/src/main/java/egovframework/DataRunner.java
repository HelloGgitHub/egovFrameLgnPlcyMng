package egovframework;

import java.sql.Connection;
import java.sql.Statement;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import egovframework.com.cmm.ComUtil;

@Component
public class DataRunner implements ApplicationRunner {

    @Autowired
    DataSource dataSource;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        try(Connection connection = dataSource.getConnection()){
			String sstime= ComUtil.getTime("yyyyMMddHHmmss");
//			String URL = connection.getMetaData().getURL();
//			String User = connection.getMetaData().getUserName();

            Statement statement = connection.createStatement();
            StringBuffer comtnUserMng = new StringBuffer();
            comtnUserMng.append("\n CREATE TABLE COMTNUSERMNG                       ");
            comtnUserMng.append("\n (                                               ");
            comtnUserMng.append("\n 	USR_ID               		VARCHAR(20) NOT NULL, ");
            comtnUserMng.append("\n 	PASSWORD              VARCHAR(200) NOT NULL,  ");
            comtnUserMng.append("\n 	PASSWORD_HINT      VARCHAR(100) ,             ");
            comtnUserMng.append("\n 	PASSWORD_CNSR     VARCHAR(100) ,              ");
            comtnUserMng.append("\n 	USR_NM               	VARCHAR(50) ,           ");
            comtnUserMng.append("\n 	ZIP                   		VARCHAR(6) ,        ");
            comtnUserMng.append("\n 	ADRES                 	VARCHAR(100) ,        ");
            comtnUserMng.append("\n 	AREA_NO               	VARCHAR(4) ,          ");
            comtnUserMng.append("\n 	USR_STTUS           	VARCHAR(15) ,           ");
            comtnUserMng.append("\n 	DETAIL_ADRES         VARCHAR(100) ,           ");
            comtnUserMng.append("\n 	END_TELNO             	VARCHAR(4)  ,         ");
            comtnUserMng.append("\n 	MBTLNUM               	VARCHAR(20)  ,        ");
            comtnUserMng.append("\n 	USR_FXNUM           	VARCHAR(20) ,           ");
            comtnUserMng.append("\n 	USR_EMAIL_ADRES  	VARCHAR(50) ,               ");
            comtnUserMng.append("\n 	MIDDLE_TELNO         VARCHAR(4) ,             ");
            comtnUserMng.append("\n 	SBSCRB_DE             	DATETIME ,            ");
            comtnUserMng.append("\n 	SEXDSTN_CODE      	VARCHAR(1) ,                 ");
            comtnUserMng.append("\n 	ESNTL_ID              	VARCHAR(20) ,            ");
            comtnUserMng.append("\n 	LOCK_AT               	VARCHAR(1) ,             ");
            comtnUserMng.append("\n 	LOCK_CNT              	NUMERIC(3) ,          ");
            comtnUserMng.append("\n 	LOCK_LAST_PNTTM   DATETIME ,                  ");
            comtnUserMng.append("\n 	CHANGE_DT					VARCHAR(14) ,               ");
            comtnUserMng.append("\n 	 PRIMARY KEY (USR_ID)                         ");
            comtnUserMng.append("\n )                                               ");
            statement.executeUpdate(comtnUserMng.toString());
    		String comtnUserMngQry = "INSERT INTO COMTNUSERMNG ( USR_ID , PASSWORD , PASSWORD_HINT , PASSWORD_CNSR , USR_NM , ZIP , ADRES , AREA_NO , USR_STTUS , DETAIL_ADRES , END_TELNO , MBTLNUM , USR_FXNUM , USR_EMAIL_ADRES , MIDDLE_TELNO , SBSCRB_DE , SEXDSTN_CODE , ESNTL_ID , LOCK_AT , LOCK_CNT , CHANGE_DT )";  
    		comtnUserMngQry = comtnUserMngQry +" VALUES ( 'TESTUSER' , '473287f8298dba7163a897908958f7c0eae733e25d2e027992ea2edc9bed2fa8' , 'P02' , 'P0002' , '홍길동' , '23121' , 'adres' , '112' , 'A' , '111-335' , '6432' , '010-0000-3333' , '02-332-3332' , 'test@egov.go.kr' , '2332' , null , 'M' , null , 'N' , 0 , '"+sstime+"' ) ";
			jdbcTemplate.execute("INSERT INTO COMTNUSERMNG (USR_ID,PASSWORD,PASSWORD_HINT,PASSWORD_CNSR,USR_NM,ZIP,ADRES,AREA_NO,USR_STTUS,DETAIL_ADRES,END_TELNO,MBTLNUM,USR_FXNUM,USR_EMAIL_ADRES,MIDDLE_TELNO,SBSCRB_DE,SEXDSTN_CODE,ESNTL_ID, CHANGE_DT) values ('USER','473287f8298dba7163a897908958f7c0eae733e25d2e027992ea2edc9bed2fa8','P01','전자정부표준프레임워크센터','일반회원','100775','서울 중구 무교동 한국정보화진흥원','02','P','전자정부표준프레임워크센터','2059','1566-2059','1566-2059','egovframesupport@gmail.com','1566',SYSDATE(),'F','USRCNFRM_00000000001', '"+sstime+"');");
			jdbcTemplate.execute(comtnUserMngQry);
			
			
        	/*이하 로그인 정책 관리
        	 */
        	StringBuffer comtnLoginPolicy = new StringBuffer();
        	comtnLoginPolicy.append("\n CREATE TABLE COMTNLOGINPOLICY        ");
        	comtnLoginPolicy.append("\n (                                    ");
        	comtnLoginPolicy.append("\n 	POLICY_ID VARCHAR(20) NOT NULL,    ");
        	comtnLoginPolicy.append("\n 	POLICY_NM VARCHAR(100),            ");
        	comtnLoginPolicy.append("\n 	POLICY_DC VARCHAR(500),            ");
        	comtnLoginPolicy.append("\n 	POLICY_BGNDT VARCHAR(14),             ");
        	comtnLoginPolicy.append("\n 	POLICY_ENDDT VARCHAR(14),             ");
        	comtnLoginPolicy.append("\n 	POLICY_APPY_YN VARCHAR(1),         ");
        	comtnLoginPolicy.append("\n 	POLICY_REGIST_DT VARCHAR(14),         ");
        	comtnLoginPolicy.append("\n 	POLICY_HTMXCNT NUMERIC(5),         ");
        	comtnLoginPolicy.append("\n 	POLICY_APPY_USRID VARCHAR(20),     ");
        	comtnLoginPolicy.append("\n 	CHANGE_DT VARCHAR(14) NOT NULL,    ");
        	comtnLoginPolicy.append("\n 	PRIMARY KEY (POLICY_ID)            ");
        	comtnLoginPolicy.append("\n )                                    ");
        	statement.executeUpdate(comtnLoginPolicy.toString());
        	String comtnLoginPolicyQry1 = "INSERT INTO COMTNLOGINPOLICY (POLICY_ID,POLICY_NM,POLICY_DC,POLICY_BGNDT,POLICY_ENDDT,POLICY_APPY_YN,POLICY_REGIST_DT,POLICY_HTMXCNT,POLICY_APPY_USRID,CHANGE_DT) VALUES ('POLCY001','ID/PW 오류 체크','로그인시 ID/PW 오류 횟수를 체크하여 로그인을 제한한다.','20200707','99991231','Y','"+sstime+"',3,'TESTUSER',"+sstime+")";
        	String comtnLoginPolicyQry2 = "INSERT INTO COMTNLOGINPOLICY (POLICY_ID,POLICY_NM,POLICY_DC,POLICY_BGNDT,POLICY_ENDDT,POLICY_APPY_YN,POLICY_REGIST_DT,POLICY_HTMXCNT,POLICY_APPY_USRID,CHANGE_DT) VALUES ('POLCY002','IP차단','로그인 차단 대상 IP를 등록하고 체크하여 로그인을 제한한다.','20200707','99991231','Y','"+sstime+"',0,'TESTUSER',"+sstime+")";
        	jdbcTemplate.execute(comtnLoginPolicyQry1);
        	jdbcTemplate.execute(comtnLoginPolicyQry2);

        	StringBuffer comtnLoginPolicyHitHistory = new StringBuffer();
        	comtnLoginPolicyHitHistory.append("\n CREATE TABLE COMTNLOGINPOLICYHITHISTORY            ");
        	comtnLoginPolicyHitHistory.append("\n (                                                  ");
        	comtnLoginPolicyHitHistory.append("\n 	USR_ID VARCHAR(20) NOT NULL,                     ");
        	comtnLoginPolicyHitHistory.append("\n 	POLICY_ID VARCHAR(20) NOT NULL,                  ");
        	comtnLoginPolicyHitHistory.append("\n 	POLICY_HIT_DT VARCHAR(20) NOT NULL,                 ");
        	comtnLoginPolicyHitHistory.append("\n 	LOGIN_IP VARCHAR(23),                            ");
        	comtnLoginPolicyHitHistory.append("\n 	PRIMARY KEY (USR_ID, POLICY_ID, POLICY_HIT_DT)   ");
        	comtnLoginPolicyHitHistory.append("\n )                                                  ");
        	statement.executeUpdate(comtnLoginPolicyHitHistory.toString());
        	String comtnLoginPolicyHitHistoryQry1 = "INSERT INTO COMTNLOGINPOLICYHITHISTORY (USR_ID, POLICY_ID, POLICY_HIT_DT, LOGIN_IP) VALUES ('TESTUSER','POLCY002','20200707143514987','10.20.30.114')";
        	String comtnLoginPolicyHitHistoryQry2 = "INSERT INTO COMTNLOGINPOLICYHITHISTORY (USR_ID, POLICY_ID, POLICY_HIT_DT, LOGIN_IP) VALUES ('TESTUSER','POLCY002','20200702143514987','10.20.30.114')";
        	String comtnLoginPolicyHitHistoryQry3 = "INSERT INTO COMTNLOGINPOLICYHITHISTORY (USR_ID, POLICY_ID, POLICY_HIT_DT, LOGIN_IP) VALUES ('TESTUSER','POLCY002','20200617143514987','10.20.30.114')";
        	String comtnLoginPolicyHitHistoryQry4 = "INSERT INTO COMTNLOGINPOLICYHITHISTORY (USR_ID, POLICY_ID, POLICY_HIT_DT, LOGIN_IP) VALUES ('TESTUSER','POLCY001','20200625093514987','10.20.30.114')";
        	String comtnLoginPolicyHitHistoryQry5 = "INSERT INTO COMTNLOGINPOLICYHITHISTORY (USR_ID, POLICY_ID, POLICY_HIT_DT, LOGIN_IP) VALUES ('USER','POLCY001','20200505093514987','10.20.30.114')";
        	jdbcTemplate.execute(comtnLoginPolicyHitHistoryQry1);
        	jdbcTemplate.execute(comtnLoginPolicyHitHistoryQry2);
        	jdbcTemplate.execute(comtnLoginPolicyHitHistoryQry3);
        	jdbcTemplate.execute(comtnLoginPolicyHitHistoryQry4);
        	jdbcTemplate.execute(comtnLoginPolicyHitHistoryQry5);

        	StringBuffer COMTNBLKIPMNGR = new StringBuffer();
        	COMTNBLKIPMNGR.append("\n CREATE TABLE COMTNBLKIPMNGR      ");
        	COMTNBLKIPMNGR.append("\n (                              ");
        	COMTNBLKIPMNGR.append("\n 	BLK_IP VARCHAR(23) NOT NULL,  ");
        	COMTNBLKIPMNGR.append("\n 	BLK_IPNM VARCHAR(100) ,  ");
        	COMTNBLKIPMNGR.append("\n 	BLK_IPDC VARCHAR(500) ,  ");
        	COMTNBLKIPMNGR.append("\n 	ADD_USRID VARCHAR(20) NOT NULL,  ");
        	COMTNBLKIPMNGR.append("\n 	ADD_DT VARCHAR(14) NOT NULL,  ");
        	COMTNBLKIPMNGR.append("\n 	PRIMARY KEY (BLK_IP)          ");
        	COMTNBLKIPMNGR.append("\n )                              ");
        	statement.executeUpdate(COMTNBLKIPMNGR.toString());
        	String comBlkipMngrQry1 = "INSERT INTO COMTNBLKIPMNGR(BLK_IP, BLK_IPNM, BLK_IPDC, ADD_USRID, ADD_DT) VALUES ('10.20.30.40','테스트IP','테스트를 위해 등록된 IP','TESTUSER','"+sstime+"')";
        	String comBlkipMngrQry2 = "INSERT INTO COMTNBLKIPMNGR(BLK_IP, BLK_IPNM, BLK_IPDC, ADD_USRID, ADD_DT) VALUES ('202.10.210.50','테스트IP2','테스트를 위해 등록된 IP2','TESTUSER','"+sstime+"')";
        	jdbcTemplate.execute(comBlkipMngrQry1);
        	jdbcTemplate.execute(comBlkipMngrQry2);
        }
    }
}
