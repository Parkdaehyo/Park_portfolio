package com.spring.mvc.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.mvc.board.model.CommentVO;
import com.spring.mvc.board.repository.CommentMapper;
 
@Service
public class CommentService {
 
    @Autowired
    CommentMapper mCommentMapper;
    
    public List<CommentVO> commentListService(int bno) throws Exception{
        
        return mCommentMapper.commentList(bno);
    }
    
    public int commentInsertService(CommentVO comment) throws Exception{
        
        return mCommentMapper.commentInsert(comment);
    }
    
    public int commentUpdateService(CommentVO comment) throws Exception{
        
        return mCommentMapper.commentUpdate(comment);
    }
    
    public int commentDeleteService(int cno) throws Exception{
        
        return mCommentMapper.commentDelete(cno);
    }
}

