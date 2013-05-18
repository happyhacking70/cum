/**
 * 
 */
package jp.happyhacking.cum.aud.seshLyr;

import jp.happyhacking.cum.aud.excp.CumExcpIgnoreChnlStatus;
import jp.happyhacking.cum.aud.excp.CumExcpIllegalChnlStatus;
import jp.happyhacking.cum.aud.excp.CumExcpIllegalSeshStatus;

/**
 * @author happyhacking70@gmail.com
 * 
 */
public interface AudSeshIntfForSeshView {

	public abstract void joinSesh() throws CumExcpIllegalSeshStatus;

	public abstract void lvSesh() throws CumExcpIllegalSeshStatus,
			CumExcpIllegalChnlStatus, CumExcpIgnoreChnlStatus;

}