package com.apple.shop.domain.comment.repo;

import com.apple.shop.domain.comment.Entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepo extends JpaRepository<Comment,Long> {
        public List<Comment> findAllByParentId(Long a);
    
}
