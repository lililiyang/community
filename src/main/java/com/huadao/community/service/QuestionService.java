package com.huadao.community.service;

import com.huadao.community.DTO.PaginationDTO;
import com.huadao.community.DTO.QuestionDTO;
import com.huadao.community.mapper.QuestionMapper;
import com.huadao.community.mapper.UserMapper;
import com.huadao.community.model.Question;
import com.huadao.community.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liyang
 * @date 2019/12/4
 */
@Service
public class QuestionService {

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private UserMapper userMapper;

    public PaginationDTO questionList(Integer page, Integer size) {
        Integer totalPage;
        Integer totalCount = questionMapper.count();
        if (totalCount % size == 0) {
            totalPage = totalCount / size;
        } else {
            totalPage = totalCount / size + 1;
        }
        if (page < 1) {
            page = 1;
        }
        if (page > totalPage) {
            page = totalPage;
        }

        Integer offset = page < 1 ? 0 : size * (page - 1);
        List<Question> questions = questionMapper.questionList(offset, size);
        List<QuestionDTO> questionDTOList = new ArrayList<>();

        PaginationDTO paginationDTO = new PaginationDTO();

        if (questions != null) {
            for (Question question : questions) {
                QuestionDTO questionDTO = new QuestionDTO();
                BeanUtils.copyProperties(question, questionDTO);

                Integer creator = question.getCreator();
                User user = userMapper.getUserById(creator);
                questionDTO.setUser(user);
                questionDTOList.add(questionDTO);
            }
        }
        paginationDTO.setQuestions(questionDTOList);

        paginationDTO.setPagination(totalPage,page);

        return paginationDTO;
    }
}
