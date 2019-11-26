package com.huadao.community.DTO;

/**
 * @author liyang
 * @version 1.0
 * @date 2019/11/23 20:15
 */
public class GitHubUser {
    private String name;
    private Long id;
    private String bio;
    private String login;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
