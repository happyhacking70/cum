/**
 * 
 */
package jp.happyhacking70.cum.prestr.chnlLyr;

import jp.happyhacking70.cum.prestr.prestrLyr.PrestrChnlViewIntf;

/**
 * @author happyhacking70@gmail.com
 * 
 */
public class DummyPrestrChnlViewIntf implements PrestrChnlViewIntf {
	protected PrestrChnl prestrChnl;
	protected String audJoined;
	protected String audRjcted;
	protected String audLft;
	protected String audDiscned;
	protected boolean isReged = false;
	protected boolean isClsed = false;
	protected boolean isDiscned = false;
	protected boolean isSeshClsing = false;
	protected boolean isSeshClsed = false;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * jp.happyhacking70.cum.prestr.prestrLyr.PrestrChnlViewIntf#setChnl(jp.
	 * happyhacking70.cum.prestr.chnlLyr.PrestrChnl)
	 */
	@Override
	public void setChnl(PrestrChnl prestrChnl) {
		this.prestrChnl = prestrChnl;
	}

	public PrestrChnl getPrestrChnl() {
		return prestrChnl;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * jp.happyhacking70.cum3.prestr.prestrLyr.PrestrChnlViewIntf#audJoined(
	 * java.lang.String)
	 */
	@Override
	public void audJoined(String audName) {
		this.audJoined = audName;
	}

	public String getAudJoined() {
		return audJoined;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * jp.happyhacking70.cum3.prestr.prestrLyr.PrestrChnlViewIntf#audRjcted(
	 * java.lang.String)
	 */
	@Override
	public void audRjcted(String audName) {
		this.audRjcted = audName;
	}

	public String getAudRjcted() {
		return audRjcted;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * jp.happyhacking70.cum3.prestr.prestrLyr.PrestrChnlViewIntf#audLft(java
	 * .lang.String)
	 */
	@Override
	public void audLft(String audName) {
		this.audLft = audName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * jp.happyhacking70.cum3.prestr.prestrLyr.PrestrChnlViewIntf#audDiscned
	 * (java.lang.String)
	 */
	@Override
	public void audDiscned(String audName) {
		this.audDiscned = audName;
	}

	public String getAudLft() {
		return audLft;
	}

	public String getAudDiscned() {
		return audDiscned;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * jp.happyhacking70.cum3.prestr.prestrLyr.PrestrChnlViewIntf#chnlReged()
	 */
	@Override
	public void chnlReged() {
		isReged = true;
	}

	public boolean isReged() {
		return isReged;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * jp.happyhacking70.cum3.prestr.prestrLyr.PrestrChnlViewIntf#chnlClsed()
	 */
	@Override
	public void chnlClsed() {
		isClsed = true;
	}

	public boolean isClsed() {
		return isClsed;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see jp.happyhacking70.cum3.prestr.prestrLyr.PrestrChnlViewIntf#discned()
	 */
	@Override
	public void discned() {
		isDiscned = true;
	}

	public boolean isDiscned() {
		return isDiscned;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * jp.happyhacking70.cum3.prestr.prestrLyr.PrestrChnlViewIntf#seshClsing()
	 */
	@Override
	public void seshClsing() {
		isSeshClsing = true;
	}

	public boolean isSeshClsing() {
		return isSeshClsing;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * jp.happyhacking70.cum3.prestr.prestrLyr.PrestrChnlViewIntf#seshClsed()
	 */
	@Override
	public void seshClsed() {
		isSeshClsed = true;

	}

	public boolean isSeshClsed() {
		return isSeshClsed;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * jp.happyhacking70.cum3.prestr.prestrLyr.PrestrChnlViewIntf#clsChnlFailed
	 * (java.lang.String)
	 */
	@Override
	public void clsChnlFailed(String rslt) {
		// TODO Auto-generated method stub

	}

}
