package com.huadao.community.service;

import com.huadao.community.enums.CommentTypeEnum;
import com.huadao.community.exception.CustomizeException;
import com.huadao.community.exception.QuestionErrorCode;
import com.huadao.community.mapper.CommentMapper;
import com.huadao.community.mapper.QuestionExtMapper;
import com.huadao.community.mapper.QuestionMapper;
import com.huadao.community.model.Comment;
import com.huadao.community.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author liyang
 * @date 2020/4/28
 */
@Service
public class CommentService {
    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private QuestionExtMapper questionExtMapper;

    @Transactional
    public void insert(Comment comment) {
        if(comment.getParemtId() == null || comment.getParemtId() == 0){
            throw new CustomizeException(QuestionErrorCode.TARGET_PARAM_NOT_FOUND);
        }
        if(comment.getType() == null || !CommentTypeEnum.isExist(comment.getType())){
            throw new CustomizeException(QuestionErrorCode.TYPE_PARAM_WORNG);
        }

        if(comment.getType().equals(CommentTypeEnum.COMMENT.getType())){
            //回复评论
            commentMapper.insert(comment);
        }else{
            //回复问题
            Question question = questionMapper.selectByPrimaryKey(comment.getParemtId());
            if(question == null){
                throw new CustomizeException(QuestionErrorCode.QUESTION_NOT_FOUND);
            }
            commentMapper.insert(comment);
            //增加评论数
            question.setCommentCount(1);
            questionExtMapper.incCommentCount(question);
        }
    }
}
