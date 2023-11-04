package lk.ijse.carRentalSystem.repo;

import lk.ijse.carRentalSystem.entity.Maintenance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MaintenanceRepo extends JpaRepository<Maintenance,String> {
    @Modifying
    @Query(value = "UPDATE maintenance SET runKm=0 WHERE mId=:mId",nativeQuery = true)
    void updateMaintenance(@Param("mId") long mId);
}
