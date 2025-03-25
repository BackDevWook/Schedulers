package com.sprta.schedulers.service;

import com.sprta.schedulers.dto.GetScheduleDto;
import com.sprta.schedulers.dto.PostScheduleDto;
import com.sprta.schedulers.dto.PutScheduleDto;
import com.sprta.schedulers.entity.Schedule;
import com.sprta.schedulers.repository.JdbcScheduleRepository;
import com.sprta.schedulers.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    @Autowired
    public ScheduleService(JdbcScheduleRepository jdbcScheduleRepository) {
        this.scheduleRepository = jdbcScheduleRepository;
    }

    //비밀번호 확인
    public boolean checkPassword(String password, Long id) {

        return password.equals(scheduleRepository.findById(id).getPassword());

    }

    //데이터 생성 요청
    public void postService(PostScheduleDto postScheduleDto) {
        scheduleRepository.addSchedule(postScheduleDto);
    }
    //데이터 삭제 요청
    public void deleteService(Long id) {
        scheduleRepository.deleteSchedule(id);
    }
    //데이터 단일 조회 요청
    public GetScheduleDto getScheduleService(Long id) {
        return scheduleRepository.getSchedule(id);
    }
    //데이터 전체 조회 요청
    public List<GetScheduleDto> getAllSchedulesService() {
        return scheduleRepository.getAllSchedule();
    }
    //데이터 수정 요청
    public void putScheduleService(Long id, PutScheduleDto putScheduleDto) {
        scheduleRepository.updateSchedule(
                putScheduleDto.getWhoName(),
                putScheduleDto.getTitle(),
                putScheduleDto.getContent(),
                id
        );
    }
}
