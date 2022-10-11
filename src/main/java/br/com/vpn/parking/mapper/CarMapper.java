package br.com.vpn.parking.mapper;

import br.com.vpn.parking.dto.CarDTO;
import br.com.vpn.parking.dto.CarFormDTO;
import br.com.vpn.parking.model.Car;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CarMapper {

    private static final ModelMapper MODEL_MAPPER = new ModelMapper();

    public CarDTO toCarDTO(Car car) {
        return MODEL_MAPPER.map(car, CarDTO.class);
    }

    public List<CarDTO> toCarDTOList(List<Car> carList) {
        return carList.stream().map(this::toCarDTO).collect(Collectors.toList());
    }

    public Car toCar(CarDTO dto) {
        return MODEL_MAPPER.map(dto, Car.class);
    }

    public Car carFormToCar(CarFormDTO dto) {
        return MODEL_MAPPER.map(dto, Car.class);
    }
}
