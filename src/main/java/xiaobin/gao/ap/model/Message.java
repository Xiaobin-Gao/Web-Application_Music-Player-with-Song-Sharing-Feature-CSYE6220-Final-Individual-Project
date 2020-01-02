/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xiaobin.gao.ap.model;

/**
 *
 * @author gao.xiaob
 */
public class Message {

    private int id;
    private int fromUserId;
    private String mType;
    private String songContent;

    public Message() {
    }

    public Message(int fromUserId, String mType, String songContent) {
        this.fromUserId = fromUserId;
        this.mType = mType;
        this.songContent = songContent;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFromUserId() {
        return fromUserId;
    }

    public void setFromUserId(int fromUserId) {
        this.fromUserId = fromUserId;
    }

    public String getmType() {
        return mType;
    }

    public void setmType(String mType) {
        this.mType = mType;
    }

    public String getSongContent() {
        return songContent;
    }

    public void setSongContent(String songContent) {
        this.songContent = songContent;
    }

    
}
