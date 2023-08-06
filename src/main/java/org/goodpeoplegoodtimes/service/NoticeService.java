package org.goodpeoplegoodtimes.service;

import lombok.RequiredArgsConstructor;
import org.goodpeoplegoodtimes.domain.Notice;
import org.goodpeoplegoodtimes.dto.NoticeDto;
import org.goodpeoplegoodtimes.repository.NoticeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class NoticeService {

    private final NoticeRepository noticeRepository;

    public Long save(NoticeDto noticeDto) {
        return noticeRepository.save(Notice.of(noticeDto)).getNotice_id();
    }

}
