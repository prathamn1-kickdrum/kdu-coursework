package com.example.spring.handson.dao;


import com.example.spring.handson.mapper.ShiftUserRowMapper;
import com.example.spring.handson.model.ShiftUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class ShiftUserDAO {
    final JdbcTemplate jdbcTemplate;
    @Autowired
    public ShiftUserDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public void saveShiftUser(ShiftUser shiftUser){
        String sql= "INSERT INTO shiftuser VALUES(?,?,?,?)";
        jdbcTemplate.update(sql, shiftUser.getId(), shiftUser.getShiftId(), shiftUser.getUserId(), shiftUser.getTenantId());
    }
    public ShiftUser getShiftUserById(UUID id){
        String  sql = "SELECT * FROM shiftuser WHERE id=?";
        return jdbcTemplate.queryForObject(sql, new ShiftUserRowMapper(),id);
    }
}

