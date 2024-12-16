package pe.edu.cibertec.DAWI_Fournier_Soto_FabrizioEduardo.response;

import pe.edu.cibertec.DAWI_Fournier_Soto_FabrizioEduardo.dto.CarDto;

import java.util.List;

public record FindCarsResponse(String code,
                               String error,
                               List<CarDto> cars) {
}
