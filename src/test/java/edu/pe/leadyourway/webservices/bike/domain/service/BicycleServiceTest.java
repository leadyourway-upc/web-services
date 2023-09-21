package edu.pe.leadyourway.webservices.bike.domain.service;

import edu.pe.leadyourway.webservices.bike.domain.model.Bicycle;
import edu.pe.leadyourway.webservices.bike.domain.repository.BicycleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BicycleServiceTest {
    @Autowired
    private BicycleService bicycleService;
    @Autowired
    private BicycleRepository bicycleRepository;

    @BeforeEach
    void setUp() {
    }

    @Test
    void createBicycle() {
        Bicycle bicycle = Bicycle.builder()
                .bicycleName("Bicicleta de prueba")
                .bicycleDescription("Bicicleta de prueba")
                .bicyclePrice(100.00)
                .bicycleSize("M")
                .bicycleModel("Modelo de prueba")
                .imageData("Imagen de prueba")
                .build();
        Bicycle result = bicycleService.createBicycle(bicycle);
        assertNotNull(bicycleRepository.findById(result.getId()));
    }

    @Test
    void getBicycleById() {
        assertNotNull(bicycleService.getBicycleById(1L));
    }

    @Test
    void updateBicycle() {
        Bicycle bicycle = Bicycle.builder()
                .bicycleName("Bicicleta de prueba 2")
                .bicycleDescription("Bicicleta de prueba")
                .bicyclePrice(100.00)
                .bicycleSize("M")
                .bicycleModel("Modelo de prueba")
                .imageData("Imagen de prueba")
                .build();
        Bicycle result = bicycleService.updateBicycle(1L, bicycle);
        assertEquals("Bicicleta de prueba 2", result.getBicycleName());
    }

    @Test
    void deleteBicycle() {
        bicycleService.deleteBicycle(1L);
        assertNull(bicycleRepository.findById(1L).orElse(null));
    }

    @Test
    void getAllBicycles() {
        List<Bicycle> result = bicycleService.getAllBicycles();
        assertEquals(1, result.size());
    }
}