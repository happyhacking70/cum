/**
 * 
 */
package jp.happyhacking.cum.aud.chnlLyr;

import java.util.HashMap;

import jp.happyhacking.cum.aud.seshLyr.AudSeshAdptrIntf;
import jp.happyhacking.cum.aud.seshLyr.AudSeshChnlIntf;
import jp.happyhacking.cum.aud.seshLyr.AudSeshIntfForSeshView;
import jp.happyhacking70.cum.cmd.rsc.ChnlRscIntf;

/**
 * @author happyhacking70@gmail.com
 * 
 */
public class DummyAudSesh implements AudSeshAdptrIntf, AudSeshChnlIntf,
		AudSeshIntfForSeshView {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * jp.happyhacking.cum.aud.seshLyr.AudSeshIntf#chnlReged(java.lang.String,
	 * java.util.HashMap)
	 */
	@Override
	public void chnlReged(String chnlType, String chnlName,
			HashMap<String, ChnlRscIntf> rsces) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * jp.happyhacking.cum.aud.seshLyr.AudSeshIntf#joinChnl(java.lang.String)
	 */
	@Override
	public void joinChnl(String chnlName) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * jp.happyhacking.cum.aud.seshLyr.AudSeshIntf#chnlJoined(java.lang.String)
	 */
	@Override
	public void chnlJoined(String chnlName) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see jp.happyhacking.cum.aud.seshLyr.AudSeshIntf#lvChnl(java.lang.String)
	 */
	@Override
	public void lvChnl(String chnlName) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see jp.happyhacking.cum.aud.seshLyr.AudSeshIntf#join()
	 */
	@Override
	public void joinSesh() {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see jp.happyhacking.cum.aud.seshLyr.AudSeshIntf#joined()
	 */
	@Override
	public void seshJoined() {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * jp.happyhacking.cum.aud.seshLyr.AudSeshIntf#joinFailed(java.lang.String)
	 */
	@Override
	public void seshJoinFailed(String rslt) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see jp.happyhacking.cum.aud.seshLyr.AudSeshIntf#lv()
	 */
	@Override
	public void lvSesh() {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see jp.happyhacking.cum.aud.seshLyr.AudSeshIntf#lft()
	 */
	@Override
	public void seshLft() {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * jp.happyhacking.cum.aud.seshLyr.AudSeshIntf#lvFailed(java.lang.String)
	 */
	@Override
	public void seshLvFailed(String rslt) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see jp.happyhacking.cum.aud.seshLyr.AudSeshIntf#clsed()
	 */
	@Override
	public void seshClsed() {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see jp.happyhacking.cum.aud.seshLyr.AudSeshIntf#dscned()
	 */
	@Override
	public void seshDscned() {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * jp.happyhacking.cum.aud.seshLyr.AudSeshChnlIntf#chnlJoinFailed(java.lang
	 * .String, java.lang.String)
	 */
	@Override
	public void chnlJoinFailed(String chnlName, String rslt) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * jp.happyhacking.cum.aud.seshLyr.AudSeshChnlIntf#rjctChnl(java.lang.String
	 * )
	 */
	@Override
	public void rjctChnl(String chnlName) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * jp.happyhacking.cum.aud.seshLyr.AudSeshChnlIntf#chnlRjcted(java.lang.
	 * String)
	 */
	@Override
	public void chnlRjcted(String chnlName) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * jp.happyhacking.cum.aud.seshLyr.AudSeshChnlIntf#chnlRjctFailed(java.lang
	 * .String, java.lang.String)
	 */
	@Override
	public void chnlRjctFailed(String chnlName, String rslt) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * jp.happyhacking.cum.aud.seshLyr.AudSeshChnlIntf#lftChnl(java.lang.String)
	 */
	@Override
	public void chnlLft(String chnlName) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * jp.happyhacking.cum.aud.seshLyr.AudSeshChnlIntf#lvChnlFailed(java.lang
	 * .String, java.lang.String)
	 */
	@Override
	public void lvChnlFailed(String chnlName, String rslt) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * jp.happyhacking.cum.aud.seshLyr.AudSeshChnlIntf#chnlClsed(java.lang.String
	 * )
	 */
	@Override
	public void chnlClsed(String chnlName) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * jp.happyhacking.cum.aud.seshLyr.AudSeshChnlIntf#chnlCmdRcved(java.lang
	 * .String, java.lang.String, java.util.HashMap)
	 */
	@Override
	public void chnlCmdRcved(String chnlName, String actioName,
			HashMap<String, String> params) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * jp.happyhacking.cum.aud.seshLyr.AudSeshChnlIntf#fetchRsc(java.lang.String
	 * , java.lang.String, java.lang.String)
	 */
	@Override
	public ChnlRscIntf fetchRsc(String chnlType, String chnlName, String name) {
		// TODO Auto-generated method stub
		return null;
	}

}
