package com.cjj.qlemusic.security.service.impl;


import com.cjj.qlemusic.security.entity.UmsUser;
import com.cjj.qlemusic.security.service.UmsUserCacheService;
import com.cjj.qlemusic.security.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


/**
 * 用户缓存Service实现类
 */
@Service
public class UmsUserCacheServiceImpl implements UmsUserCacheService {
    @Value("${redis.database}")
    private String database;
    @Value("${redis.key.admin}")
    private String admin;
    @Value("${redis.key.verify}")
    private String verifyKey;
    @Value("${redis.key.resourceList}")
    private String resourceList;
    @Value("${redis.expire.common}")
    private long expiration;
    @Value("${redis.expire.verify}")
    private long expirationVerify;

    @Autowired
    private RedisService redisService;

    @Override
    public void setUser(UmsUser umsUser) {
        String key = database + admin + umsUser.getIdentity();
        redisService.set(key, umsUser,expiration);
    }


    @Override
    public UmsUser getUser(String identity) {
        String key = database + admin + identity;
        return (UmsUser) redisService.get(key);
    }


    @Override
    public void delUser(String identity) {
        String key = database + admin + identity;
        redisService.del(key);
    }

    @Override
    public void setVerify(String telephone, String verify) {
        redisService.set(database + verifyKey + telephone,verify,expirationVerify);
    }

    @Override
    public String getVerify(String telephone) {
        return (String) redisService.get(database + verifyKey + telephone);
    }

    @Override
    public void delVerify(String telephone) {
        redisService.del(database + verifyKey + telephone);
    }

}
