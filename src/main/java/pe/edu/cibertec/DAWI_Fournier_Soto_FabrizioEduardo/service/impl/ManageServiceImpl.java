package pe.edu.cibertec.DAWI_Fournier_Soto_FabrizioEduardo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.cibertec.DAWI_Fournier_Soto_FabrizioEduardo.dto.CarDetailDto;
import pe.edu.cibertec.DAWI_Fournier_Soto_FabrizioEduardo.dto.CarDto;
import pe.edu.cibertec.DAWI_Fournier_Soto_FabrizioEduardo.entity.Car;
import pe.edu.cibertec.DAWI_Fournier_Soto_FabrizioEduardo.repository.CarRepository;
import pe.edu.cibertec.DAWI_Fournier_Soto_FabrizioEduardo.service.ManageService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ManageServiceImpl implements ManageService {

    @Autowired
    CarRepository carRepository;

    @Override
    public List<CarDto> getAllCars() throws Exception {

        List<CarDto> cars = new ArrayList<CarDto>();
        Iterable<Car> iterable = carRepository.findAll();
        iterable.forEach(car -> {
            cars.add(new CarDto(car.getCarId(),
                    car.getMake(),
                    car.getModel(),
                    car.getYear(),
                    car.getMileage(),
                    car.getEngineType(),
                    car.getColor()
            ));
        });
        return cars;
    }

    @Override
    public Optional<CarDetailDto> getCarById(int id) throws Exception {

        Optional<Car> optional = carRepository.findById(id);
        return optional.map(car -> new CarDetailDto(car.getCarId(),
                car.getMake(),
                car.getModel(),
                car.getYear(),
                car.getVin(),
                car.getLicensePlate(),
                car.getOwnerName(),
                car.getOwnerContact(),
                car.getPurchaseDate(),
                car.getMileage(),
                car.getEngineType(),
                car.getColor(),
                car.getInsuranceCompany(),
                car.getInsurancePolicyNumber(),
                car.getRegistrationExpirationDate(),
                car.getServiceDueDate()
        ));
    }

    @Override
    public boolean updateCar(CarDto carDto) throws Exception {

        Optional<Car> optional = carRepository.findById(carDto.id());
        return optional.map(car -> {
           car.setMake(carDto.make());
           car.setModel(carDto.model());
           car.setYear(carDto.year());
           car.setMileage(carDto.mileage());
           car.setEngineType(carDto.engine());
           car.setColor(carDto.color());
           carRepository.save(car);
           return true;
        }).orElse(false);
    }

    @Override
    public boolean deleteCarById(int id) throws Exception {
        Optional<Car> optional = carRepository.findById(id);
        return optional.map(car -> {
            carRepository.delete(car);
            return true;
        }).orElse(false);
    }

    @Override
    public boolean addCar(CarDetailDto carDetailDto) throws Exception {
        Optional<Car> optional = carRepository.findById(carDetailDto.id());
        if (optional.isPresent())
            return false;
        else {
            Car car = new Car();
            car.setMake(carDetailDto.make());
            car.setModel(carDetailDto.model());
            car.setYear(carDetailDto.year());
            car.setVin(carDetailDto.vin());
            car.setLicensePlate(carDetailDto.license());
            car.setOwnerName(carDetailDto.ownerName());
            car.setOwnerContact(carDetailDto.ownerContact());
            car.setPurchaseDate(new Date());
            car.setMileage(carDetailDto.mileage());
            car.setEngineType(carDetailDto.engine());
            car.setColor(carDetailDto.color());
            car.setInsuranceCompany(carDetailDto.insuranceName());
            car.setInsurancePolicyNumber(carDetailDto.insuranceNumber());
            car.setRegistrationExpirationDate(new Date());
            car.setServiceDueDate(new Date());
            carRepository.save(car);
            return true;
        }
    }
}
