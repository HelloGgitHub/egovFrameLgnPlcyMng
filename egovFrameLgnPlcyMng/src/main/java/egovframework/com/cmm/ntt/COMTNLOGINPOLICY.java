package egovframework.com.cmm.ntt;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class COMTNLOGINPOLICY implements Serializable {
	/***************************************
		CREATE TABLE COMTNLOGINPOLICY
		 (
		 	POLICY_ID VARCHAR(20) NOT NULL COMMENT '정책ID',
		 	POLICY_NM VARCHAR(100) COMMENT '정책명',
		 	POLICY_DC VARCHAR(500) COMMENT '정책상세설명',
		 	POLICY_BGNDT VARCHAR(14) COMMENT '정책시작일시',
		 	POLICY_ENDDT VARCHAR(14) COMMENT '정책종료일시',
		 	POLICY_APPY_YN VARCHAR(1) COMMENT '정책적용여부',
		 	POLICY_REGIST_DT VARCHAR(14) COMMENT '정책등록일시',
		 	POLICY_HTMXCNT NUMERIC(5) COMMENT '로그인정책적중최대횟수',
		 	POLICY_APPY_USRID VARCHAR(20) COMMENT '정책적용자ID',
		 	POLICY_DATA VARCHAR(100) COMMENT '정책 데이타',
		 	CHANGE_DT VARCHAR(14) NOT NULL COMMENT '변경시간',
		 	PRIMARY KEY (POLICY_ID)
		 ) COMMENT '로그인정책' ;
	 ***************************************/
	
    @Id
	@Column(columnDefinition = "VARCHAR(20) NOT NULL COMMENT '정책ID'")
    private String POLICY_ID;

    @Column(columnDefinition = "VARCHAR(100) COMMENT '정책명'")
    private String POLICY_NM;

    @Column(columnDefinition = "VARCHAR(500) COMMENT '정책상세설명'")
    private String POLICY_DC;

    @Column(columnDefinition = "VARCHAR(14) COMMENT '정책시작일시'")
    private String POLICY_BGNDT;

    @Column(columnDefinition = "VARCHAR(14) COMMENT '정책종료일시'")
    private String POLICY_ENDDT;

    @Column(columnDefinition = "VARCHAR(1) COMMENT '정책적용여부'")
    private String POLICY_APPY_YN;

    @Column(columnDefinition = "VARCHAR(14) COMMENT '정책등록일시'")
    private String POLICY_REGIST_DT;

    @Column(columnDefinition = "NUMERIC(5) COMMENT '로그인정책적중최대횟수'")
    private String POLICY_HTMXCNT;

    @Column(columnDefinition = "VARCHAR(20) COMMENT '정책적용자ID'")
    private String POLICY_APPY_USRID;

    @Column(columnDefinition = "VARCHAR(100) COMMENT '정책 데이타'")
    private String POLICY_DATA;

    @Column(columnDefinition = "VARCHAR(14) NOT NULL COMMENT '변경시간'")
    private String CHANGE_DT;

}