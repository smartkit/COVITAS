package constellation.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wordnik.swagger.annotations.ApiOperation;

import constellation.dto.PersonCreate;

import java.util.ArrayList;
import java.util.List;

import constellation.dto.SourceFile;
import constellation.dto.SourceTarget;
import constellation.dto.SourceVideo;

@RestController
public class SourceController {
	
	@RequestMapping(method=RequestMethod.GET,value="constellation/source/create")
	@ApiOperation(httpMethod="GET",value="Select file or video for source input.")
	public int create(@RequestParam(value="fid", required=false, defaultValue="0") int fileId,
			@RequestParam(value="vid", required=false, defaultValue="0") int videoId) throws Exception
	{
		//TODO:save creating results to somewhere.
		return fileId+videoId;
	}
	@RequestMapping(method=RequestMethod.GET,value="constellation/source/files")
	@ApiOperation(httpMethod="GET",value="Get list of files.")
	public List<SourceFile> files(){
		List<SourceFile> files = new ArrayList<SourceFile>();
		//TODO:dynamic listing files.
		SourceFile file1 = new SourceFile();file1.setId(1);file1.setName("文件1");
		SourceFile file2 = new SourceFile();file2.setId(2);file2.setName("文件2");
		files.add(file1);
		files.add(file2);
        return files;
	}
	@RequestMapping(method=RequestMethod.GET,value="constellation/source/videos")
	@ApiOperation(httpMethod="GET",value="Get list of videos.")
	public List<SourceVideo> videos(){
		List<SourceVideo> videos = new ArrayList<SourceVideo>();
		//TODO:dynamic listing videos.
		SourceVideo video1 = new SourceVideo();video1.setId(1);video1.setName("视频1"); 
		SourceVideo video2 = new SourceVideo();video2.setId(2);video2.setName("视频2"); 
		videos.add(video1);
		videos.add(video2);
        return videos;
	}
	@RequestMapping(method=RequestMethod.GET,value="constellation/source/targets")
	@ApiOperation(httpMethod="GET",value="Get list of targets.")
	public List<SourceTarget> targets(){
		List<SourceTarget> targets = new ArrayList<SourceTarget>();
		//TODO:dynamic listing targets.
		SourceTarget target1 = new SourceTarget();target1.setId(1);target1.setName("人物1");target1.setImage("icon_a_01.png"); 
		SourceTarget target2 = new SourceTarget();target2.setId(2);target2.setName("人物2");target2.setImage("icon_a_02.png"); 
		SourceTarget target3 = new SourceTarget();target3.setId(3);target2.setName("人物3");target3.setImage("icon_a_03.png"); 
		SourceTarget target4 = new SourceTarget();target4.setId(4);target2.setName("人物4");target4.setImage("icon_a_04.png"); 
		targets.add(target1);
		targets.add(target2);
		targets.add(target3);
		targets.add(target4);
        return targets;
	}
}
