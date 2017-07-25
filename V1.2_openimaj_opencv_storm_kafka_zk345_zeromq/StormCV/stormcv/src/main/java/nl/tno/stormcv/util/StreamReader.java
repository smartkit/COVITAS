package nl.tno.stormcv.util;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.tno.stormcv.model.Frame;
import backtype.storm.utils.Utils;

import com.xuggle.mediatool.IMediaReader;
import com.xuggle.mediatool.MediaListenerAdapter;
import com.xuggle.mediatool.ToolFactory;
import com.xuggle.mediatool.event.IVideoPictureEvent;
import com.xuggle.xuggler.ICodec;
import com.xuggle.xuggler.IContainer;

/**
 * This class reads a video stream or file, decodes frames and puts those in a queue for further processing. 
 * The StreamReader will automatically throttle itself based on the size of the queue it writes the frames to.
 * This is done to avoid memory overload if production of frames is higher than the consumption. The actual decoding of 
 * frames is done by Xuggler which in turn uses FFMPEG (xuggler jar file is shipped with ffmpeg binaries).
 *  
 * @author Corne Versloot
 *
 */
public class StreamReader extends MediaListenerAdapter implements Runnable {
	
	private Logger logger = LoggerFactory.getLogger(StreamReader.class);
	private IMediaReader mediaReader;
    private int mVideoStreamIndex = -1;
	private String streamId;
	private int frameSkip;
	private int groupSize;
	private long frameNr; // number of the frame read so far
	private boolean running = false; // indicator if the reader is still active
	private LinkedBlockingQueue<Frame> frameQueue; // queue used to store frames
	private long lastRead = -1; // used to determine if the EOF was reached if Xuggler does not detect it
	private int sleepTime;
	private boolean useSingleID = false;
	private String tmpDir;
	private int frameMs = -1;

	private String streamLocation;
	private LinkedBlockingQueue<String> videoList = null;
	private String imageType = Frame.JPG_IMAGE;
   
	  public StreamReader( LinkedBlockingQueue<String> videoList, String imageType, int frameSkip, int groupSize, int sleepTime, boolean uniqueIdPerFile, LinkedBlockingQueue<Frame> frameQueue){
		  this.videoList = videoList;
		  this.imageType = imageType;
		  this.frameSkip = Math.max(1, frameSkip);
		  this.groupSize = Math.max(1, groupSize);
		  this.sleepTime = sleepTime;
		  this.frameQueue = frameQueue;
		  this.useSingleID = uniqueIdPerFile;
		  this.streamId = ""+this.hashCode(); // give a default streamId
		  lastRead = System.currentTimeMillis()+10000;
		  try {
			tmpDir = File.createTempFile("abc", ".tmp").getParent();
		  } catch (IOException e) {	}
	  }
	
	  public StreamReader( String streamId, String streamLocation, String imageType, int frameSkip, int groupSize, int sleepTime, LinkedBlockingQueue<Frame> frameQueue){
		  this.streamLocation = streamLocation;
		  this.imageType = imageType;
		  this.frameSkip = Math.max(1, frameSkip);
		  this.groupSize = Math.max(1, groupSize);
		  this.sleepTime = sleepTime;
		  this.frameQueue = frameQueue;
		  this.streamId = streamId;
		  lastRead = System.currentTimeMillis()+10000;
		  try {
				tmpDir = File.createTempFile("abc", ".tmp").getParent();
		  } catch (IOException e) {	}
	  }
	  
