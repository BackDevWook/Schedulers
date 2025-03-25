package com.sprta.schedulers.repository;

import com.sprta.schedulers.dto.GetScheduleDto;
import com.sprta.schedulers.dto.PostScheduleDto;
import com.sprta.schedulers.entity.Schedule;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleRepository {

    // 스케줄 생성
    public void addSchedule(PostScheduleDto postScheduleDto);

    // 스케줄 단일 조회
    public  GetScheduleDto getSchedule(Long id);

    // 스케줄 전체 조회
    public List<GetScheduleDto> getAllSchedule();

    // 스케줄 수정
    public void updateSchedule(String whoName, String title, String content, Long id);

    // 스케줄 삭제
    public void deleteSchedule(Long id);

    // id로 데이터 찾기
    public Schedule findById(Long id);
}
