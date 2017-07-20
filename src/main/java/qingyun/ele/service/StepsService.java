package qingyun.ele.service;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import qingyun.ele.domain.db.ProjectSteps;
import qingyun.ele.repository.ProjectStepsRepository;

@Service
@Transactional(readOnly = false)
public class StepsService {

	@Autowired
	private ProjectStepsRepository projectStepsRepository;

	@Autowired
	private EmailSenderService emailSenderService;
	private static final Log logger = LogFactory.getLog(StepsService.class);

	@Scheduled(fixedRate = 1000 * 60 * 180)
	public void sendDelayEmails() {
		// logger.debug("scan project steps");
		List<ProjectSteps> projectStepsList = projectStepsRepository.scanDelaySteps();
		for (ProjectSteps ps : projectStepsList) {
			String delayEmail = ps.getSteps().getDelay_email();
			String delayMoreEmail = ps.getSteps().getDelay_more_email();
			long lastedDays = ps.getSteps().getLastedDays(); // 最慢天数设置
			Date today = new Date();
			Date startDay = ps.getStart();
			// 已经持续天数
			long days = (today.getTime() - startDay.getTime()) / (1000 * 3600 * 24);
			// 延迟一天
			boolean save = false;

			long delayDays = days - lastedDays;
			if (delayDays == 1 && ps.getdE() == null) {
				logger.debug("delay1");
				save = true;
				ps.setdE(1l);
				// send email
				sendEmail("项目步骤延迟1天", delayEmail, ps, delayDays);
			}
			if (delayDays > 1 && ps.getdME() == null) {
				logger.debug("delay2");
				save = true;
				ps.setdME(1l);
				ps.setdE(1l);
				// send email
				sendEmail("项目步骤延迟多天", delayMoreEmail, ps, delayDays);
			}

			if (save) {
				projectStepsRepository.save(ps);
			}

		}
	}

	private void sendEmail(String subject, String email, ProjectSteps ps, Long days) {
		String[] to = new String[] { email };
		String content = "";
		content = "客户: " + ps.getCustomer().getName() + ",项目: " + ps.getCustomer().getProject() + "\r\n";
		content = content + "项目步骤: " + ps.getSteps().getName() + " 已经延迟" + days + "天。";
		try {
			emailSenderService.sendEmail(to, subject, content, null);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
