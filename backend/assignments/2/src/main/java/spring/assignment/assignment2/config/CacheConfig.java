package spring.assignment.assignment2.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
public class CacheConfig {
    @Bean
    public Caffeine<Object,Object> caffeineConfiger(){
        return Caffeine.newBuilder()
                .maximumSize(15)
                .expireAfterAccess(2, TimeUnit.MINUTES);
    }

    /**
     *
     * @param caffeine
     * @return
     */
    public CacheManager cacheManager(Caffeine<Object,Object> caffeine){
        CaffeineCacheManager cacheManager = new CaffeineCacheManager();
        cacheManager.setCaffeine(caffeine);
        return cacheManager;
    }
}
