package com.conexaproject.star_wars_app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Objects;

import static com.conexaproject.star_wars_app.utility.Constants.CACHE_NOT_FOUND;
import static com.conexaproject.star_wars_app.utility.Constants.CACHE_SUCCESS_MSG;

@Service
public class CacheService {

    @Autowired
    private CacheManager cacheManager;

    @Scheduled(fixedDelayString = "${delay.cache}")
    public void cleanCacheByTimeInterval() {
        clearCache();
    }

    @Scheduled(cron="${cache.clear.cron}")
    public void cleanCacheEveryDay(){
        clearCache();
    }


    private void clearCache() {
        cacheManager.getCacheNames().forEach(cacheName -> Objects.requireNonNull(cacheManager.getCache(cacheName)).clear());
    }

    public String cleanCache(String nameCache) {
        if (cacheManager.getCacheNames().contains(nameCache)) {
            Objects.requireNonNull(cacheManager.getCache(nameCache)).clear();
            return CACHE_SUCCESS_MSG;
        } else {
            return CACHE_NOT_FOUND;
        }
    }
}
