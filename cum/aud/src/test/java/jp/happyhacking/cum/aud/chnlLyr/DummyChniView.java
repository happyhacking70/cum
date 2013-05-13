/**
 * 
 */
package jp.happyhacking.cum.aud.chnlLyr;

import java.util.HashMap;

import jp.happyhacking.cum.aud.audLyr.AudChnlViewIntf;

/**
 * @author happyhacking70@gmail.com
 * 
 */
public class DummyChniView implements AudChnlViewIntf {
	protected boolean joined = false;
	protected boolean closed = false;
	protected boolean seshClsed = false;
	protected String actionName = null;
	protected HashMap<String, String> params = null;
	protected boolean chnlLft;
	protected String rslt;
	protected boolean dscned;

	public boolean isJoined() {
		return joined;
	}

	public boolean isClosed() {
		return closed;
	}

	public boolean isSeshClsed() {
		return seshClsed;
	}

	public String getActionName() {
		return actionName;
	}

	public HashMap<String, String> getParams() {
		return params;
	}

	public boolean isChnlLft() {
		return chnlLft;
	}

	public String getRslt() {
		return rslt;
	}

	public boolean isDscned() {
		return dscned;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see jp.happyhacking.cum.aud.audLyr.AudChnlViewIntf#chnlJoined()
	 */
	@Override
	public void chnlJoined() {
		joined = true;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see jp.happyhacking.cum.aud.audLyr.AudChnlViewIntf#chnlClsed()
	 */
	@Override
	public void chnlClsed() {
		closed = true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see jp.happyhacking.cum.aud.audLyr.AudChnlViewIntf#seshClsed()
	 */
	@Override
	public void seshClsed() {
		seshClsed = true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * jp.happyhacking.cum.aud.audLyr.AudChnlViewIntf#chnlCmdRcved(java.lang
	 * .String, java.util.HashMap)
	 */
	@Override
	public void chnlCmdRcved(String actionName, HashMap<String, String> params) {
		this.actionName = actionName;
		this.params = params;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see jp.happyhacking.cum.aud.audLyr.AudChnlViewIntf#chnlLeft()
	 */
	@Override
	public void chnlLeft() {
		chnlLft = true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * jp.happyhacking.cum.aud.audLyr.AudChnlViewIntf#chnlLvFailed(java.lang
	 * .String)
	 */
	@Override
	public void chnlLvFailed(String rslt) {
		this.rslt = rslt;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see jp.happyhacking.cum.aud.audLyr.AudChnlViewIntf#chnlDscned()
	 */
	@Override
	public void chnlDscned() {
		dscned = true;
	}

}
