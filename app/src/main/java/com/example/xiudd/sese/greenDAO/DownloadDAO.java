package com.example.xiudd.sese.greenDAO;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Xuyijie on 2018/10/19.
 */
@Entity
public class DownloadDAO {
    @Id(autoincrement = true)
    private Long id;
    @Property(nameInDb = "name")
    private String name;
    @Property(nameInDb = "path")
    private String path;
    @Property(nameInDb = "url")
    private String url;
    @Property(nameInDb = "image")
    private String image;
    @Property(nameInDb = "down")
    private int down;
    @Generated(hash = 888344225)
    public DownloadDAO(Long id, String name, String path, String url, String image,
            int down) {
        this.id = id;
        this.name = name;
        this.path = path;
        this.url = url;
        this.image = image;
        this.down = down;
    }
    @Generated(hash = 427925700)
    public DownloadDAO() {
    }

    @Override
    public String toString() {
        return "DownloadDAO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", path='" + path + '\'' +
                ", url='" + url + '\'' +
                ", image='" + image + '\'' +
                ", down=" + down +
                '}';
    }

    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPath() {
        return this.path;
    }
    public void setPath(String path) {
        this.path = path;
    }
    public String getUrl() {
        return this.url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getImage() {
        return this.image;
    }
    public void setImage(String image) {
        this.image = image;
    }
    public int getDown() {
        return this.down;
    }
    public void setDown(int down) {
        this.down = down;
    }


}
