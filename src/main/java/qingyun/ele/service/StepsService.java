package qingyun.ele.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import qingyun.ele.domain.db.ProjectSteps;
import qingyun.ele.domain.db.Steps;
import qingyun.ele.repository.ProjectStepsRepository;
import qingyun.ele.repository.StepsRepository;

@Service
@Transactional(readOnly = true)
public class StepsService {

	@Autowired
	private StepsRepository stepsRepository;
	@Autowired
	private ProjectStepsRepository projectStepsRepository;

	// 判断是否出现签字按钮 0不出现，1出现
	public int isEditable(Long stepsId, Long eventId) {
		int editable = 0;
		Steps step = stepsRepository.findOne(stepsId);
		ProjectSteps projectSteps = projectStepsRepository.findByProjectIdAndStepId(eventId, stepsId);

		return editable;
	}

}
