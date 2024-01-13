package hiber.dao;

import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {


    private final SessionFactory sessionFactory;

    @Autowired
    public UserDaoImp(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void add(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    public List<User> listUsers() {
        String hql = "SELECT u FROM User u";
        List<User> result = sessionFactory.getCurrentSession().createQuery(hql, User.class).list();
        return result;
    }

    @Override
    public User findUserByCar(String model, int series) {
        String hql = "SELECT u FROM User u WHERE u.car.model = :model AND u.car.series = :series";
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery(hql, User.class)
                .setParameter("model", model).setParameter("series", series);
        return query.getSingleResult();
    }
}
