package qingyun.ele.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import qingyun.ele.domain.db.DicDic;

public interface DicDicRepository  extends JpaRepository<DicDic, Long> {
	
	public DicDic findByName(String name);

}
