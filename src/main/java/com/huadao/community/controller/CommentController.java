package com.huadao.community.controller;

import com.huadao.community.DTO.CommentDTO;
import com.huadao.community.exception.CustomizeException;
import com.huadao.community.exception.QuestionErrorCode;
import com.huadao.community.model.Comment;
import com.huadao.community.model.User;
import com.huadao.community.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

/**
 * @author liyang
 * @date 2020/4/28
 */
@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;

    @ResponseBody
    @RequestMapping(value = "/comment", method = RequestMethod.POST)
    public Object post(@RequestBody CommentDTO commentDTO,
                       HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if(user == null){
            throw new CustomizeException(QuestionErrorCode.USER_NOT_LOGIN);
        }


        Comment comment = new Comment();
        comment.setCommentator(user.getId());
        comment.setCommentContent(commentDTO.getContent());
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setGmtModified(System.currentTimeMillis());
        comment.setType(commentDTO.getType());
        comment.setParemtId(commentDTO.getParentId());
        comment.setLikeCount(0L);
        commentService.insert(comment);

        HashMap<Object, Object> objectHashMap = new HashMap<>();
        objectHashMap.put("message","成功");
        objectHashMap.put("object", comment);
        return objectHashMap;
    }
}
