package com.sprta.schedulers.mapper;

import com.sprta.schedulers.entity.Schedule;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ScheduleMapper implements RowMapper<Schedule> {

    @Override
    public Schedule mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Schedule(
                rs.getLong("id"),
                rs.getString("who_name"),
                rs.getString("title"),
                rs.getString("content"),
                rs.getTimestamp("create_date").toLocalDateTime(),
                rs.getTimestamp("modify_date").toLocalDateTime(),
                rs.getString("password")
        );

    }
}
