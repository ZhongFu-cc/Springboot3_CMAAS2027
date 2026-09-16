package tw.org.cmaas.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 對標 member table , membership_dues_status 屬性<br>
 * 前端於報名時傳入,用來表達「是否繳交常年會費」,此為選填項目
 *
 */
@Getter
@AllArgsConstructor
public enum MembershipDuesEnum {
	/** 本次報名一併繳交常年會費, 需加收費用 */
	PAY_ON_REGISTRATION("本次報名繳交"),
	/** 先前已繳交過常年會費, 本次報名不加收 */
	ALREADY_PAID("已繳交116年會費");

	private final String value;

	public static MembershipDuesEnum fromValue(String value) {
		for (MembershipDuesEnum type : values()) {
			if (type.value.equals(value))
				return type;
		}
		throw new IllegalArgumentException("無效的常年會費繳交狀態值: " + value);
	}

}
