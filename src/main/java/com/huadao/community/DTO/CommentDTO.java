package com.huadao.community.DTO;

import lombok.Data;

/**
 * @author liyang
 * @date 2020/4/28
 */
@Data
public class CommentDTO {
    private Long parentId;
    private String content;
    private Integer type;
}
