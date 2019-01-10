package com.course.selection.service.impl;

import com.course.selection.bean.BroadCast;
import com.course.selection.dao.BroadCastDao;
import com.course.selection.service.BroadCastService;
import com.course.selection.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
@Service("broadCastService")
public class BroadCastServiceImpl implements BroadCastService {
    @Autowired
    private BroadCastDao broadCastDao;
    @Override
    public int addBroadCast(String title, String address) {
        LocalDateTime localDateTime = LocalDateTime.now();
        String date = DateUtil.localDateTimeFormat(localDateTime,DateUtil.FORMAT_PATTERN1);
        BroadCast broadCast = BroadCast.builder()
                .address(address)
                .title(title)
                .state(1)
                .date(date).build();
        return broadCastDao.addBroadCast(broadCast);
    }

    @Override
    public int updateBroadCast(String title, String address, Integer id, Integer state) {
        BroadCast broadCast = BroadCast.builder()
                .address(address)
                .title(title)
                .state(state)
                .id(id)
                .build();
        return broadCastDao.updateBroadCast(broadCast);
    }

    @Override
    public List<BroadCast> queryBroadCasts() {
        return broadCastDao.queryBroadCasts();
    }
}
