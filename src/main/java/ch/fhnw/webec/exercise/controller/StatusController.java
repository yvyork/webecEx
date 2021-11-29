package ch.fhnw.webec.exercise.controller;

import ch.fhnw.webec.exercise.model.Status;
import ch.fhnw.webec.exercise.repository.StatusRepository;
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
public class StatusController {
    private final StatusRepository statusRepository;

    public StatusController(StatusRepository statusRepository) {
        this.statusRepository = statusRepository;
    }

    @RequestMapping(path = "/statuses", method = RequestMethod.GET)
    public String index(@RequestParam() Optional<String> search, Model model) {
        model.addAttribute("statuses", this.statusRepository.findBySearch(search.orElse("")));
        return "status/index";
    }

    @RequestMapping(path="/statuses/{id}", method = RequestMethod.GET)
    public String showStatus(@PathVariable int id, Model model) {
        model.addAttribute("status", this.statusRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));
        return "status/show";
    }

    @RequestMapping(path = "/statuses/add", method = RequestMethod.GET)
    public String addStatus(Model model) {
        return "status/add-or-edit";
    }

    @RequestMapping(path = "/statuses/add", method = RequestMethod.POST)
    public String addStatus(@Valid Status status, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("status", status);
            return "status/add-or-edit";
        } else {
            this.statusRepository.save(status);
            return "redirect:/statuses/";
        }
    }

    @RequestMapping(path = "/statuses/{id}/edit", method = RequestMethod.GET)
    public String editStatus(@PathVariable int id, Model model) {
        model.addAttribute("status", this.statusRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));
        return "status/add-or-edit";
    }

    @RequestMapping(path = "/statuses/{id}/edit", method = RequestMethod.POST)
    public String editStatus(@Valid Status status, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("status", status);
            return "status/add-or-edit";
        } else {
            this.statusRepository.save(status);
            return "redirect:/statuses/";
        }
    }

    @RequestMapping(path = "/statuses/{id}/delete", method = RequestMethod.POST)
    public String deleteStatus(@PathVariable int id) {
        this.statusRepository.delete(this.statusRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));
        return "redirect:/statuses/";
    }
}
