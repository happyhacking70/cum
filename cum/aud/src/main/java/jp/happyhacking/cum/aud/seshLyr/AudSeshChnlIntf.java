/**
 * 
 */
package jp.happyhacking.cum.aud.seshLyr;

import jp.happyhacking.cum.aud.excp.CumExcpChnlNotExist;
import jp.happyhacking.cum.aud.excp.CumExcpIllegalSeshStatus;

/**
 * @author happyhacking70@gmail.com
 * 
 */
public interface AudSeshChnlIntf {

	public abstract void lvChnl(String chnlName)
			throws CumExcpIllegalSeshStatus, CumExcpChnlNotExist;

}