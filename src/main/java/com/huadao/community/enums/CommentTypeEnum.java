package com.huadao.community.enums;

/**
 * @author liyang
 * @date 2020/4/28
 */
public enum CommentTypeEnum {
    /**
     * 问题
     */
    QUESTION(1),
    /**
     * 评论
     */
    COMMENT(2);

    private Integer type;

    CommentTypeEnum(Integer type){
        this.type = type;
    }

    public Integer  getType(){
        return type;
    }

    public static boolean isExist(Integer type) {
        for (CommentTypeEnum commentTypeEnum : CommentTypeEnum.values()) {
            if(commentTypeEnum.getType().equals(type)){
                return true;
            }
        }
        return false;
    }
}
