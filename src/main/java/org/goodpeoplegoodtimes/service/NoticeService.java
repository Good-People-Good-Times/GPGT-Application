package org.goodpeoplegoodtimes.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.goodpeoplegoodtimes.domain.Notice;
import org.goodpeoplegoodtimes.repository.NoticeRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NoticeService {

    private final NoticeRepository noticeRepository;

    public List<Notice> getList() {
        return this.noticeRepository.findAll();
    }

}
