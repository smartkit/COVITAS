package info.smartkit.tas.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import info.smartkit.tas.pojo.PFMessage;

/**
 * Created by smartkit on 28/04/2017.
 */
public interface ITASServices {
    public Object personalityForge(PFMessage message) throws JsonProcessingException;
}
