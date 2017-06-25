package qingyun.ele.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import qingyun.ele.domain.db.CustomerDevice;

/**
 * Created by zhaohelong on 2017/6/25.
 */
public interface CustomerDeviceRepository extends JpaRepository<CustomerDevice,Long> {

    @Query("select c from CustomerDevice c where c.customer.id=?1")
    public Page<CustomerDevice> findByProjectId(Long projectId, Pageable pageable);
}
