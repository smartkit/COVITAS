package info.smartkit.tas.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import info.smartkit.tas.pojo.MessageResponse;
import info.smartkit.tas.pojo.PFMessage;
import info.smartkit.tas.pojo.PFMessageResponse;

import java.io.IOException;

/**
 * Created by smartkit on 28/04/2017.
 */
public interface ITASServices {
    public MessageResponse personalityForge(PFMessage message) throws IOException;
}
