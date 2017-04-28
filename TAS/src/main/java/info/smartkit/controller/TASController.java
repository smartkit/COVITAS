package info.smartkit.controller;

import info.smartkit.pojo.PFMessage;
import info.smartkit.service.ITASServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by smartkit on 28/04/2017.
 */
public class TASController {

    @Autowired
    private ITASServices tasService;

    @RequestMapping(method = RequestMethod.POST)
    public Object pfMessage(@RequestBody PFMessage message) {
        return tasService.personalityForge(message);
    }
}