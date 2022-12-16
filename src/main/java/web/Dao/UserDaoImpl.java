package web.Dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import web.Model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> getAllUsers() {
        List<User> users = entityManager.createQuery("select us from User us")
                .getResultList();
        return users;
    }

    @Override
    public User getUser(int id) {

        return entityManager.find(User.class,id);
    }

    @Override
    public void saveUser(User user) {

        entityManager.persist(user);
    }

    @Override
    public void updateUser(int id, User updatedUser) {
        User user = entityManager.find(User.class,id);
        user.setName(updatedUser.getName());
        user.setSurname(updatedUser.getSurname());
    }

    @Override
    public void deleteUser(int id) {

        entityManager.createQuery("DELETE FROM User u WHERE u.id =:id", User.class)
                .setParameter("id", id).executeUpdate();
    }
}
