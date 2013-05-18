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
	public void seshJoinFailed(String rslt) {
		// TODO Auto-generated method stub

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
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see jp.happyhacking.cum.aud.audLyr.AudSeshViewIntf#seshDscned()
	 */
	@Override
	public void seshDscned() {
		// TODO Auto-generated method stub

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
		System.out.println("chnlView created");
		return chnlView;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * jp.happyhacking.cum.aud.audLyr.AudSeshViewIntf#chnlRged(java.lang.String)
	 */
	@Override
	public void chnlRged(String chnlName) {
		// TODO Auto-generated method stub

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
		// TODO Auto-generated method stub

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
		// TODO Auto-generated method stub

	}

	public DummyChniView getChnlView() {
		return chnlView;
	}

}
