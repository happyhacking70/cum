/**
 * 
 */
package jp.happyhacking70.cum.prestr.seshLyr;

import jp.happyhacking70.cum.excp.prestr.CumExcpChnlNotExist;
import jp.happyhacking70.cum.excp.prestr.CumExcpIllegalChnlStatus;
import jp.happyhacking70.cum.excp.prestr.CumExcpIllegalSeshStatus;
import jp.happyhacking70.cum.excp.prestr.CumExcpSeshDiscned;
import jp.happyhacking70.cum.prestr.chnlLyr.PrestrChnl;
import jp.happyhacking70.cum.prestr.prestrLyr.PrestrChnlViewIntf;

/**
 * @author happyhacking70@gmail.com
 * 
 */
public class DummyChnlView implements PrestrChnlViewIntf {
	protected String audJoined;
	protected String audRjcted;
	protected String audLft;
	protected String audDiscned;
	protected boolean chnlClsed = false;
	protected boolean chnlReged = false;
	protected boolean discned = false;
	protected boolean seshClsing = false;
	protected boolean seshClsed = false;
	protected String rslt;
	protected PrestrChnl chnl;

	public String getAudJoined() {
		return audJoined;
	}

	public String getAudRjcted() {
		return audRjcted;
	}

	public String getAudLft() {
		return audLft;
	}

	public String getAudDiscned() {
		return audDiscned;
	}

	public boolean isChnlClsed() {
		return chnlClsed;
	}

	public boolean isChnlReged() {
		return chnlReged;
	}

	public boolean isDiscned() {
		return discned;
	}

	public boolean isSeshClsing() {
		return seshClsing;
	}

	public boolean isSeshClsed() {
		return seshClsed;
	}

	public String getRslt() {
		return rslt;
	}

	public PrestrChnl getChnl() {
		return chnl;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * jp.happyhacking70.cum.prestr.prestrLyr.PrestrChnlViewIntf#setChnl(jp.
	 * happyhacking70.cum.prestr.chnlLyr.PrestrChnl)
	 */
	@Override
	public void setChnl(PrestrChnl prestrChnl) {
		chnl = prestrChnl;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * jp.happyhacking70.cum.prestr.prestrLyr.PrestrChnlViewIntf#audJoined(java
	 * .lang.String)
	 */
	@Override
	public void audJoined(String audName) {
		audJoined = audName;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * jp.happyhacking70.cum.prestr.prestrLyr.PrestrChnlViewIntf#audRjcted(java
	 * .lang.String)
	 */
	@Override
	public void audRjcted(String audName) {
		audRjcted = audName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * jp.happyhacking70.cum.prestr.prestrLyr.PrestrChnlViewIntf#audLft(java
	 * .lang.String)
	 */
	@Override
	public void audLft(String audName) {
		audLft = audName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * jp.happyhacking70.cum.prestr.prestrLyr.PrestrChnlViewIntf#audDiscned(
	 * java.lang.String)
	 */
	@Override
	public void audDiscned(String audName) {
		audDiscned = audName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * jp.happyhacking70.cum.prestr.prestrLyr.PrestrChnlViewIntf#chnlReged()
	 */
	@Override
	public void chnlReged() {
		chnlReged = true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * jp.happyhacking70.cum.prestr.prestrLyr.PrestrChnlViewIntf#chnlClsed()
	 */
	@Override
	public void chnlClsed() {
		chnlClsed = true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see jp.happyhacking70.cum.prestr.prestrLyr.PrestrChnlViewIntf#discned()
	 */
	@Override
	public void discned() {
		discned = true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * jp.happyhacking70.cum.prestr.prestrLyr.PrestrChnlViewIntf#seshClsing()
	 */
	@Override
	public void seshClsing() {
		seshClsing = true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * jp.happyhacking70.cum.prestr.prestrLyr.PrestrChnlViewIntf#seshClsed()
	 */
	@Override
	public void seshClsed() {
		seshClsed = true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * jp.happyhacking70.cum.prestr.prestrLyr.PrestrChnlViewIntf#clsChnlFailed
	 * (java.lang.String)
	 */
	@Override
	public void clsChnlFailed(String rslt) {
		this.rslt = rslt;

	}

	public void clsChnl() throws CumExcpIllegalChnlStatus, CumExcpChnlNotExist,
			CumExcpIllegalSeshStatus, CumExcpSeshDiscned {
		getChnl().clsChnl();
	}

}
