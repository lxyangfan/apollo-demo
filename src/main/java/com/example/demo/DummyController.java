package com.example.demo;

import com.example.demo.config.UrlConfigListener;
import com.example.demo.config.UrlConfigProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/")
@RestController
@Slf4j
@RefreshScope
public class DummyController {

    @Autowired
    private UrlConfigProperties urlConfigProperties;
    @Autowired
    private UrlConfigListener urlConfigListener;


    @GetMapping("hello")
    public ResponseEntity<String> hello() {
      log.info("urlConfigProperties: {}, urlConfigListener:{}", urlConfigProperties, urlConfigListener);
        return ResponseEntity.ok(urlConfigProperties.getWhiteListStr());
    }

}
