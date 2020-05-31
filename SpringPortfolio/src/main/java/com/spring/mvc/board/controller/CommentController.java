package com.spring.mvc.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.mvc.board.model.CommentVO;
import com.spring.mvc.board.service.CommentService;
 
@Controller
@RequestMapping("/comment")
public class CommentController {
 
    @Autowired
    CommentService mCommentService;
    
    
    @GetMapping("/list")//댓글 리스트
    @ResponseBody
    private List<CommentVO> mCommentServiceList(Model model, int bno) throws Exception{
        
        return mCommentService.commentListService(bno);
    }
    
    @RequestMapping("/insert") //댓글 작성 //묶여있다는 것을 표현하기위해서 @RequestMapping("/insert")를 하였다.
    @ResponseBody
    private int mCommentServiceInsert(@RequestParam int bno, @RequestParam String content) throws Exception{
        
        CommentVO comment = new CommentVO();
        comment.setBno(bno);
        comment.setContent(content);
       
        comment.setWriter("test");  
        
        return mCommentService.commentInsertService(comment);
    }
    
    @RequestMapping("/update") //댓글 수정  
    @ResponseBody
    private int mCommentServiceUpdateProc(@RequestParam int cno, @RequestParam String content) throws Exception{
        
        CommentVO comment = new CommentVO();
        comment.setCno(cno);
        comment.setContent(content);
        
        return mCommentService.commentUpdateService(comment);
    }
    
    @RequestMapping("/delete/{cno}") //댓글 삭제  
    @ResponseBody
    private int mCommentServiceDelete(@PathVariable int cno) throws Exception{
        
        return mCommentService.commentDeleteService(cno);
    }
    
}


