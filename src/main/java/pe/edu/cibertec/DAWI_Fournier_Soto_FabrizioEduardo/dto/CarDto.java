package pe.edu.cibertec.DAWI_Fournier_Soto_FabrizioEduardo.dto;


public record CarDto(Integer id,
                     String make,
                     String model,
                     Integer year,
                     Integer mileage,
                     String engine,
                     String color
                     ) {
}
