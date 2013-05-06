/**
 * 
 */
package jp.happyhacking70.cum.prestr.chnlLyr;

import java.util.HashMap;

import jp.happyhacking70.cum.excp.prestr.CumExcpChnlNotExist;
import jp.happyhacking70.cum.excp.prestr.CumExcpIllegalChnlStatus;
import jp.happyhacking70.cum.excp.prestr.CumExcpIllegalSeshStatus;
import jp.happyhacking70.cum.excp.prestr.CumExcpSeshDiscned;

/**
 * @author happyhacking70@gmail.com
 * 
 */
public interface PrestrChnlIntfFromChnlView {

	/**
	 * Close this channel
	 * 
	 * @throws CumExcpChnlNotExist
	 * @throws CumExcpIllegalChnlStatus
	 * @throws CumExcpIllegalSeshStatus
	 * @throws CumExcpSeshDiscned
	 * 
	 */
	void clsChnl() throws CumExcpChnlNotExist, CumExcpIllegalChnlStatus,
			CumExcpIllegalSeshStatus, CumExcpSeshDiscned;

	/**
	 * Send channel specific command to audience(s)
	 * 
	 * @param actionName
	 * @param params
	 * @throws CumExcpIllegalChnlStatus
	 * @throws CumExcpIllegalSeshStatus
	 * @throws CumExcpChnlNotExist
	 * @throws CumExcpSeshDiscned
	 */
	void sendChnlCmd(String actionName, HashMap<String, String> params)
			throws CumExcpIllegalChnlStatus, CumExcpChnlNotExist,
			CumExcpIllegalSeshStatus, CumExcpSeshDiscned;

}
