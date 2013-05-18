/**
 * 
 */
package jp.happyhacking.cum.aud.seshLyr;

import jp.happyhacking.cum.aud.excp.CumExcpChnlNotExist;
import jp.happyhacking.cum.aud.excp.CumExcpIllegalChnlStatus;
import jp.happyhacking.cum.aud.excp.CumExcpIllegalSeshStatus;

/**
 * @author happyhacking70@gmail.com
 * 
 */
public interface AudSeshChnlIntf {

	public abstract void joinChnl(String chnlName)
			throws CumExcpIllegalSeshStatus, CumExcpChnlNotExist,
			CumExcpIllegalChnlStatus;

	public abstract void rjctChnl(String chnlName)
			throws CumExcpIllegalSeshStatus, CumExcpChnlNotExist,
			CumExcpIllegalChnlStatus;

	public abstract void lvChnl(String chnlName)
			throws CumExcpIllegalSeshStatus, CumExcpChnlNotExist;

}