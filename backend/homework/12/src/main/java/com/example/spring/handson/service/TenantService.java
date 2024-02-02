package com.example.spring.handson.service;

import com.example.spring.handson.repository.TenantRepository;
import com.example.spring.handson.entity.Tenant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service class for managing tenant-related operations.
 */
@Service
public class TenantService {

    private final TenantRepository tenantRepository;

    @Autowired
    public TenantService(TenantRepository tenantRepository) {
        this.tenantRepository = tenantRepository;
    }

    /**
     * Retrieves all tenants.
     *
     * @return The list of all tenants.
     */
    @Transactional(readOnly = true)
    public List<Tenant> getAllTenants() {
        return tenantRepository.findAll();
    }

    /**
     * Adds a new tenant.
     *
     * @param tenant The tenant to add.
     * @return The added tenant.
     */
    @Transactional
    public Tenant addTenant(Tenant tenant) {
        return tenantRepository.save(tenant);
    }
}
