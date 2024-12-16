package pe.edu.cibertec.DAWI_Fournier_Soto_FabrizioEduardo.response;

import pe.edu.cibertec.DAWI_Fournier_Soto_FabrizioEduardo.dto.CarDetailDto;

public record FindCarResponse(String code,
                              String error,
                              CarDetailDto carDetailDto) {
}
