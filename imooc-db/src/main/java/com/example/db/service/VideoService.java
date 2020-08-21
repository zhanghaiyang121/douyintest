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
        System.out.println("===============");
        System.out.println(video.getAudioId());
        System.out.println(video.getUserId());
        System.out.println(video.getVideoSeconds());
        System.out.println(video.getVideoHeight());
        System.out.println(video.getVideoWidth());
        System.out.println(video.getVideoDesc());
        System.out.println(video.getVideoPath());
        System.out.println(video.getCoverPath());
        System.out.println(video.getStatus());
        System.out.println(video.getId());
        videoMapper.saveVideo(video);
        return videoId;
    }
}
