/**
 * 
 */
package jp.happyhacking.cum.aud.seshLyr;

import jp.happyhacking.cum.aud.audLyr.AudChnlViewIntf;
import jp.happyhacking.cum.aud.audLyr.AudSeshViewIntf;
import jp.happyhacking.cum.aud.chnlLyr.DummyChniView;

/**
 * @author happyhacking70@gmail.com
 * 
 */
public class DummySeshView implements AudSeshViewIntf {
	protected DummyChniView chnlView = null;
	protected String chnlNameFailed;
	protected String rslt;
	protected String chnlNameReged;
	protected String chnlTypeReged;
	protected String chnlNameRjcted;
	protected String chnlNameRjctFailed;
	protected String rsltChnlRjctFailed;
	protected boolean clsed = false;
	protected boolean dscned = false;
	protected boolean joined = false;
	protected String joinSeshRslt;

	public String getJoinSeshRslt() {
		return joinSeshRslt;
	}

	public boolean isJoined() {
		return joined;
	}

	public boolean isDscned() {
		return dscned;
	}

	public boolean isClsed() {
		return clsed;
	}

	public String getChnlNameRjctFailed() {
		return chnlNameRjctFailed;
	}

	public String getRsltChnlRjctFailed() {
		return rsltChnlRjctFailed;
	}

	public String getChnlNameRjcted() {
		return chnlNameRjcted;
	}

	public String getChnlTypeReged() {
		return chnlTypeReged;
	}

	public String getChnlNameReged() {
		return chnlNameReged;
	}

	public String getChnlNameFailed() {
		return chnlNameFailed;
	}

	public String getRslt() {
		return rslt;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see jp.happyhacking.cum.aud.audLyr.AudSeshViewIntf#joinSesh()
	 */
	@Override
	public void joinSesh() {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * jp.happyhacking.cum.aud.audLyr.AudSeshViewIntf#seshJoinFailed(java.lang
	 * .String)
	 */
	@Override
	public void joinSseshFailed(String rslt) {
		this.joinSeshRslt = rslt;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see jp.happyhacking.cum.aud.audLyr.AudSeshViewIntf#seshLft()
	 */
	@Override
	public void seshLft() {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * jp.happyhacking.cum.aud.audLyr.AudSeshViewIntf#seshLvFailed(java.lang
	 * .String)
	 */
	@Override
	public void seshLvFailed(String rslt) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see jp.happyhacking.cum.aud.audLyr.AudSeshViewIntf#seshClsed()
	 */
	@Override
	public void seshClsed() {
		this.clsed = true;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see jp.happyhacking.cum.aud.audLyr.AudSeshViewIntf#seshDscned()
	 */
	@Override
	public void seshDscned() {
		this.dscned = true;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * jp.happyhacking.cum.aud.audLyr.AudSeshViewIntf#getChnlView(java.lang.
	 * String)
	 */
	@Override
	public AudChnlViewIntf getChnlView(String chnlType) {
		chnlView = new DummyChniView();
		return chnlView;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * jp.happyhacking.cum.aud.audLyr.AudSeshViewIntf#chnlJoinFailed(java.lang
	 * .String, java.lang.String)
	 */
	@Override
	public void chnlJoinFailed(String chnlName, String rslt) {
		chnlNameFailed = chnlName;
		this.rslt = rslt;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * jp.happyhacking.cum.aud.audLyr.AudSeshViewIntf#chnlRjcted(java.lang.String
	 * )
	 */
	@Override
	public void chnlRjcted(String chnlName) {
		this.chnlNameRjcted = chnlName;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * jp.happyhacking.cum.aud.audLyr.AudSeshViewIntf#chnlRjctFailed(java.lang
	 * .String, java.lang.String)
	 */
	@Override
	public void chnlRjctFailed(String chnlName, String rslt) {
		this.chnlNameRjctFailed = chnlName;
		this.rsltChnlRjctFailed = rslt;

	}

	public DummyChniView getChnlView() {
		return chnlView;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * jp.happyhacking.cum.aud.audLyr.AudSeshViewIntf#chnlRged(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public void chnlReged(String chnlType, String chnlName) {
		this.chnlTypeReged = chnlType;
		this.chnlNameReged = chnlName;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see jp.happyhacking.cum.aud.audLyr.AudSeshViewIntf#seshJoined()
	 */
	@Override
	public void seshJoined() {
		this.joined = true;

	}

}
