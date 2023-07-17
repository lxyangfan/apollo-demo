package com.example.demo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

@RefreshScope
@Component
@ConfigurationProperties(value = "auth.url")
public class UrlConfigProperties {

    private String whiteListStr;

    @Override
    public String toString() {
        return "UrlConfigProperties{" +
                "whiteListStr='" + whiteListStr + '\'' +
                '}';
    }

    public String getWhiteListStr() {
        return whiteListStr;
    }

    public void setWhiteListStr(String whiteListStr) {
        this.whiteListStr = whiteListStr;
    }
}
