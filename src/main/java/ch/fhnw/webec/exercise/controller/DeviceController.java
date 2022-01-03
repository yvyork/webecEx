package ch.fhnw.webec.exercise.controller;

import ch.fhnw.webec.exercise.model.Device;
import ch.fhnw.webec.exercise.repository.DeviceRepository;
import ch.fhnw.webec.exercise.repository.LocationRepository;
import ch.fhnw.webec.exercise.repository.StatusRepository;
import ch.fhnw.webec.exercise.repository.UserRepository;
import ch.fhnw.webec.exercise.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.Optional;

@PreAuthorize("hasRole('ADMIN')")
@Controller
public class DeviceController {
    private final DeviceRepository deviceRepository;
    private final LocationRepository locationRepository;
    private final StatusRepository statusRepository;
    private final UserService userService;

    public DeviceController(DeviceRepository deviceRepository,
                            LocationRepository locationRepository,
                            StatusRepository statusRepository, UserService userService) {
        this.deviceRepository = deviceRepository;
        this.locationRepository = locationRepository;
        this.statusRepository = statusRepository;
        this.userService = userService;

    }

    @PreAuthorize("permitAll()")
    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String index(@RequestParam() Optional<String> search, Model model) {
        model.addAttribute("search", search.orElse(""));
        model.addAttribute("devices", this.deviceRepository.findBySearch(search.orElse("")));

        return "device/index";
    }

    @PreAuthorize("permitAll()")
    @RequestMapping(path = "/devices/{id}", method = RequestMethod.GET)
    public String showDevice(@PathVariable int id, Model model) {
        model.addAttribute("device", this.deviceRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND)));

        return "device/show";

    }

    @RequestMapping(path = "/devices/add", method = RequestMethod.GET)
    public String addDevice(Model model) {
        model.addAttribute("location", this.locationRepository.findAll());
        model.addAttribute("statuses", this.statusRepository.findAll());
        return "device/add-or-edit"; }

    @RequestMapping(path = "/devices/add", method = RequestMethod.POST)
    public String addDevice(@Valid Device device, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("location", this.locationRepository.findAll());
            model.addAttribute("statuses", this.statusRepository.findAll());
            model.addAttribute("device", device);
            return "device/add-or-edit";
        } else {
            this.deviceRepository.save(device);
            return "redirect:/";
        }
    }

    @RequestMapping(path = "/devices/{id}/edit", method = RequestMethod.GET)
    public String editDevice(@PathVariable int id, Model model) {
        model.addAttribute("location", this.locationRepository.findAll());
        model.addAttribute("statuses", this.statusRepository.findAll());
        model.addAttribute("device", this.deviceRepository.findById(id).orElseThrow( () ->
                new ResponseStatusException(HttpStatus.NOT_FOUND)));

        return "device/add-or-edit";
    }

    @RequestMapping(path = "/devices/{id}/edit", method = RequestMethod.POST)
    public String editDevice(@Valid Device device, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("location", this.locationRepository.findAll());
            model.addAttribute("statuses", this.statusRepository.findAll());
            model.addAttribute("device", device);

            return "device/add-or-edit";
        } else {
            this.deviceRepository.save(device);

            return "redirect:/devices/" + device.getId();
        }
    }

    @RequestMapping(path = "/devices/{id}/delete", method = RequestMethod.POST)
    public String deleteDevice(@PathVariable int id) {
        this.deviceRepository.delete(this.deviceRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));
        return "redirect:/";
    }
}
