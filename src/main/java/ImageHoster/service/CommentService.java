package ImageHoster.service;

import ImageHoster.model.Comment;
import ImageHoster.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;

    //This method calls uploadComment method from repository to add new comment in database
    public Comment addComment(Comment newCommnet){return commentRepository.uploadComment(newCommnet);}

    //This methods retrieves Comments of specific images
    public List<Comment> getCommentsByImageId(Integer imageId){return commentRepository.getCommentsByImageId(imageId);}
}
