package com.zhu.config.mongodbconfig.prop;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by zhu on 2016/12/21.
 * 用于读取 mongo 连接属性的配置文件
 */
@ConfigurationProperties(prefix = "mongodb",locations = "classpath:mongodb.properties")
public class MongoProp {
    private String host ;
    private Integer port;
    private String username;
    private String password;
    private String dbname;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDbname() {
        return dbname;
    }

    public void setDbname(String dbname) {
        this.dbname = dbname;
    }
}