    /**
     * Start reading the provided URL
     * @param url the url to read video from
     * @param frameskip milliseconds between frames to extract
     */
	public void run(){
		running = true;
		while(running){
			try{
				// if a url was provided read it
				if(videoList == null && streamLocation != null){
					logger.info("Start reading stream: "+streamLocation);
					mediaReader = ToolFactory.makeReader(streamLocation);
				}else if(videoList != null){
					// read next video from the list or block until one is available
					logger.info("Waiting for new file to be downloaded...");
					streamLocation = videoList.take();
					
					if(!useSingleID){
						streamId = ""+streamLocation.hashCode();
						if(streamLocation.contains("/")) streamId = streamLocation.substring(streamLocation.lastIndexOf('/')+1)+"_"+streamId;
					}
					logger.info("Start reading File: "+streamLocation);
					
					// read framerate from file
			        IContainer cont = IContainer.make();
			        if (cont.open(streamLocation, IContainer.Type.READ, null) > 0) try{
				        for(int s=0; s < cont.getNumStreams(); s++){// find the videostream
				        	if(cont.getStream(s).getStreamCoder().getCodecType() == ICodec.Type.CODEC_TYPE_VIDEO){
				        		frameMs = (int)Math.floor(1000f/cont.getStream(s).getStreamCoder().getFrameRate().getDouble());
				        	}
				        }
			        }catch(Exception e){
			        	logger.warn("Unable to read metadata from video");
			        }
			        
					mediaReader = ToolFactory.makeReader(streamLocation);
				}else{
					logger.error("No video list or url provided, nothing to read");
					break;
				}
				lastRead = System.currentTimeMillis() + 10000;
	
				mVideoStreamIndex = -1;
				if(!useSingleID) frameNr = 0;
		        mediaReader.setBufferedImageTypeToGenerate(BufferedImage.TYPE_3BYTE_BGR);
		        mediaReader.addListener(this);
		        
		        while (mediaReader.readPacket() == null && running ) ;
		 
		        // reset internal state
		        mediaReader.close();
		        // delete the local file (if it is in tmp dir)
		        if(videoList != null){
		        	File localFile = new File(streamLocation);
		        	if(localFile.getAbsolutePath().startsWith(tmpDir)){
		        		localFile.delete();
		        	}
		        }
			}catch(Exception e){
				logger.warn("Stream closed unexpectatly: "+e.getMessage(), e);
				// sleep a minute and try to read the stream again
				Utils.sleep(1 * 60 * 1000);
			}
		}
		
        mVideoStreamIndex = -1;
        running = false;
	}
	
	/**
	 * Gets called when FFMPEG transcoded a frame
	 */
	public void onVideoPicture(IVideoPictureEvent event) {
		lastRead  = System.currentTimeMillis();
        if (event.getStreamIndex() != mVideoStreamIndex) {
            if (mVideoStreamIndex == -1){
                mVideoStreamIndex = event.getStreamIndex();
            }else return;
        }
        if(frameNr % frameSkip < groupSize) try{
        	BufferedImage frame = event.getImage();
        	byte[] buffer = ImageUtils.imageToBytes(frame, imageType);
        	long timestamp = event.getTimeStamp(TimeUnit.MILLISECONDS);
        	if(frameMs > 0 ) timestamp = frameNr * frameMs;
        	Frame newFrame = new Frame(streamId, frameNr, imageType, buffer, timestamp, new Rectangle(0, 0,frame.getWidth(), frame.getHeight()));
        	newFrame.getMetadata().put("uri", streamLocation);
        	frameQueue.put(newFrame);
        	// enforced throttling
        	if(sleepTime > 0) Utils.sleep(sleepTime);
        	// queue based throttling 
        	if(frameQueue.size() > 20) Utils.sleep(frameQueue.size());
        }catch(Exception e){
        	logger.warn("Unable to process new frame due to: "+e.getMessage(), e);
        }
        
        frameNr++;
    }

	/**
	 * Tells the StreamReader to stop reading frames
	 */
	public void stop(){
		running = false;
	}
	
	/**
	 * Returns whether the StreamReader is still active or not
	 * @return
	 */
	public boolean isRunning(){
		// kill this thread if the last frame read is to long ago (means Xuggler missed the EoF) and clear resources 
		if(lastRead > 0 && System.currentTimeMillis() - lastRead > 3000){
			running = false;
			if(mediaReader != null && mediaReader.getContainer() != null) mediaReader.getContainer().close();
			return this.running;
		}
		return true;
	}
	
}
	
