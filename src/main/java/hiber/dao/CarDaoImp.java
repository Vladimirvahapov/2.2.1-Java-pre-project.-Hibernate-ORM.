package hiber.dao;

import hiber.model.Car;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class CarDaoImp implements CarDao {

    private SessionFactory sessionFactory;

    public CarDaoImp(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional
    public void add(Car car) {
        sessionFactory.getCurrentSession().save(car);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Car> listCar() {
        TypedQuery<Car> query = sessionFactory.getCurrentSession().createQuery("from Car");
        return query.getResultList();
    }
}
