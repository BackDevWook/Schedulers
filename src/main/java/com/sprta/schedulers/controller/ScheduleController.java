package com.sprta.schedulers.controller;

import com.sprta.schedulers.dto.DeleteScheduleDto;
import com.sprta.schedulers.dto.GetScheduleDto;
import com.sprta.schedulers.dto.PostScheduleDto;
import com.sprta.schedulers.dto.PutScheduleDto;
import com.sprta.schedulers.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/schedules")
public class ScheduleController {

    // 서비스 클래스
    ScheduleService scheduleService;

    @Autowired
    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }



    // 스케줄 조회하기
    @GetMapping("/{id}")
    public ResponseEntity<GetScheduleDto> getSchedule(@PathVariable long id) {
        return ResponseEntity.ok(scheduleService.getScheduleService(id));
    }

    // 스케줄 전체조회
    @GetMapping
    public ResponseEntity<List<GetScheduleDto>> getAllSchedules() {
        return ResponseEntity.ok(scheduleService.getAllSchedulesService());
    }

    // 스케줄 생성하기
    @PostMapping
    public ResponseEntity<String> postSchedule(@RequestBody PostScheduleDto postScheduleDto) {
        // 글자 수 제한
        if(postScheduleDto.getWhoName().length() > 25) {
            return ResponseEntity.badRequest().body("이름 길이가 초과하셨습니다.");
        } else if (postScheduleDto.getTitle().length() > 25 ) {
           return ResponseEntity.badRequest().body("title 길이가 초과하셨습니다.");
        } else if (postScheduleDto.getContent().length() > 500) {
            return ResponseEntity.badRequest().body("content 길이가 초과하셨습니다.");
        } else if ( postScheduleDto.getPassword().length() > 25 ) {
            return ResponseEntity.badRequest().body("비밀번호 길이가 초과하셨습니다.");
        }
        scheduleService.postService(postScheduleDto);
        return ResponseEntity.ok("등록 완료");
    }

    // 스케줄 수정하기
    @PutMapping("/{id}")
    public ResponseEntity<String> updateSchedule(@PathVariable Long id, @RequestBody PutScheduleDto putScheduleDto) {
        //비밀번호가 일치할 경우 수정
        if (scheduleService.checkPassword(putScheduleDto.getPassword(), id)) {
            scheduleService.putScheduleService(id, putScheduleDto);
        } else {
            return ResponseEntity.badRequest().body("비밀번호 틀림");
        }
        return ResponseEntity.ok("수정 됌");
    }

    // 스케줄 삭제하기
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSchedule(@PathVariable Long id, @RequestBody DeleteScheduleDto deleteDto) {

        if (scheduleService.checkPassword(deleteDto.getPassword(), id)) {
            scheduleService.deleteService(id);
            return ResponseEntity.ok("삭제 됌");
        };
        return ResponseEntity.badRequest().body("비밀번호 틀림");
    }
}
