package tw.org.cmaas.constants;

import java.util.Map;

/**
 * 臨時創建Workshop處理<br>
 * 主會議 與 工作坊 的代號及名稱,費用計算、通知信件共用
 *
 */
public final class WorkshopConstants {

	// 私有化構造函數,禁止被 new
	private WorkshopConstants() {}

	/** 主會議的workshop代號 */
	public static final String MAIN_CONFERENCE_CODE = "MAIN";

	/** workshop代號 對應 顯示名稱 */
	public static final Map<String, String> WORKSHOP_NAME_MAP = Map.of("WSA001", "1/23 早上A場", "WSA002", "1/23 下午A場",
			"WSB001", "1/23 早上B場", "WSB002", "1/23 下午B場", MAIN_CONFERENCE_CODE, "1/24 主會議");

}
