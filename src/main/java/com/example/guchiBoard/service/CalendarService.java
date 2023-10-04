package com.example.guchiBoard.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.guchiBoard.dao.CalendarMapper;
import com.example.guchiBoard.entity.Calendar;

/**
 * 投稿 Service
 */
@Service
public class CalendarService {

    /**
     * 投稿 Mapper
     */
    @Autowired
    private CalendarMapper calendarMapper;
    
    /**
     * 
     */
    public List<Calendar> findCalendar() {
    	return calendarMapper.findCalendar();
    }
}
