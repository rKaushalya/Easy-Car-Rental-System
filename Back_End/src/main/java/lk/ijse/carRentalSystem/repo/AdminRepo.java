package lk.ijse.carRentalSystem.repo;

import lk.ijse.carRentalSystem.entity.Admins;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepo extends JpaRepository<Admins,String> {
    Admins findByUserNameAndPassword(String name,String password);
}
