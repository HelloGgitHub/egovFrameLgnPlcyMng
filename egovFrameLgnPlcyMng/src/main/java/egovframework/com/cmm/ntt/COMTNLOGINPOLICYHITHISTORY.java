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
public class COMTNLOGINPOLICYHITHISTORY implements Serializable {
	/***************************************
		 CREATE TABLE COMTNLOGINPOLICYHITHISTORY
		 (
		 	USR_ID VARCHAR(20) NOT NULL COMMENT '사용자ID',
		 	POLICY_ID VARCHAR(20) NOT NULL COMMENT '정책ID',
		 	POLICY_HIT_DT VARCHAR(20) NOT NULL COMMENT '정책적중 일시',
		 	LOGIN_IP VARCHAR(23) COMMENT '접속IP',
		 	LOGIN_IP_CNTR VARCHAR(10) COMMENT '접속IP 국가',
		 	PRIMARY KEY (USR_ID, POLICY_ID, POLICY_HIT_DT)
		 ) COMMENT '로그인정책 적중이력' ;
	 ***************************************/
	
    @Id
	@Column(columnDefinition = "VARCHAR(20) NOT NULL COMMENT '사용자ID'")
    private String USR_ID;

    @Id
    @Column(columnDefinition = "VARCHAR(20) NOT NULL COMMENT '정책ID'")
    private String POLICY_ID;

    @Id
    @Column(columnDefinition = "VARCHAR(20) NOT NULL COMMENT '정책적중 일시'")
    private String POLICY_HIT_DT;

    @Column(columnDefinition = "VARCHAR(23) COMMENT '접속IP'")
    private String LOGIN_IP;

    @Column(columnDefinition = "VARCHAR(10) COMMENT '접속IP 국가'")
    private String LOGIN_IP_CNTR;

}