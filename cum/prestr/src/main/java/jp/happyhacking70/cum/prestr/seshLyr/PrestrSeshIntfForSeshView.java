/**
 * 
 */
package jp.happyhacking70.cum.prestr.seshLyr;

import java.util.HashMap;

import jp.happyhacking70.cum.cmd.rsc.ChnlRscIntf;
import jp.happyhacking70.cum.excp.prestr.CumExcpChnlExists;
import jp.happyhacking70.cum.excp.prestr.CumExcpIllegalChnlStatuMulti;
import jp.happyhacking70.cum.excp.prestr.CumExcpIllegalSeshStatus;
import jp.happyhacking70.cum.prestr.prestrLyr.PrestrChnlViewIntf;

/**
 * @author happyhacking70@gmail.com
 * 
 */
public interface PrestrSeshIntfForSeshView {

	/**
	 * register session
	 * 
	 * @param seshName
	 *            name of session
	 * @throws CumExcpIllegalSeshStatus
	 */
	void regSesh(String seshName) throws CumExcpIllegalSeshStatus;

	/**
	 * close session
	 * 
	 * @throws CumExcpIllegalSeshStatus
	 * @throws CumExcpIllegalChnlStatuMulti
	 */
	void clsSesh() throws CumExcpIllegalSeshStatus,
			CumExcpIllegalChnlStatuMulti;

	/**
	 * register channel
	 * 
	 * @param chnlName
	 *            name of channel
	 * @param rsces
	 *            channel resources
	 * @param chnlView
	 *            channel view
	 * @throws CumExcpChnlExists
	 * @throws CumExcpIllegalSeshStatus
	 * @throws CumExcpChnlCannotBeReged
	 */
	void regChnl(String chnlName, HashMap<String, ChnlRscIntf> rsces,
			PrestrChnlViewIntf chnlView) throws CumExcpChnlExists,
			CumExcpIllegalSeshStatus;

}