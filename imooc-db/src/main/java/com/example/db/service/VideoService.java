package com.example.db.service;

import org.n3r.idworker.Sid;
import com.example.db.mapper.VideoMapper;
import com.example.db.pojo.Video;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VideoService {
    @Autowired
    VideoMapper videoMapper;

    @Autowired
    private Sid sid;

    public String saveVideo(Video video){
        String videoId = sid.nextShort();
        video.setId(videoId);
        videoMapper.saveVideo(video);
        return videoId;
    }
}
