package com.yyh.service;

import com.yyh.domain.Comment;

import java.util.List;

public interface ICommentService {

    List<Comment> listCommentByBlogId(Long blogId);

    Comment saveComment(Comment comment);
}
