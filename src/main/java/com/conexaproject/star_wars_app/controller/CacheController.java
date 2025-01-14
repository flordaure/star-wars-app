package com.conexaproject.star_wars_app.controller;

import com.conexaproject.star_wars_app.service.CacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CacheController extends ApiController{

    @Autowired
    CacheService cacheService;

    @PostMapping("/cleanCache/{nameCache}")
    public ResponseEntity<String> cleanCache(@PathVariable String nameCache){
        return ResponseEntity.ok(cacheService.cleanCache(nameCache));
    }
}
