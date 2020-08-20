package com.example.wx.controller;

import com.example.db.pojo.Users;
import com.example.db.service.UsersService;
import com.example.db.vo.UserVo;
import com.example.wx.utils.RedisOperator;
import com.github.pagehelper.util.StringUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.UUID;

@Controller
public class UsersController {

    public static final String USER_REDIS_SESSION = "user-redis-session";

    // 文件保存的命名空间
    public static final String FILE_SPACE = "C:/imooc_videos_dev";

    // ffmpeg所在目录
    public static final String FFMPEG_EXE = "C:\\ffmpeg\\bin\\ffmpeg.exe";

    // 每页分页的记录数
    public static final Integer PAGE_SIZE = 5;


    @Autowired
    UsersService usersService;

    @Autowired
    public RedisOperator redis;

    @RequestMapping("/users")
    @ResponseBody
    public List<Users> getusers(){
        return usersService.getusers();
    }

    @RequestMapping("/regist")
    @ResponseBody
    public com.imooc.utils.IMoocJSONResult regist(@RequestBody Users user){
        // 1. 判断用户名和密码必须不为空
        if(StringUtil.isEmpty(user.getUsername())){
           return com.imooc.utils.IMoocJSONResult.errorMsg("用户名和密码不能为空");
        }
        //2. 判断用户名是否存在
        Boolean flag=usersService.queryUsernameIsExist(user.getUsername());
        if(!flag){
            user.setNickname(user.getUsername());
            user.setPassword(user.getPassword());
            user.setFansCounts(0);
            user.setFollowCounts(0);
            user.setReceiveLikeCounts(0);
            usersService.saveuser(user);
        }else{
           return com.imooc.utils.IMoocJSONResult.errorMsg("用户已存在");
        }

        UserVo userVo=setUserRedisSessionToken(user);
        return com.imooc.utils.IMoocJSONResult.ok(userVo);
    }


    public UserVo setUserRedisSessionToken(Users userModel) {
        String uniqueToken = UUID.randomUUID().toString();
        redis.set(USER_REDIS_SESSION + ":" + userModel.getId(), uniqueToken, 60*60*2);

        UserVo userVo = new UserVo();
        BeanUtils.copyProperties(userModel, userVo);
        userVo.setUserToken(uniqueToken);
        return userVo;
    }
    @RequestMapping("/login")
    @ResponseBody
    public com.imooc.utils.IMoocJSONResult login(@RequestBody Users user){
        String username = user.getUsername();
        String password = user.getPassword();
        // 1. 判断用户名和密码必须不为空
        if(StringUtil.isEmpty(username)||StringUtil.isEmpty(password)){
            return com.imooc.utils.IMoocJSONResult.ok("用户名或密码不能为空...");
        }
        // 2. 判断用户是否存在
        Users userResult=usersService.queryUserForLogin(user.getUsername(),user.getPassword());
        if(userResult==null){
            return com.imooc.utils.IMoocJSONResult.ok("用户不存在");
        }

        // 3. 返回
        //3.1设置刷新token
        UserVo userVo = setUserRedisSessionToken(userResult);
        //3.2返回结果
        return com.imooc.utils.IMoocJSONResult.ok(userVo);
    }
}
