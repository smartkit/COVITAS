package info.smartkit.tas.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import info.smartkit.tas.pojo.Message;
import info.smartkit.tas.pojo.PFMessage;
import info.smartkit.tas.pojo.User;
import info.smartkit.tas.service.ITASServices;
import info.smartkit.tas.service.impl.TASService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;


/**
 * Created by smartkit on 28/04/2017.
 */
@RestController
@RequestMapping("/tas")
public class TASController {

    private static final Logger LOG = LoggerFactory.getLogger(TASController.class);

    @Autowired
    private ITASServices tasService;

    @RequestMapping(method = RequestMethod.GET, value="pf/{chatbotID}/{message}")
    public Object simplePFMessage(@PathVariable int chatbotID,@PathVariable String message) throws JsonProcessingException {
        LOG.info("simplePFMessage called.");
        PFMessage pfMessage = new PFMessage();
        Message messageObj = new Message();
        messageObj.setChatbotID(chatbotID);
        messageObj.setMessage(message);
        //default User
        User userObj = new User();
        userObj.setExternalID("abc-639184572");
        userObj.setFirstName("Tugger");
        userObj.setLastName("Sufani");
        userObj.setGender("m");
        pfMessage.setMessage(messageObj);
        pfMessage.setUser(userObj);
        return tasService.personalityForge(pfMessage);
    }

    @RequestMapping(method = RequestMethod.POST,value = "/pf")
    public Object pfMessage(@RequestBody PFMessage pfMessage) throws JsonProcessingException {
        return tasService.personalityForge(pfMessage);
    }

//    @RequestMapping(value = "/pf", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
//    @ResponseBody
//    public Resource<Object> getArtist(@RequestBody PFMessage message) throws JsonProcessingException {
//        Object a = tasService.personalityForge(message);
//        Resource<Object> resource = new Resource(a);
//        resource.add(linkTo(methodOn(ArtistController.class).getArtist(id)).withSelfRel());
//        return a;
//    }

//    private Resource<Album> getAlbumResource(Album album) {

//        Resource<Album> resource = new Resource<Album>(album);
//
//        // Link to Album
//        resource.add(linkTo(methodOn(AlbumController.class).getAlbum(album.getId())).withSelfRel());
//        // Link to Artist
//        resource.add(linkTo(methodOn(ArtistController.class).getArtist(album.getArtist().getId())).withRel("artist"));
//        // Option to purchase Album
//        if (album.getStockLevel() > 0) {
//            resource.add(linkTo(methodOn(AlbumController.class).purchaseAlbum(album.getId())).withRel("album.purchase"));
//        }

//        return resource;
//    }
}