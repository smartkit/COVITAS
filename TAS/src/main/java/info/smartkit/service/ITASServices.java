package info.smartkit.service;

import info.smartkit.pojo.PFMessage;
import org.springframework.stereotype.Service;

/**
 * Created by smartkit on 28/04/2017.
 */
@Service
public interface ITASServices {
    public Object personalityForge(PFMessage message);
}
