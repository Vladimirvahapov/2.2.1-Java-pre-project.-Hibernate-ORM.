package hiber.service;

import hiber.dao.CarDao;
import hiber.model.Car;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarServiceImp implements CarService {

    private CarDao carDao;

    public CarServiceImp(CarDao carDao) {
        this.carDao = carDao;
    }

    @Override
    public void add(Car car) {
        carDao.add(car);
    }


    @Override
    public List<Car> listCar() {
        return carDao.listCar();
    }
}
