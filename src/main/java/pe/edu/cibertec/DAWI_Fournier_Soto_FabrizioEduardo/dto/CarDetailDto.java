package pe.edu.cibertec.DAWI_Fournier_Soto_FabrizioEduardo.dto;

import java.util.Date;

public record CarDetailDto(Integer id,
                           String make,
                           String model,
                           Integer year,
                           String vin,
                           String license,
                           String ownerName,
                           String ownerContact,
                           Date purchaseDate,
                           Integer mileage,
                           String engine,
                           String color,
                           String insuranceName,
                           String insuranceNumber,
                           Date registrationExpDate,
                           Date serviceDueDate) {
}
