package uz.sudev.clickapp.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.sudev.clickapp.service.SpaceService;

@RestController
@RequestMapping(value = "/space")
public class SpaceController {
    final SpaceService spaceService;

    public SpaceController(SpaceService spaceService) {
        this.spaceService = spaceService;
    }
}
