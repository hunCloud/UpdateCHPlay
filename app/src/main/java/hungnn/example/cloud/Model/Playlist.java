package hungnn.example.cloud.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Playlist implements Serializable {

    @SerializedName("IdPlaylist")
    @Expose
    private String idPlaylist;
    @SerializedName("Ten")
    @Expose
    private String ten;
    @SerializedName("HinhPlayList")
    @Expose
    private String hinhPlayList;
    @SerializedName("Icon")
    @Expose
    private String icon;

    public String getIdPlaylist() {
        return idPlaylist;
    }

    public void setIdPlaylist(String idPlaylist) {
        this.idPlaylist = idPlaylist;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getHinhPlayList() {
        return hinhPlayList;
    }

    public void setHinhPlayList(String hinhPlayList) {
        this.hinhPlayList = hinhPlayList;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

}