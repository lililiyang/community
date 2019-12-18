package com.huadao.community.DTO;

import com.huadao.community.model.User;
import lombok.Data;

/**
 * @author liyang
 * @date 2019/12/4
 */
@Data
public class QuestionDTO {
    private Integer id;
    private String title;
    private String description;
    private Long gmtCreate;
    private Long gmtModified;
    private Integer creator;
    private Integer commentCount;
    private Integer viewCount;
    private Integer likeCount;
    private String tag;
    private User user;
}
