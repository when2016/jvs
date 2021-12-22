package com.bctools.document.repository;

import com.bctools.document.po.DocumentEsPo;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author: ZhuXiaoKang
 * @Description:
 */
@Repository
public interface DocumentRepository extends PagingAndSortingRepository<DocumentEsPo, String> {
}
