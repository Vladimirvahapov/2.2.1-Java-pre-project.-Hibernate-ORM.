package hiber.dao;

import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class UserDaoImp implements UserDao {

    private SessionFactory sessionFactory;

    public UserDaoImp(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional
    public void add(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsers() {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
        return query.getResultList();
    }

    @Override
    public User foundUser(String model, Integer series) {
        TypedQuery<User> userList = sessionFactory.getCurrentSession()
                .createQuery("FROM User u JOIN FETCH u.car c WHERE c.model = :model AND c.series = :series", User.class);
        userList.setParameter("model", model);
        userList.setParameter("series", series);
        List<User> resultList = userList.getResultList();
        if (resultList.isEmpty()) {
            return null;
        } else {
            return resultList.get(0);
        }
    }

}
