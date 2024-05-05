package kz.don.recepkz.service;

import kz.don.recepkz.model.Comment;
import kz.don.recepkz.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;

    public List<Comment> listCommentsByRecipeId(Long id){
        return commentRepository.findAllByRecipeId(id);
    }

    public void addComment(Comment comment) {
        commentRepository.save(comment);
    }
}
