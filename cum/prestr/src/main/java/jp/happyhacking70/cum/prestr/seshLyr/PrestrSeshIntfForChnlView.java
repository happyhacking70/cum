/**
 * 
 */
package jp.happyhacking70.cum.prestr.seshLyr;

import java.util.HashMap;

import jp.happyhacking70.cum.excp.prestr.CumExcpChnlNotExist;
import jp.happyhacking70.cum.excp.prestr.CumExcpIllegalSeshStatus;

/**
 * @author happyhacking70@gmail.com
 * 
 */
public interface PrestrSeshIntfForChnlView {

	/**
	 * close channel
	 * 
	 * @param chnlName
	 *            name of channel
	 * @throws CumExcpChnlNotExist
	 * @throws CumExcpIllegalSeshStatus
	 */
	public abstract void clsChnl(String chnlName) throws CumExcpChnlNotExist,
			CumExcpIllegalSeshStatus;

	/**
	 * send channel command
	 * 
	 * 
	 * 
	 * @param chnlName
	 *            name of channel
	 * @param actionName
	 *            name of action
	 * @param params
	 *            parameters
	 * @throws CumExcpIllegalSeshStatus
	 * @throws CumExcpChnlNotExist
	 */
	public abstract void sendChnlCmd(String chnlName, String actionName,
			HashMap<String, String> params) throws CumExcpChnlNotExist,
			CumExcpIllegalSeshStatus;

}