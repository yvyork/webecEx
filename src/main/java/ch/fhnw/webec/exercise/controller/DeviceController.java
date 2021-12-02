package ch.fhnw.webec.exercise.controller;

import ch.fhnw.webec.exercise.repository.DeviceRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Controller
public class DeviceController {
    private final DeviceRepository deviceRepository;

    public DeviceController(DeviceRepository deviceRepository) { this.deviceRepository = deviceRepository; }

    @RequestMapping(path = "/devices", method = RequestMethod.GET)
    public String index(@RequestParam() Optional<String> search, Model model) {
        model.addAttribute("devices", this.deviceRepository.findBySearch(search.orElse("")));

        return "device/index";
    }

    @RequestMapping(path = "/devices/{id}", method = RequestMethod.GET)
    public String showDevice(@PathVariable int id, Model model) {
        model.addAttribute("device", this.deviceRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND)));

        return "device/show";

    }
}
