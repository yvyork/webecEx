package ch.fhnw.webec.exercise.controller;

import ch.fhnw.webec.exercise.repository.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class InventoryController {
    @Autowired
    private DeviceRepository deviceRepository;
    @Autowired
    private ZubehoerRepository zubehoerRepository;
}
