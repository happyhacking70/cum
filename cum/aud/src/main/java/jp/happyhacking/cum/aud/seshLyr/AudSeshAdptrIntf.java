/**
 * 
 */
package jp.happyhacking.cum.aud.seshLyr;

import java.util.HashMap;

import jp.happyhacking.cum.aud.excp.CumExcpChnlExists;
import jp.happyhacking.cum.aud.excp.CumExcpChnlNotExist;
import jp.happyhacking.cum.aud.excp.CumExcpIllegalChnlStatuMulti;
import jp.happyhacking.cum.aud.excp.CumExcpIllegalChnlStatus;
import jp.happyhacking.cum.aud.excp.CumExcpIllegalSeshStatus;
import jp.happyhacking70.cum.cmd.rsc.ChnlRscIntf;

/**
 * @author happyhacking70@gmail.com
 * 
 */
public interface AudSeshAdptrIntf {

	public abstract void seshJoined() throws CumExcpIllegalSeshStatus;

	public abstract void seshJoinFailed(String rslt)
			throws CumExcpIllegalSeshStatus;

	public abstract void seshLft() throws CumExcpIllegalSeshStatus;

	public abstract void seshLvFailed(String rslt)
			throws CumExcpIllegalSeshStatus;

	public abstract void seshClsed() throws CumExcpIllegalChnlStatuMulti,
			CumExcpIllegalSeshStatus;

	public abstract void seshDscned() throws CumExcpIllegalChnlStatuMulti,
			CumExcpIllegalSeshStatus;

	public abstract void chnlReged(String chnlType, String chnlName,
			HashMap<String, ChnlRscIntf> rsces) throws CumExcpChnlExists,
			CumExcpIllegalSeshStatus;

	public abstract void chnlJoined(String chnlName)
			throws CumExcpIllegalSeshStatus, CumExcpChnlNotExist,
			CumExcpIllegalChnlStatus;

	public abstract void chnlJoinFailed(String chnlName, String rslt)
			throws CumExcpIllegalSeshStatus, CumExcpChnlNotExist;

	public abstract void chnlRjcted(String chnlName)
			throws CumExcpIllegalSeshStatus, CumExcpChnlNotExist,
			CumExcpIllegalChnlStatus;

	public abstract void chnlRjctFailed(String chnlName, String rslt)
			throws CumExcpIllegalSeshStatus, CumExcpChnlNotExist;

	public abstract void lftChnl(String chnlName)
			throws CumExcpIllegalSeshStatus, CumExcpChnlNotExist,
			CumExcpIllegalChnlStatus;

	public abstract void lvChnlFailed(String chnlName, String rslt)
			throws CumExcpIllegalSeshStatus, CumExcpChnlNotExist,
			CumExcpIllegalChnlStatus;

	public abstract void chnlClsed(String chnlName)
			throws CumExcpIllegalSeshStatus, CumExcpChnlNotExist,
			CumExcpIllegalChnlStatus;

	public abstract void chnlCmdRcved(String chnlName, String actioName,
			HashMap<String, String> params) throws CumExcpIllegalSeshStatus,
			CumExcpChnlNotExist, CumExcpIllegalChnlStatus;
}