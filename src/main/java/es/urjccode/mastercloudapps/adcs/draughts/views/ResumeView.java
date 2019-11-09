package es.urjccode.mastercloudapps.adcs.draughts.views;

import es.urjccode.mastercloudapps.adcs.draughts.controllers.ResumeController;
import es.urjccode.mastercloudapps.adcs.draughts.utils.YesNoDialog;

public class ResumeView extends WithConsoleView {

	private YesNoDialog yesNoDialog;

	public ResumeView() {
		super();
		this.yesNoDialog = new YesNoDialog();
	}

	void interact(ResumeController resumeController) {
		if (this.yesNoDialog.read(MessageView.MESSAGE_RESUME.getMessage())) {
			resumeController.reset();
		} else {
			resumeController.next();
		}

	}
}
