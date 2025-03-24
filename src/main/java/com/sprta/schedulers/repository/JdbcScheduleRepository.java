package com.sprta.schedulers.repository;

import com.sprta.schedulers.dto.GetScheduleDto;
import com.sprta.schedulers.dto.PostScheduleDto;
import com.sprta.schedulers.dto.PutScheduleDto;
import com.sprta.schedulers.entity.Schedule;
import com.sprta.schedulers.mapper.GetScheduleMapper;
import com.sprta.schedulers.mapper.ScheduleMapper;
import lombok.ToString;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@ToString
@Repository
public class JdbcScheduleRepository implements ScheduleRepository {

    // jdbcTemplate 초기화
    public JdbcScheduleRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    private final JdbcTemplate jdbcTemplate;

    // POST : 일정 생성
    @Override
    public void addSchedule(PostScheduleDto postScheduleDto) {
        String sql = "INSERT INTO schedule (who_name, title, content, password) VALUES (?, ?, ?, ?)";

        jdbcTemplate.update(sql,
                postScheduleDto.getWhoName(),
                postScheduleDto.getTitle(),
                postScheduleDto.getContent(),
                postScheduleDto.getPassword()
                );
    }

    // GET : 스케줄 단일 조회
    @Override
    public GetScheduleDto getSchedule(Long id) {
        String sql = "SELECT * FROM schedule WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, new GetScheduleMapper());
    }

    // GET : 스케줄 전체 조회
    @Override
    public List<GetScheduleDto> getAllSchedule() {
        String sql = "SELECT * FROM schedule";
        return jdbcTemplate.query(sql, new GetScheduleMapper());
    }

    // PUT : 스케줄 수정
    @Override
    public void updateSchedule(String whoName, String title, String content, Long id) {
        String sql ="UPDATE schedule SET who_name=?, title=?, content=? WHERE id=?";
        jdbcTemplate.update(sql, whoName, title, content, id);
    }

    // DELETE : 스케줄 삭제
    @Override
    public void deleteSchedule() {

    }



}
