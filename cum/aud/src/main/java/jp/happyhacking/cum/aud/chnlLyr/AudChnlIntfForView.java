/**
 * 
 */
package jp.happyhacking.cum.aud.chnlLyr;

import jp.happyhacking.cum.aud.excp.CumExcpChnlNotExist;
import jp.happyhacking.cum.aud.excp.CumExcpIllegalChnlStatus;
import jp.happyhacking.cum.aud.excp.CumExcpIllegalSeshStatus;

/**
 * @author happyhacking70@gmail.com
 * 
 */
public interface AudChnlIntfForView {

	void lvChnl() throws CumExcpIllegalChnlStatus, CumExcpIllegalSeshStatus,
			CumExcpChnlNotExist;

}
