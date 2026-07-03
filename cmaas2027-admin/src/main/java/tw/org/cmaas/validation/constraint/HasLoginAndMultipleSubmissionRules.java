package tw.org.cmaas.validation.constraint;

import tw.org.cmaas.enums.CommonStatusEnum;

public interface HasLoginAndMultipleSubmissionRules {

	public CommonStatusEnum getRequireLogin();
	public CommonStatusEnum getAllowMultipleSubmissions();
	
}
