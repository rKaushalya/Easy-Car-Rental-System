package lk.ijse.carRentalSystem.service.impl;

import lk.ijse.carRentalSystem.dto.VehicleDTO;
import lk.ijse.carRentalSystem.dto.VehicleShowDTO;
import lk.ijse.carRentalSystem.entity.Vehicle;
import lk.ijse.carRentalSystem.entity.VehicleDetails;
import lk.ijse.carRentalSystem.repo.VehicleDetailsRepo;
import lk.ijse.carRentalSystem.repo.VehicleRepo;
import lk.ijse.carRentalSystem.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
@Transactional
public class VehicleServiceImpl implements VehicleService {
    @Autowired
    VehicleRepo vehicleRepo;

    @Autowired
    VehicleDetailsRepo vehicleDetailsRepo;

    private final String FOLDER_PATH = "C:\\Users\\ASUS\\Documents\\Car rental Images\\";

    @Override
    public void addVehicle(VehicleDTO dto, MultipartFile front, MultipartFile back, MultipartFile side, MultipartFile interior) throws IOException {
        if (vehicleRepo.existsById(dto.getRegisterNo())){
            throw new RuntimeException(dto.getRegisterNo()+" this car already available...");
        }else {

            String frontPath = FOLDER_PATH+front.getOriginalFilename();
            String backPath = FOLDER_PATH+back.getOriginalFilename();
            String sidePath = FOLDER_PATH+side.getOriginalFilename();
            String interiorPath = FOLDER_PATH+interior.getOriginalFilename();

            Vehicle vehicle = new Vehicle();
            vehicle.setRegisterNo(dto.getRegisterNo());
            vehicle.setBrand(dto.getBrand());
            vehicle.setType(dto.getType());
            vehicle.setFuelType(dto.getFuelType());
            vehicle.setTransmissionType(dto.getTransmissionType());
            vehicle.setDailyRate(dto.getDailyRate());
            vehicle.setMonthlyRate(dto.getMonthlyRate());
            vehicle.setNoOfPassenger(dto.getNoOfPassenger());
            vehicle.setFreeMileage(dto.getFreeMileage());
            vehicle.setFreePrice(dto.getFreePrice());
            vehicle.setPriceForExtraKM(dto.getPriceForExtraKM());
            vehicle.setColor(dto.getColor());
            vehicle.setState(dto.getState());


            VehicleDetails frontView = new VehicleDetails();
            frontView.setFileName(front.getOriginalFilename());
            frontView.setFilePath(frontPath);
            frontView.setFileType(front.getContentType());
            frontView.setVehicle(vehicle);

            VehicleDetails backView = new VehicleDetails();
            backView.setFileName(back.getOriginalFilename());
            backView.setFilePath(backPath);
            backView.setFileType(back.getContentType());
            backView.setVehicle(vehicle);

            VehicleDetails sideView = new VehicleDetails();
            sideView.setFileName(side.getOriginalFilename());
            sideView.setFilePath(sidePath);
            sideView.setFileType(side.getContentType());
            sideView.setVehicle(vehicle);

            VehicleDetails interiorView = new VehicleDetails();
            interiorView.setFileName(interior.getOriginalFilename());
            interiorView.setFilePath(interiorPath);
            interiorView.setFileType(interior.getContentType());
            interiorView.setVehicle(vehicle);

            vehicle.getVehicleDetails().add(frontView);
            vehicle.getVehicleDetails().add(backView);
            vehicle.getVehicleDetails().add(sideView);
            vehicle.getVehicleDetails().add(interiorView);

            vehicleRepo.save(vehicle);

            vehicleDetailsRepo.save(frontView);
            vehicleDetailsRepo.save(backView);
            vehicleDetailsRepo.save(sideView);
            vehicleDetailsRepo.save(interiorView);

            front.transferTo(new File(frontPath));
            back.transferTo(new File(backPath));
            side.transferTo(new File(sidePath));
            interior.transferTo(new File(interiorPath));
        }
    }

    @Override
    public List<VehicleShowDTO> getAllVehicle() {
        return null;
    }

    @Override
    public void deleteVehicle(String id) {

    }

    @Override
    public void updateVehicle(VehicleDTO dto, MultipartFile front, MultipartFile back, MultipartFile side, MultipartFile interior) {

    }
}
