package ch.fhnw.webec.exercise.controller;

import ch.fhnw.webec.exercise.model.Location;
import ch.fhnw.webec.exercise.repository.LocationRepository;
import org.springframework.http.HttpStatus;
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

@Controller
public class LocationController {
    private final LocationRepository locationRepository;

    public LocationController(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    @RequestMapping(path = "/locations", method = RequestMethod.GET)
    public String index(@RequestParam() Optional<String> search, Model model) {
        model.addAttribute("locations", this.locationRepository.findBySearch(search.orElse("")));
        return "location/index";
    }

    @RequestMapping(path="/locations/{id}", method = RequestMethod.GET)
    public String showLocation(@PathVariable int id, Model model) {
        model.addAttribute("location", this.locationRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));
        return "location/show";
    }

    @RequestMapping(path = "/locations/add", method = RequestMethod.GET)
    public String addLocation(Model model) {
        return "location/add-or-edit";
    }

    @RequestMapping(path = "/locations/add", method = RequestMethod.POST)
    public String addLocation(@Valid Location location, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("location", location);
            return "location/add-or-edit";
        } else {
            this.locationRepository.save(location);
            return "redirect:/locations/";
        }
    }

    @RequestMapping(path = "/locations/{id}/edit", method = RequestMethod.GET)
    public String editLocation(@PathVariable int id, Model model) {
        model.addAttribute("location", this.locationRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));
        return "location/add-or-edit";
    }

    @RequestMapping(path = "/locations/{id}/edit", method = RequestMethod.POST)
    public String editLocation(@Valid Location location, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("location", location);
            return "location/add-or-edit";
        } else {
            this.locationRepository.save(location);
            return "redirect:/locations/";
        }
    }

    @RequestMapping(path = "/locations/{id}/delete", method = RequestMethod.POST)
    public String deleteLocation(@PathVariable int id) {
        this.locationRepository.delete(this.locationRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));
        return "redirect:/locations/";
    }
}
