package com.example.db.mapper;



import com.example.db.pojo.Users;
import com.example.db.pojo.Video;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VideoMapper {
   void saveVideo(Video video);
}
