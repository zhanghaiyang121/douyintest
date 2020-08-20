package com.example.db.service;

import com.example.db.mapper.UsersMapper;
import com.example.db.pojo.Users;
import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersService {
    @Autowired
    UsersMapper usersMapper;

    @Autowired
    private Sid sid;
    public List<Users> getusers(){
        return usersMapper.getusers();
    }


    public boolean queryUsernameIsExist(String username) {

        Users user = new Users();
        user.setUsername(username);

        Users result = usersMapper.selectOne(user);

        return result == null ? false : true;
    }

    public Users queryUserForLogin(String username,String password){
        Users user = new Users();
        user.setUsername(username);
        user.setPassword(password);

        Users result = usersMapper.queryUserForLogin(user);
        return result;
    }

    public void saveuser(Users user){
        String userId = sid.nextShort();
        user.setId(userId);
        usersMapper.saveuser(user);
    }
}
