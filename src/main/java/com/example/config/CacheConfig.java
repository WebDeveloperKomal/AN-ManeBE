/*
 * package com.example.config;
 * 
 * import org.ehcache.CacheManager; import
 * org.springframework.cache.annotation.EnableCaching; import
 * org.springframework.cache.ehcache.EhCacheCacheManager; import
 * org.springframework.cache.ehcache.EhCacheManagerFactoryBean; import
 * org.springframework.context.annotation.Bean; import
 * org.springframework.context.annotation.Configuration; import
 * org.springframework.core.io.ClassPathResource;
 * 
 * @Configuration
 * 
 * @EnableCaching public class CacheConfig {
 * 
 * @Bean public CacheManager cacheManager() { // Configure and return your
 * CacheManager (e.g., EhCacheManager) return new
 * EhCacheCacheManager(ehCacheCacheManager().getObject()); }
 * 
 * @Bean public EhCacheManagerFactoryBean ehCacheCacheManager() {
 * EhCacheManagerFactoryBean cmfb = new EhCacheManagerFactoryBean();
 * cmfb.setConfigLocation(new ClassPathResource("ehcache.xml"));
 * cmfb.setShared(true); return cmfb; } }
 */



