/**
 * 
 */
package jp.happyhacking70.cum.prestr.chnlLyr;

import java.util.HashMap;

import jp.happyhacking70.cum.cmd.rsc.ChnlRscIntf;
import jp.happyhacking70.cum.excp.prestr.CumExcpAudExist;
import jp.happyhacking70.cum.excp.prestr.CumExcpChnlNotExist;
import jp.happyhacking70.cum.excp.prestr.CumExcpIllegalChnlStatus;
import jp.happyhacking70.cum.excp.prestr.CumExcpIllegalSeshStatus;
import jp.happyhacking70.cum.excp.prestr.CumExcpSeshDiscned;
import jp.happyhacking70.cum.prestr.prestrLyr.PrestrChnlViewIntf;
import jp.happyhacking70.cum.prestr.seshLyr.PrestrSeshIntfForChnlView;

/**
 * @author happyhacking70@gmail.com
 * 
 */
public class PrestrChnl implements PrestrChnlIntfFromChnlView,
		PrestrChnlNotfyIntf {

	/**
	 * Statuses of channel
	 * 
	 * @author happyhacking70@gmail.com
	 * 
	 */
	public enum ChnlStatus {
		/** registering (initial status */
		reging,
		/** registered */
		reged,
		/** under closing */
		clsing,
		/** closed */
		clsed,
		/** session disconnected */
		discned
	}

	/** Name of channel */
	protected String chnlName;
	/** Channel Resources */
	protected HashMap<String, ChnlRscIntf> rsces;
	/** Current status of channel */
	protected ChnlStatus chnlStatus = ChnlStatus.reging;
	/** associated channel view */
	protected PrestrChnlViewIntf chnlView;
	/** associated session */
	protected PrestrSeshIntfForChnlView sesh;
	/** audiences */
	protected HashMap<String, String> auds = new HashMap<String, String>();

	/**
	 * @param chnlName
	 *            Name of Channel
	 * @param rsces
	 *            Channel Resources
	 * @param chnlView
	 *            View for this channel
	 * @param sesh
	 *            Session this channel belongs to
	 */
	public PrestrChnl(String chnlName, HashMap<String, ChnlRscIntf> rsces,
			PrestrChnlViewIntf chnlView, PrestrSeshIntfForChnlView sesh) {
		super();
		this.chnlName = chnlName;
		this.rsces = rsces;
		this.chnlView = chnlView;
		this.sesh = sesh;
		chnlView.setChnl(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * jp.happyhacking70.cum.prestr.chnlLyr.PrestrChnlIntfFromChnlView#clsChnl()
	 */
	@Override
	public void clsChnl() throws CumExcpIllegalChnlStatus, CumExcpChnlNotExist,
			CumExcpIllegalSeshStatus, CumExcpSeshDiscned {
		if (chnlStatus == ChnlStatus.reged || chnlStatus == ChnlStatus.reging) {
			chnlStatus = PrestrChnl.ChnlStatus.clsing;
			sesh.clsChnl(chnlName);
		} else {
			throw new CumExcpIllegalChnlStatus(chnlName, chnlStatus.name());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * jp.happyhacking70.cum3.prestr.chnlLyr.PrestrChnlIntfFromChnlView#sendChnlCmd
	 * (java.lang.String, java.util.HashMap)
	 */
	@Override
	public void sendChnlCmd(String actionName, HashMap<String, String> params)
			throws CumExcpIllegalChnlStatus, CumExcpChnlNotExist,
			CumExcpIllegalSeshStatus, CumExcpSeshDiscned {
		if (chnlStatus == ChnlStatus.reged && chnlStatus != ChnlStatus.discned) {

			sesh.sendChnlCmd(chnlName, actionName, params);
		} else {
			throw new CumExcpIllegalChnlStatus(chnlName, chnlStatus.name());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * jp.happyhacking70.cum3.prestr.chnlLyr.PrestrChnlNotfyIntf#audJoinedChnl
	 * (java.lang.String)
	 */
	@Override
	public void audJoinedChnl(String audName) throws CumExcpAudExist,
			CumExcpIllegalChnlStatus {

		if (chnlStatus == ChnlStatus.reging || chnlStatus == ChnlStatus.reged) {
			if (auds.containsKey(audName) == false) {
				auds.put(audName, audName);
				chnlView.audJoined(audName);
			} else {
				throw new CumExcpAudExist(chnlName, audName);
			}
		} else {
			throw new CumExcpIllegalChnlStatus(chnlName, chnlStatus.name());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * jp.happyhacking70.cum3.prestr.chnlLyr.PrestrChnlNotfyIntf#audRjctedChnl
	 * (java.lang.String)
	 */
	@Override
	public void audRjctedChnl(String audName) throws CumExcpIllegalChnlStatus {
		if (chnlStatus == ChnlStatus.reging || chnlStatus == ChnlStatus.reged) {
			chnlView.audRjcted(audName);
		} else {
			throw new CumExcpIllegalChnlStatus(chnlName, chnlStatus.name());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * jp.happyhacking70.cum3.prestr.chnlLyr.PrestrChnlNotfyIntf#audLftChnl(
	 * java.lang.String)
	 */
	@Override
	public void audLftChnl(String audName) throws CumExcpIllegalChnlStatus {

		if (chnlStatus == ChnlStatus.reging || chnlStatus == ChnlStatus.reged) {
			if (auds.containsKey(audName)) {
				chnlView.audLft(audName);
			}
		} else {
			throw new CumExcpIllegalChnlStatus(chnlName, chnlStatus.name());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * jp.happyhacking70.cum3.prestr.chnlLyr.PrestrChnlNotfyIntf#audDiscned(
	 * java.lang.String)
	 */
	@Override
	public void audDiscned(String audName) throws CumExcpIllegalChnlStatus {
		if (chnlStatus == ChnlStatus.reging || chnlStatus == ChnlStatus.reged) {
			if (auds.containsKey(audName)) {
				chnlView.audDiscned(audName);
			}
		} else {
			throw new CumExcpIllegalChnlStatus(chnlName, chnlStatus.name());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * jp.happyhacking70.cum3.prestr.chnlLyr.PrestrChnlNotfyIntf#chnlClsed()
	 */
	@Override
	public void chnlReged() throws CumExcpIllegalChnlStatus {

		if (chnlStatus == ChnlStatus.reging) {
			chnlStatus = ChnlStatus.reged;
			chnlView.chnlReged();
		} else {
			throw new CumExcpIllegalChnlStatus(chnlName, chnlStatus.name());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * jp.happyhacking70.cum3.prestr.chnlLyr.PrestrChnlNotfyIntf#chnlClsed()
	 */
	@Override
	public void chnlClsed() throws CumExcpIllegalChnlStatus {
		if (chnlStatus == ChnlStatus.clsing) {
			chnlStatus = ChnlStatus.clsed;
			chnlView.chnlClsed();
		} else {
			throw new CumExcpIllegalChnlStatus(chnlName, chnlStatus.name());
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see jp.happyhacking70.cum3.prestr.chnlLyr.PrestrChnlNotfyIntf#discnded()
	 */
	@Override
	public void discnded() {
		chnlStatus = ChnlStatus.discned;
		chnlView.discned();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * jp.happyhacking70.cum3.prestr.chnlLyr.PrestrChnlNotfyIntf#seshClosing()
	 */
	@Override
	public void seshClsing() throws CumExcpIllegalChnlStatus {
		if (chnlStatus == ChnlStatus.reging || chnlStatus == ChnlStatus.reged) {
			chnlStatus = ChnlStatus.clsed;
			chnlView.seshClsing();
		} else {
			throw new CumExcpIllegalChnlStatus(chnlName, chnlStatus.name());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * jp.happyhacking70.cum3.prestr.chnlLyr.PrestrChnlNotfyIntf#clsChnlFailed
	 * (java.lang.String)
	 */
	@Override
	public void clsChnlFailed(String rslt) throws CumExcpIllegalChnlStatus {
		if (chnlStatus != ChnlStatus.clsing) {
			throw new CumExcpIllegalChnlStatus(chnlName, chnlStatus.name());
		}
		chnlStatus = ChnlStatus.clsed;
		chnlView.clsChnlFailed(rslt);

	}
}
