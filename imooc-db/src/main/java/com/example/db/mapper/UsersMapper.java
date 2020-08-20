package com.example.db.mapper;



import com.example.db.pojo.Users;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsersMapper {
   List<Users> getusers();
   Users selectOne(Users user);
   Users queryUserForLogin(Users user);
   void saveuser(Users user);
}
