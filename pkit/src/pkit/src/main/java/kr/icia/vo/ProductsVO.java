package kr.icia.vo;

import lombok.Data;

import java.util.Date;

@Data
public class ProductsVO {
    private Long pno;

    private String p_name;

    private String p_comment;

    private String p_detail;

    private Date p_regdate;

    private Date p_updatedate;
}
