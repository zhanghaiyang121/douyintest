package com.example.db.pojo;


public class Users {

    private String id;

    /**
     * 用户名
     */

    private String username;

    /**
     * 密码
     */

    private String password;

    /**
     * 我的头像，如果没有默认给一张
     */


    private String faceImage;

    /**
     * 昵称
     */

    private String nickname;

    /**
     * 我的粉丝数量
     */

    private Integer fansCounts;

    /**
     * 我关注的人总数
     */

    private Integer followCounts;

    /**
     * 我接受到的赞美/收藏 的数量
     */

    private Integer receiveLikeCounts;

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getFaceImage() {
        return faceImage;
    }

    public String getNickname() {
        return nickname;
    }

    public Integer getFansCounts() {
        return fansCounts;
    }

    public Integer getFollowCounts() {
        return followCounts;
    }

    public Integer getReceiveLikeCounts() {
        return receiveLikeCounts;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFaceImage(String faceImage) {
        this.faceImage = faceImage;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setFansCounts(Integer fansCounts) {
        this.fansCounts = fansCounts;
    }

    public void setFollowCounts(Integer followCounts) {
        this.followCounts = followCounts;
    }

    public void setReceiveLikeCounts(Integer receiveLikeCounts) {
        this.receiveLikeCounts = receiveLikeCounts;
    }

    @Override
    public String toString() {
        return "Users{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", faceImage='" + faceImage + '\'' +
                ", nickname='" + nickname + '\'' +
                ", fansCounts=" + fansCounts +
                ", followCounts=" + followCounts +
                ", receiveLikeCounts=" + receiveLikeCounts +
                '}';
    }
}