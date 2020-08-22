package com.example.db.mapper;



import com.example.db.pojo.Users;
import com.example.db.pojo.Video;
import com.example.db.vo.VideoVo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VideoMapper {
   void saveVideo(Video video);
   List<VideoVo> getVedioList(VideoVo video);
}
