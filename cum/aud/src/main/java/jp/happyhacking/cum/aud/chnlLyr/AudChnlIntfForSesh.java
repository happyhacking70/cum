/**
 * 
 */
package jp.happyhacking.cum.aud.chnlLyr;

import java.util.HashMap;

import jp.happyhacking.cum.aud.excp.CumExcpIllegalChnlStatus;

/**
 * @author happyhacking70@gmail.com
 * 
 */
public interface AudChnlIntfForSesh {

	void chnlClsed() throws CumExcpIllegalChnlStatus;

	void seshClsed() throws CumExcpIllegalChnlStatus;

	void chnlJoining() throws CumExcpIllegalChnlStatus;

	void chnlJoined() throws CumExcpIllegalChnlStatus;

	void chnlJoinedFailed(String rslt) throws CumExcpIllegalChnlStatus;

	void chnlRjcting() throws CumExcpIllegalChnlStatus;

	void chnlRjcted() throws CumExcpIllegalChnlStatus;

	void chnlRjctFailed(String rslt) throws CumExcpIllegalChnlStatus;

	void chnlLft() throws CumExcpIllegalChnlStatus;

	void chnlLvFailed(String rslt) throws CumExcpIllegalChnlStatus;

	void chnlCmdRcved(String actionName, HashMap<String, String> params)
			throws CumExcpIllegalChnlStatus;

	void chnlDscned() throws CumExcpIllegalChnlStatus;

	void seshLving() throws CumExcpIllegalChnlStatus;

	String getChnlType();

}
