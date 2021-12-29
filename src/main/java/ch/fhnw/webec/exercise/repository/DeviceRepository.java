package ch.fhnw.webec.exercise.repository;

import ch.fhnw.webec.exercise.model.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DeviceRepository extends JpaRepository<Device, Integer> {
//    @Query("""
//        Select device
//            FROM Device device
//        WHERE LOWER(device.model) LIKE LOWER(CONCAT('%', :search, '%'))
//            OR LOWER(device.manufacturer) LIKE LOWER(CONCAT('%', :search, '%'))
//            OR LOWER(device.serialNumber) LIKE LOWER(CONCAT('%', :search, '%'))
//            OR LOWER(device.displaySize) LIKE LOWER(CONCAT('%', :search, '%'))
//            OR LOWER(device.processor) LIKE LOWER(CONCAT('%', :search, '%'))
//            OR LOWER(device.memory) LIKE LOWER(CONCAT('%', :search, '%'))
//            OR LOWER(device.purchaseDate) LIKE LOWER(CONCAT('%', :search, '%'))
//    """)
@Query("""
        Select DISTINCT device FROM Device device
        INNER JOIN device.location location
        INNER JOIN device.status status
        WHERE LOWER(device.model) LIKE LOWER(CONCAT('%', :search, '%'))
            OR LOWER(device.manufacturer) LIKE LOWER(CONCAT('%', :search, '%'))
            OR LOWER(device.serialNumber) LIKE LOWER(CONCAT('%', :search, '%'))
            OR LOWER(device.displaySize) LIKE LOWER(CONCAT('%', :search, '%'))
            OR LOWER(device.processor) LIKE LOWER(CONCAT('%', :search, '%'))
            OR LOWER(device.memory) LIKE LOWER(CONCAT('%', :search, '%'))
            OR LOWER(device.purchaseDate) LIKE LOWER(CONCAT('%', :search, '%'))
    """)


    List<Device> findBySearch(@Param("search") String search);

}
