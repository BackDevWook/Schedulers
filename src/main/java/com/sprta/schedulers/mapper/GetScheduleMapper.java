package com.sprta.schedulers.mapper;

import com.sprta.schedulers.dto.GetScheduleDto;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GetScheduleMapper implements RowMapper<GetScheduleDto> {
    @Override
    public GetScheduleDto mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new GetScheduleDto(
                rs.getString("who_name"),
                rs.getString("title"),
                rs.getString("content"),
                rs.getTimestamp("create_date").toLocalDateTime(),
                rs.getTimestamp("modify_date").toLocalDateTime()
        );
    }
}
