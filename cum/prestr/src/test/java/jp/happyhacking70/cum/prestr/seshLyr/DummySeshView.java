/**
 * 
 */
package jp.happyhacking70.cum.prestr.seshLyr;

import jp.happyhacking70.cum.prestr.prestrLyr.PrestrSeshViewIntf;

/**
 * @author happyhacking70@gmail.com
 * 
 */
public class DummySeshView implements PrestrSeshViewIntf {
	protected boolean isReged = false;
	protected String regSeshFailedReason = null;
	protected boolean isSeshClsed = false;
	protected String regChnlFailedReason = null;
	protected String clsSeshFailedReason = null;
	protected String audJoinedName = null;
	protected String audDscnedName = null;
	protected String audLftName = null;

	public boolean isReged() {
		return isReged;
	}

	public String getRegSeshFailedReason() {
		return regSeshFailedReason;
	}

	public boolean isSeshClsed() {
		return isSeshClsed;
	}

	public String getRegChnlFailedReason() {
		return regChnlFailedReason;
	}

	public String getClsSeshFailedReason() {
		return clsSeshFailedReason;
	}

	public String getAudJoinedName() {
		return audJoinedName;
	}

	public String getAudDscnedName() {
		return audDscnedName;
	}

	public String getAudLftName() {
		return audLftName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * jp.happyhacking70.cum.prestr.prestrLyr.PrestrSeshViewIntf#seshReged()
	 */
	@Override
	public void seshReged() {
		isReged = true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * jp.happyhacking70.cum.prestr.prestrLyr.PrestrSeshViewIntf#regSeshFailed
	 * (java.lang.String)
	 */
	@Override
	public void regSeshFailed(String reason) {
		regSeshFailedReason = reason;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * jp.happyhacking70.cum.prestr.prestrLyr.PrestrSeshViewIntf#seshClsed()
	 */
	@Override
	public void seshClsed() {
		isSeshClsed = true;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * jp.happyhacking70.cum.prestr.prestrLyr.PrestrSeshViewIntf#clsSeshFailed
	 * (java.lang.String)
	 */
	@Override
	public void clsSeshFailed(String rslt) {
		clsSeshFailedReason = rslt;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * jp.happyhacking70.cum.prestr.prestrLyr.PrestrSeshViewIntf#audJoined(java
	 * .lang.String)
	 */
	@Override
	public void audJoined(String audName) {
		audJoinedName = audName;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * jp.happyhacking70.cum.prestr.prestrLyr.PrestrSeshViewIntf#regChnlFailed
	 * (java.lang.String)
	 */
	@Override
	public void regChnlFailed(String rslt) {
		regChnlFailedReason = rslt;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * jp.happyhacking70.cum.prestr.prestrLyr.PrestrSeshViewIntf#audDscned(java
	 * .lang.String)
	 */
	@Override
	public void audDscned(String audName) {
		audDscnedName = audName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * jp.happyhacking70.cum.prestr.prestrLyr.PrestrSeshViewIntf#audLft(java
	 * .lang.String)
	 */
	@Override
	public void audLft(String audName) {
		audLftName = audName;

	}

}
