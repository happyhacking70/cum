/**
 * 
 */
package jp.happyhacking.cum.aud.chnlLyr;

import jp.happyhacking.cum.aud.excp.CumExcpChnlNotExist;
import jp.happyhacking.cum.aud.excp.CumExcpIllegalChnlStatus;
import jp.happyhacking.cum.aud.excp.CumExcpIllegalSeshStatus;

/**
 * Audience Channel Interface called by Channel View
 * 
 * @author happyhacking70@gmail.com
 * 
 */
public interface AudChnlIntfForView {

	/**
	 * Leave Channel
	 * <UL>
	 * <LI>ask session to leave channel</LI>
	 * </UL>
	 * 
	 * @throws CumExcpIllegalChnlStatus
	 * @throws CumExcpIllegalSeshStatus
	 * @throws CumExcpChnlNotExist
	 */
	void lvChnl() throws CumExcpIllegalChnlStatus, CumExcpIllegalSeshStatus,
			CumExcpChnlNotExist;

}
