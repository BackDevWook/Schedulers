package com.sprta.schedulers.controller;

import com.sprta.schedulers.dto.GetScheduleDto;
import com.sprta.schedulers.dto.PostScheduleDto;
import com.sprta.schedulers.dto.PutScheduleDto;
import com.sprta.schedulers.entity.Schedule;
import com.sprta.schedulers.repository.JdbcScheduleRepository;
import com.sprta.schedulers.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/schedules")
public class ScheduleController {

    @Autowired
    public ScheduleController(JdbcScheduleRepository jdbcScheduleRepository) {
        this.jdbcScheduleRepository = jdbcScheduleRepository;
    }

    JdbcScheduleRepository jdbcScheduleRepository;

    // 스케줄 조회하기
    @GetMapping("/{id}")
    public GetScheduleDto getSchedule(@PathVariable long id) {
        return jdbcScheduleRepository.getSchedule(id);
    }

    // 스케줄 전체조회
    @GetMapping
    public List<GetScheduleDto> getAllSchedules() {
        return jdbcScheduleRepository.getAllSchedule();
    }

    // 스케줄 생성하기
    @PostMapping
    public String postSchedule(@RequestBody PostScheduleDto postScheduleDto) {
        // 글자 수 제한
        if(postScheduleDto.getWhoName().length() > 25
            || postScheduleDto.getTitle().length() > 25
            || postScheduleDto.getContent().length() > 500
            || postScheduleDto.getPassword().length() > 25 ) {
           throw new IllegalArgumentException("잘못된 입력입니다.");
        }

        jdbcScheduleRepository.addSchedule(postScheduleDto);
        return "일정이 정상적으로 등록되었습니다.";
    }

    // 스케줄 수정하기
    @PutMapping("/{id}")
    public String updateSchedule(@PathVariable Long id, @RequestBody PutScheduleDto putScheduleDto) {
        //비밀번호가 일치할 경우 수정
        if (new ScheduleService(jdbcScheduleRepository).checkPassword(putScheduleDto.getPassword(), id)) {
            jdbcScheduleRepository.updateSchedule(
                    putScheduleDto.getWhoName(),
                    putScheduleDto.getTitle(),
                    putScheduleDto.getContent(),
                    id
            );
        } else {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
        return "수정 완료";
    }

    // 스케줄 삭제하기
    @DeleteMapping("/{id}")
    public String deleteSchedule(@PathVariable Long id, @RequestBody Schedule schedule) {
        if(new ScheduleService(jdbcScheduleRepository).checkPassword(schedule.getPassword(), id)) {
            jdbcScheduleRepository.deleteSchedule(id);
            return "삭제됌";
        }
        return "비밀번호 틀림";
    }
}
