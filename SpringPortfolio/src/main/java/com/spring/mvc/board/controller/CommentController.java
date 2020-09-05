package com.spring.mvc.board.controller;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring.mvc.board.model.CommentVO;
import com.spring.mvc.board.service.CommentService;
import com.spring.mvc.user.model.UserVO;
import com.spring.mvc.user.service.IUserService;
 
@RestController
@RequestMapping("/comment")
public class CommentController {
 
    @Autowired
    CommentService mCommentService;
    
    @Autowired
    IUserService user_service;
    
    
    @GetMapping("/list")//댓글 리스트
    private List<CommentVO> mCommentServiceList(int bno) throws Exception{
        
        return mCommentService.commentListService(bno);
    }
    
    @RequestMapping("/insert") //댓글 작성 //묶여있다는 것을 표현하기위해서 @RequestMapping("/insert")를 하였다.
    						   //@RequestParam: 단일파라미터를 전달 받을때 사용하는 어느테이션 입니다. 자세한 설명은 iwg910 블로그에 있습니다.
    private int mCommentServiceInsert(@RequestParam int bno, @RequestParam String content) throws Exception{
   
 
    			CommentVO comment = new CommentVO();
    			comment.setBno(bno);
    			comment.setContent(content);
    			comment.setWriter("test");  
    			
    			
        return mCommentService.commentInsertService(comment);
    }
    
    @RequestMapping("/update") //댓글 수정  
    private int mCommentServiceUpdateProc(@RequestParam int cno, @RequestParam String content) throws Exception{
        
        CommentVO comment = new CommentVO();
        comment.setCno(cno); //댓글 sequence
        comment.setContent(content);
        
        return mCommentService.commentUpdateService(comment);
    }
    
    @RequestMapping("/delete/{cno}") //댓글 삭제 
    private int mCommentServiceDelete(@PathVariable int cno) throws Exception{
        
        return mCommentService.commentDeleteService(cno);
    }
    
}


