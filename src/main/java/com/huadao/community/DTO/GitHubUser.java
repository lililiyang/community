package com.huadao.community.DTO;

import lombok.Data;

/**
 * @author liyang
 * @version 1.0
 * @date 2019/11/23 20:15
 */
@Data
public class GitHubUser {
    private String name;
    private Long id;
    private String bio;
    private String login;
    private String avatar_url;
}
