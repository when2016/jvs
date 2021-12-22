package com.bctools.document.repository;

import com.bctools.document.po.DocumentLogEsPo;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author: ZhuXiaoKang
 * @Description:
 */

@Repository
public interface DocumentLogRepository extends PagingAndSortingRepository<DocumentLogEsPo, String> {
}
