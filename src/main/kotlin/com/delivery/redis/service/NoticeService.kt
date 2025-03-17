package com.delivery.redis.service

import com.delivery.redis.common.Log
import com.delivery.redis.model.NoticeDto
import com.delivery.redis.repository.NoticeRepository
import org.springframework.cache.annotation.CachePut
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service

@Service
class NoticeService(
    private val noticeRepository: NoticeRepository
) {
    companion object: Log

    @Cacheable(cacheNames = ["notice"], key = "#id") //notice::id
    fun getNotice(id: Long?): NoticeDto? {
        log.info("notice service get notice : {}", id)
        return noticeRepository.getNotice(id)
    }

    @CachePut(cacheNames = ["notice"], key="#notice.id") //notice-id(auto-gen)
    fun addNotice(notice: NoticeDto?): NoticeDto? {
        log.info("notice service add notice : {}", notice)
        return noticeRepository.addNotice(notice)
    }
}