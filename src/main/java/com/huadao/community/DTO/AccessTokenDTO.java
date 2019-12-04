package com.huadao.community.DTO;

import lombok.Data;

/**
 * @author liyang
 * @version 1.0
 * @date 2019/11/23 19:50
 */
@Data
public class AccessTokenDTO {
    private String client_id;
    private String client_secret;
    private String code;
    private String state;
    private String redirect_url;


}
