package com.example.PrimeService.config;

import org.ehcache.CacheManager;
import org.ehcache.config.CacheConfiguration;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@EnableCaching
public class CacheConfig {

    @Bean("customCacheManager")
    public CacheManager cacheManager() {
        CacheConfiguration<Integer, List> cacheConfiguration = CacheConfigurationBuilder.newCacheConfigurationBuilder(
                        Integer.class, List.class,
                        ResourcePoolsBuilder.heap(100))
                .build();

        CacheManager cacheManager = CacheManagerBuilder.newCacheManagerBuilder()
                .withCache("primes", cacheConfiguration)
                .build(true);

        return cacheManager;
    }
}

