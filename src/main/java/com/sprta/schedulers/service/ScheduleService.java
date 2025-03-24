package com.sprta.schedulers.service;

import com.sprta.schedulers.dto.PutScheduleDto;
import com.sprta.schedulers.entity.Schedule;
import com.sprta.schedulers.repository.JdbcScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScheduleService {

    private final JdbcScheduleRepository jdbcScheduleRepository;

    @Autowired
    public ScheduleService(JdbcScheduleRepository jdbcScheduleRepository) {
        this.jdbcScheduleRepository = jdbcScheduleRepository;
    }
    //비밀번호 확인
    public boolean checkPassword(String password, Long id) {

        return password.equals(jdbcScheduleRepository.findById(id).getPassword());

    }

}
