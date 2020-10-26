package ImageHoster.repository;

import ImageHoster.model.Comment;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.List;

//The annotation is a special type of @Component annotation which describes that the class defines a data repository
@Repository
public class CommentRepository {
    //Get an instance of EntityManagerFactory from persistence unit with name as 'imageHoster'
    @PersistenceUnit(unitName = "imageHoster")
    private EntityManagerFactory emf;


    //The method adds the Comment object to be persisted in the database
    //Creates an instance of EntityManager
    //Starts a transaction
    //The transaction is committed if it is successful
    //The transaction is rolled back in case of unsuccessful transaction
    public Comment uploadComment(Comment newComment) {

        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();
            em.persist(newComment);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }
        return newComment;
    }

    //The method creates an instance of EntityManager
    //Executes JPQL query to fetch the image from the database with corresponding id
    //Returns the Comment in case the Comment is found in the database
    //Returns null if no Comment is found in the database
    public List<Comment> getCommentsByImageId(Integer imageId) {
        EntityManager em = emf.createEntityManager();
        try {

            TypedQuery<Comment> typedQuery = em.createQuery("SELECT c from Comment c JOIN c.image i where i.id =:imageId", Comment.class).setParameter("imageId", imageId);
//            TypedQuery<Comment> typedQuery = em.createQuery("SELECT c from Comment c where c.image =:imageId", Comment.class);
            List<Comment> comments = typedQuery.getResultList();
            return comments;
        } catch (NoResultException nre) {
            return null;
        }
    }

}
