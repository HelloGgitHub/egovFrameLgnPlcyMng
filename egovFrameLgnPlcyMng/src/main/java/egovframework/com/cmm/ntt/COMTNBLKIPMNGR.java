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
public class COMTNBLKIPMNGR implements Serializable {
	/***************************************
		 CREATE TABLE COMTNBLKIPMNGR
		 (
		 	BLK_IP VARCHAR(23) NOT NULL COMMENT '차단IP(시작)',
		 	BLK_IP_TYP VARCHAR(2) NOT NULL COMMENT '차단IP표시방법',
		 	BLK_IP_CIDR VARCHAR(2) COMMENT '차단IPCIDR',
		 	BLK_IP_ED VARCHAR(23) COMMENT '차단IP(범위 종료)',
		 	BLK_IPNM VARCHAR(100) COMMENT '차단IP명',
		 	BLK_IPDC VARCHAR(500) COMMENT '차단IP설명',
		 	ADD_USRID VARCHAR(20) NOT NULL COMMENT '등록자ID',
		 	ADD_DT VARCHAR(14) NOT NULL COMMENT '등록일시',
		 	PRIMARY KEY (BLK_IP, BLK_IP_TYP)
		 ) COMMENT '차단IP관리' ;
	 ***************************************/
	
    @Id
	@Column(columnDefinition = "VARCHAR(23) NOT NULL COMMENT '차단IP(시작)'")
    private String BLK_IP;

    @Id
    @Column(columnDefinition = "VARCHAR(2) NOT NULL COMMENT '차단IP표시방법'")
    private String BLK_IP_TYP;

    @Column(columnDefinition = "VARCHAR(2) COMMENT '차단IPCIDR'")
    private String BLK_IP_CIDR;

    @Column(columnDefinition = "VARCHAR(23) COMMENT '차단IP(범위 종료)'")
    private String BLK_IP_ED;

    @Column(columnDefinition = "VARCHAR(100) COMMENT '차단IP명'")
    private String BLK_IPNM;

    @Column(columnDefinition = "VARCHAR(500) COMMENT '차단IP설명'")
    private String BLK_IPDC;

    @Column(columnDefinition = "VARCHAR(20) NOT NULL COMMENT '등록자ID'")
    private String ADD_USRID;

    @Column(columnDefinition = "VARCHAR(14) NOT NULL COMMENT '등록일시'")
    private String ADD_DT;

}