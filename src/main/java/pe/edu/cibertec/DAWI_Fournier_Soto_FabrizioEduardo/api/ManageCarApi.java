package pe.edu.cibertec.DAWI_Fournier_Soto_FabrizioEduardo.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.edu.cibertec.DAWI_Fournier_Soto_FabrizioEduardo.dto.CarDetailDto;
import pe.edu.cibertec.DAWI_Fournier_Soto_FabrizioEduardo.dto.CarDto;
import pe.edu.cibertec.DAWI_Fournier_Soto_FabrizioEduardo.response.*;
import pe.edu.cibertec.DAWI_Fournier_Soto_FabrizioEduardo.service.ManageService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/manage-car")
public class ManageCarApi {

    @Autowired
    ManageService manageService;

    @GetMapping("/all")
    public FindCarsResponse findCars(){
        try {
            List<CarDto> cars =  manageService.getAllCars();
            if(!cars.isEmpty()){
                return new FindCarsResponse("01","Cars list obtained successfully :)",cars);
            }else {
                return new FindCarsResponse("02","Cars not founded",null);
            }

        } catch (Exception e) {
            e.printStackTrace();
            return new FindCarsResponse("99","An error occurred, please try again",null);
        }
    }

    @GetMapping("/detail")
    public FindCarResponse findCar(@RequestParam(value = "id", defaultValue = "0")String id){
        try {
           Optional<CarDetailDto> optional =  manageService.getCarById(Integer.parseInt(id));
            return optional.map(car ->
                    new FindCarResponse("01","Car founded :)", car)
            ).orElse(
                    new FindCarResponse("02","Car not founded",null)
            );

        } catch (Exception e) {
            e.printStackTrace();
            return new FindCarResponse("99","An error occurred, please try again",null);
        }
    }

    @PutMapping("/update")
    public UpdateCarResponse updateCar(@RequestBody CarDto carDto){
        try {
            if(manageService.updateCar(carDto)){
                return new UpdateCarResponse("01","Car updated successfully :)");
            }else {
                return new UpdateCarResponse("02","Car not updated");
            }

        } catch (Exception e) {
            e.printStackTrace();
            return new UpdateCarResponse("99","An error occurred, please try again");
        }
    }

    @DeleteMapping("/delete/{id}")
    public DeleteCarResponse deleteCar(@PathVariable String id){
        try {
            if(manageService.deleteCarById(Integer.parseInt(id))) {
                return new DeleteCarResponse("01","Car deleted successfully :)");
            }else {
                return new DeleteCarResponse("02","Car not founded");
            }

        } catch (Exception e) {
            e.printStackTrace();
            return new DeleteCarResponse("99","An error occurred, please try again");
        }
    }

    @PostMapping("/create")
    public CreateCarResponse createCar(@RequestBody CarDetailDto carDetailDto){
        try {
            if(manageService.addCar(carDetailDto)) {
                return new CreateCarResponse("01","Car created successfully :)");
            }else {
                return new CreateCarResponse("02","Car already exists");
            }

        } catch (Exception e) {
            e.printStackTrace();
            return new CreateCarResponse("99","An error occurred, please try again");
        }
    }
}
