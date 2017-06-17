package qingyun.ele.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import qingyun.ele.SecurityUtils;
import qingyun.ele.domain.db.SignEvent;
import qingyun.ele.domain.db.SignWorkflowSteps;
import qingyun.ele.domain.db.Users;
import qingyun.ele.repository.SignEventRepository;
import qingyun.ele.repository.SignWorkflowStepsRepository;
import qingyun.ele.repository.UsersRepository;

@Service
@Transactional(readOnly = true)
public class SignService {

	@Autowired
	private UsersRepository usersRepository;
	@Autowired
	private SignWorkflowStepsRepository signWorkflowStepsRepository;
	@Autowired
	private SignEventRepository signEventRepository;

	@Autowired
	private SecurityUtils securityUtils;

	// 判断是否出现签字按钮 0不出现，1出现
	public Long isEditable(Long signWorkflowStepsId, Long eventId, Long idSignatory) {
		Users usr = securityUtils.getCurrentDBUser();
		System.out.println(" signId: "+idSignatory +", uid: " + usr.getId() );
		//System.out.println(" signId: "+idSignatory );
		if (idSignatory == null) {
			return 0l;
		} else {
			if (!usr.getId().equals(idSignatory)) {
				return 0l;
			}
		}

		Long editable = 0l;
		System.out.println(" stepId: "+signWorkflowStepsId);
		SignWorkflowSteps signWorkflowSteps = signWorkflowStepsRepository.findOne(signWorkflowStepsId);
		SignEvent signEvent = signEventRepository.findByIdEventAndIdSignWorkflowSteps(eventId, signWorkflowStepsId);
		Long currentLvl = signWorkflowSteps.getLvl();
		Long upperLvl = currentLvl - 1l;
		// 上一级签字数

		if (signEvent == null) // 还未签字,这时有两种情况，未签但是应该签了，一种是未签但是还需要等上级签完
		{
			if (currentLvl.equals(1l)) // 第一层，可签字
			{
				System.out.println("第一层，可签");
				editable = 1l;
				return editable;

			} else {
				boolean uppperSignfinished = isThisLvlFinished(signWorkflowSteps.getIdSignWorkflow(), upperLvl,
						eventId);
				if (uppperSignfinished) {
					editable = 1l;
					return editable;
				} else {
					editable = 0l;
					return editable;
				}
			}
		} else // 已经签字
		{
			if (signEvent.getStatus().equals(0l))// 拒绝
			{
				editable = 1l;
				return editable;
			} else {
				editable = 0l;
				return editable;

			}
		}

	}

	// 判断此层是否都已经签好字
	public Boolean isThisLvlFinished(Long signWorkflowId, Long lvl, Long eventId) {
		Boolean finished = true;
		List<SignWorkflowSteps> sfs = signWorkflowStepsRepository.findByIdSignWorkflowAndLvl(signWorkflowId, lvl);
		for (SignWorkflowSteps s : sfs) {
			SignEvent signEvent = signEventRepository.findByIdEventAndIdSignWorkflowSteps(eventId, s.getId());
			if (signEvent == null) {
				finished = false;
				return finished;
			} else {
				if (signEvent.getStatus().equals(0l)) {
					finished = false;
					return finished;
				}
			}
		}
		return finished;
	}

}
