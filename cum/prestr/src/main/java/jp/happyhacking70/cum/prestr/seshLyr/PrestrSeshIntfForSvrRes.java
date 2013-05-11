/**
 * 
 */
package jp.happyhacking70.cum.prestr.seshLyr;

import jp.happyhacking70.cum.excp.prestr.CumExcpChnlNotExist;
import jp.happyhacking70.cum.excp.prestr.CumExcpIgnoreSeshStatus;
import jp.happyhacking70.cum.excp.prestr.CumExcpIllegalChnlStatus;
import jp.happyhacking70.cum.excp.prestr.CumExcpIllegalSeshStatus;
import jp.happyhacking70.cum.prestr.prestrLyr.PrestrSeshViewIntf;
import jp.happyhacking70.cum.prestr.seshLyr.PrestrSesh.SeshStatus;

/**
 * @author happyhacking70@gmail.com
 * 
 */
public interface PrestrSeshIntfForSvrRes {

	/**
	 * <UL>
	 * <LI>if session status is not {@link SeshStatus#reging}, just ignore</LI>
	 * <LI>On success, notify session view via
	 * {@link PrestrSeshViewIntf#seshReged()}</LI>
	 * <LI>On failure, notify session view via
	 * {@link PrestrSeshViewIntf#regSeshFailed(String)}</LI>
	 * </UL>
	 * 
	 * @param rslt
	 * @throws CumExcpIgnoreSeshStatus
	 * @throws CumExcpIllegalSeshStatus
	 */
	public abstract void regSeshRslt(String rslt)
			throws CumExcpIllegalSeshStatus, CumExcpIgnoreSeshStatus;

	public abstract void clsSeshRslt(String rslt)
			throws CumExcpIllegalSeshStatus, CumExcpIgnoreSeshStatus;

	public abstract void regChnlRslt(String chnlName, String rslt)
			throws CumExcpIllegalSeshStatus, CumExcpIllegalChnlStatus,
			CumExcpChnlNotExist, CumExcpIgnoreSeshStatus;

	public abstract void clsChnlRslt(String chnlName, String rslt)
			throws CumExcpChnlNotExist, CumExcpIllegalSeshStatus,
			CumExcpIllegalChnlStatus, CumExcpIgnoreSeshStatus;

}