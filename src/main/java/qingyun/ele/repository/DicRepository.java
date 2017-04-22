package qingyun.ele.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import qingyun.ele.domain.db.Dic;

public interface DicRepository  extends JpaRepository<Dic, Long> {
	
	@Query("select d from Dic d where d.code=?1 and d.dicDic.id=?2")
	public Dic findByCodeAndDicDicId(String code, Long dicDicId);
	
	@Query("select d from Dic d where d.dicDic.id=?1")
	public List<Dic> findByDicDicId(Long dicDicId);
	
	@Query("select d from Dic d where d.dicDic.id=?1")
	public Page<Dic> findByDicDicId(Long dicDicId, Pageable page);

}
