package com.licenseservice.repository;

import com.licenseservice.model.Organization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;

@Repository
public class OrganizationRedisRepositoryImpl implements OrganizationRedisRepository {

    private final static String HASH_NAME = "organization";

    private RedisTemplate<Long, Organization> redisTemplate;
    private HashOperations hashOperations;

    @Autowired
    public OrganizationRedisRepositoryImpl(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @PostConstruct
    public void init() {
        hashOperations = redisTemplate.opsForHash();
    }

    @Override
    public void saveOrganization(Organization org) {
        hashOperations.put(HASH_NAME, org.getId(), org);
    }

    @Override
    public void updateOrganization(Organization org) {
        hashOperations.put(HASH_NAME, org.getId(), org);
    }

    @Override
    public void deleteOrganization(Long organizationId) {
        hashOperations.delete(organizationId);
    }

    @Override
    public Organization findOrganization(Long organizationId) {
        return (Organization) hashOperations.get(HASH_NAME, organizationId);
    }
}
