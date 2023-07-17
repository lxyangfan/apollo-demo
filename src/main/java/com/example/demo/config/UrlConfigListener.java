package com.example.demo.config;

import com.ctrip.framework.apollo.model.ConfigChange;
import com.ctrip.framework.apollo.model.ConfigChangeEvent;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfigChangeListener;
import com.google.common.collect.Lists;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.endpoint.event.RefreshEventListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RefreshScope
@Component
@Data
@Slf4j
public class UrlConfigListener implements InitializingBean {

    @Autowired
    private UrlConfigProperties properties;

    @Autowired
    private org.springframework.cloud.context.scope.refresh.RefreshScope  scope;

    private List<String> urls;

    @ApolloConfigChangeListener(interestedKeyPrefixes = {"auth.url"})
    public void onChange(ConfigChangeEvent event) {
        scope.refresh("urlConfigProperties");

        for (String key : event.changedKeys()) {
            log.info("change key, {} value:{}", key, event.getChange(key));
            if ("auth.url.whiteListStr".equals(key)) {
                ConfigChange change = event.getChange(key);
                updateList(change.getNewValue());
            }
        }
    }

    private void updateList(String newStr) {
        urls = Objects.nonNull(newStr) ?
                Lists.newArrayList(newStr.split(",")) : new ArrayList<>();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        updateList(properties.getWhiteListStr());
    }
}
