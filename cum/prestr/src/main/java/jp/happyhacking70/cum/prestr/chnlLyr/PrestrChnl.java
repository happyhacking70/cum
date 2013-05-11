/**
 * 
 */
package jp.happyhacking70.cum.prestr.chnlLyr;

import java.util.HashMap;

import jp.happyhacking70.cum.cmd.rsc.ChnlRscIntf;
import jp.happyhacking70.cum.excp.prestr.CumExcpAudExists;
import jp.happyhacking70.cum.excp.prestr.CumExcpAudNotExist;
import jp.happyhacking70.cum.excp.prestr.CumExcpChnlNotExist;
import jp.happyhacking70.cum.excp.prestr.CumExcpIgnoreChnlStatus;
import jp.happyhacking70.cum.excp.prestr.CumExcpIllegalChnlStatus;
import jp.happyhacking70.cum.excp.prestr.CumExcpIllegalSeshStatus;
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
		dscned
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
			CumExcpIllegalSeshStatus {

		clsChnlCheckStates();
		chnlStatus = PrestrChnl.ChnlStatus.clsing;
		sesh.clsChnl(chnlName);
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
			CumExcpIllegalSeshStatus {

		sendChnlCmdCheckStates();
		sesh.sendChnlCmd(chnlName, actionName, params);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * jp.happyhacking70.cum3.prestr.chnlLyr.PrestrChnlNotfyIntf#audJoinedChnl
	 * (java.lang.String)
	 */
	@Override
	public void audJoinedChnl(String audName) throws CumExcpIllegalChnlStatus,
			CumExcpAudExists {

		try {
			audJoinedChnlCheckStates();
			if (auds.containsKey(audName) == false) {
				auds.put(audName, audName);
				chnlView.audJoined(audName);
			} else {
				throw new CumExcpAudExists(chnlName, audName);
			}
		} catch (CumExcpIgnoreChnlStatus e) {
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
	public void audRjctedChnl(String audName) throws CumExcpIllegalChnlStatus,
			CumExcpAudNotExist {

		try {
			audRjctedChnlCheckStates();
			if (!auds.containsKey(audName)) {
				throw new CumExcpAudNotExist(chnlName, audName);
			}
			chnlView.audRjcted(audName);
		} catch (CumExcpIgnoreChnlStatus e) {
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
	public void audLftChnl(String audName) throws CumExcpIllegalChnlStatus,
			CumExcpAudNotExist {
		try {
			audLftChnlCheckStates();
			if (!auds.containsKey(audName)) {
				throw new CumExcpAudNotExist(chnlName, audName);
			}
			chnlView.audLft(audName);
		} catch (CumExcpIgnoreChnlStatus e) {
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
	public void audDiscned(String audName) throws CumExcpIllegalChnlStatus,
			CumExcpAudNotExist {

		try {
			audDiscnedCheckStates();
			if (!auds.containsKey(audName)) {
				throw new CumExcpAudNotExist(chnlName, audName);
			}
			chnlView.audDiscned(audName);
		} catch (CumExcpIgnoreChnlStatus e) {

		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see jp.happyhacking70.cum.prestr.chnlLyr.PrestrChnlNotfyIntf#chnlReged()
	 */
	@Override
	public void chnlReged() throws CumExcpIllegalChnlStatus {
		try {
			chnlRegedCheckStates();
			chnlStatus = ChnlStatus.reged;
			chnlView.chnlReged();
		} catch (CumExcpIgnoreChnlStatus e) {
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
		try {
			chnlClsedCheckStates();
			chnlStatus = ChnlStatus.clsed;
			chnlView.chnlClsed();
		} catch (CumExcpIgnoreChnlStatus e) {
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see jp.happyhacking70.cum3.prestr.chnlLyr.PrestrChnlNotfyIntf#discnded()
	 */
	@Override
	public void discnded() throws CumExcpIllegalChnlStatus {
		discndedCheckStates();
		chnlStatus = ChnlStatus.dscned;
		chnlView.discned();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * jp.happyhacking70.cum3.prestr.chnlLyr.PrestrChnlNotfyIntf#seshClosing()
	 */
	@Override
	public void seshClsing() {
		try {
			seshClsingCheckStates();
			chnlStatus = ChnlStatus.clsed;
			chnlView.seshClsing();
		} catch (CumExcpIgnoreChnlStatus e) {
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
		try {
			clsChnlFailedCheckStates();
			chnlStatus = ChnlStatus.clsed;
			chnlView.clsChnlFailed(rslt);
		} catch (CumExcpIgnoreChnlStatus e) {
		}

	}

	protected void clsChnlCheckStates() throws CumExcpIllegalChnlStatus {
		if (chnlStatus == ChnlStatus.reging) {
			throw new CumExcpIllegalChnlStatus(chnlName, chnlStatus.name());
		} else if (chnlStatus == ChnlStatus.reged) {
		} else if (chnlStatus == ChnlStatus.clsing) {
			throw new CumExcpIllegalChnlStatus(chnlName, chnlStatus.name());
		} else if (chnlStatus == ChnlStatus.clsed) {
			throw new CumExcpIllegalChnlStatus(chnlName, chnlStatus.name());
		} else if (chnlStatus == ChnlStatus.dscned) {
			throw new CumExcpIllegalChnlStatus(chnlName, chnlStatus.name());
		}
	}

	protected void sendChnlCmdCheckStates() throws CumExcpIllegalChnlStatus {
		if (chnlStatus == ChnlStatus.reging) {
			throw new CumExcpIllegalChnlStatus(chnlName, chnlStatus.name());
		} else if (chnlStatus == ChnlStatus.reged) {
		} else if (chnlStatus == ChnlStatus.clsing) {
			throw new CumExcpIllegalChnlStatus(chnlName, chnlStatus.name());
		} else if (chnlStatus == ChnlStatus.clsed) {
			throw new CumExcpIllegalChnlStatus(chnlName, chnlStatus.name());
		} else if (chnlStatus == ChnlStatus.dscned) {
			throw new CumExcpIllegalChnlStatus(chnlName, chnlStatus.name());
		}
	}

	protected void audJoinedChnlCheckStates() throws CumExcpIgnoreChnlStatus,
			CumExcpIllegalChnlStatus {
		if (chnlStatus == ChnlStatus.reging) {
		} else if (chnlStatus == ChnlStatus.reged) {
		} else if (chnlStatus == ChnlStatus.clsing) {
			throw new CumExcpIgnoreChnlStatus(chnlName, chnlStatus.name());
		} else if (chnlStatus == ChnlStatus.clsed) {
			throw new CumExcpIgnoreChnlStatus(chnlName, chnlStatus.name());
		} else if (chnlStatus == ChnlStatus.dscned) {
			throw new CumExcpIgnoreChnlStatus(chnlName, chnlStatus.name());
		}
	}

	protected void audRjctedChnlCheckStates() throws CumExcpIgnoreChnlStatus,
			CumExcpIllegalChnlStatus {
		if (chnlStatus == ChnlStatus.reging) {
		} else if (chnlStatus == ChnlStatus.reged) {
		} else if (chnlStatus == ChnlStatus.clsing) {
			throw new CumExcpIgnoreChnlStatus(chnlName, chnlStatus.name());
		} else if (chnlStatus == ChnlStatus.clsed) {
			throw new CumExcpIgnoreChnlStatus(chnlName, chnlStatus.name());
		} else if (chnlStatus == ChnlStatus.dscned) {
			throw new CumExcpIgnoreChnlStatus(chnlName, chnlStatus.name());
		}
	}

	protected void audLftChnlCheckStates() throws CumExcpIgnoreChnlStatus,
			CumExcpIllegalChnlStatus {
		if (chnlStatus == ChnlStatus.reging) {
		} else if (chnlStatus == ChnlStatus.reged) {
		} else if (chnlStatus == ChnlStatus.clsing) {
			throw new CumExcpIgnoreChnlStatus(chnlName, chnlStatus.name());
		} else if (chnlStatus == ChnlStatus.clsed) {
			throw new CumExcpIgnoreChnlStatus(chnlName, chnlStatus.name());
		} else if (chnlStatus == ChnlStatus.dscned) {
			throw new CumExcpIgnoreChnlStatus(chnlName, chnlStatus.name());
		}
	}

	protected void audDiscnedCheckStates() throws CumExcpIgnoreChnlStatus,
			CumExcpIllegalChnlStatus {
		if (chnlStatus == ChnlStatus.reging) {
		} else if (chnlStatus == ChnlStatus.reged) {
		} else if (chnlStatus == ChnlStatus.clsing) {
			throw new CumExcpIgnoreChnlStatus(chnlName, chnlStatus.name());
		} else if (chnlStatus == ChnlStatus.clsed) {
			throw new CumExcpIgnoreChnlStatus(chnlName, chnlStatus.name());
		} else if (chnlStatus == ChnlStatus.dscned) {
			throw new CumExcpIgnoreChnlStatus(chnlName, chnlStatus.name());
		}
	}

	protected void chnlClsedCheckStates() throws CumExcpIllegalChnlStatus,
			CumExcpIgnoreChnlStatus {
		if (chnlStatus == ChnlStatus.reging) {
			throw new CumExcpIllegalChnlStatus(chnlName, chnlStatus.name());
		} else if (chnlStatus == ChnlStatus.reged) {
			throw new CumExcpIllegalChnlStatus(chnlName, chnlStatus.name());
		} else if (chnlStatus == ChnlStatus.clsing) {
		} else if (chnlStatus == ChnlStatus.clsed) {
			throw new CumExcpIllegalChnlStatus(chnlName, chnlStatus.name());
		} else if (chnlStatus == ChnlStatus.dscned) {
			throw new CumExcpIgnoreChnlStatus(chnlName, chnlStatus.name());
		}
	}

	protected void chnlRegedCheckStates() throws CumExcpIgnoreChnlStatus,
			CumExcpIllegalChnlStatus {
		if (chnlStatus == ChnlStatus.reging) {
		} else if (chnlStatus == ChnlStatus.reged) {
			throw new CumExcpIllegalChnlStatus(chnlName, chnlStatus.name());
		} else if (chnlStatus == ChnlStatus.clsing) {
			throw new CumExcpIllegalChnlStatus(chnlName, chnlStatus.name());
		} else if (chnlStatus == ChnlStatus.clsed) {
			throw new CumExcpIllegalChnlStatus(chnlName, chnlStatus.name());
		} else if (chnlStatus == ChnlStatus.dscned) {
			throw new CumExcpIgnoreChnlStatus(chnlName, chnlStatus.name());
		}
	}

	protected void discndedCheckStates() throws CumExcpIllegalChnlStatus {
		if (chnlStatus == ChnlStatus.reging) {
		} else if (chnlStatus == ChnlStatus.reged) {
		} else if (chnlStatus == ChnlStatus.clsing) {
		} else if (chnlStatus == ChnlStatus.clsed) {
		} else if (chnlStatus == ChnlStatus.dscned) {
			throw new CumExcpIllegalChnlStatus(chnlName, chnlStatus.name());
		}
	}

	protected void seshClsingCheckStates() throws CumExcpIgnoreChnlStatus {
		if (chnlStatus == ChnlStatus.reging) {
		} else if (chnlStatus == ChnlStatus.reged) {
		} else if (chnlStatus == ChnlStatus.clsing) {
		} else if (chnlStatus == ChnlStatus.clsed) {
		} else if (chnlStatus == ChnlStatus.dscned) {
			throw new CumExcpIgnoreChnlStatus(chnlName, chnlStatus.name());
		}
	}

	protected void clsChnlFailedCheckStates() throws CumExcpIgnoreChnlStatus,
			CumExcpIllegalChnlStatus {
		if (chnlStatus == ChnlStatus.reging) {
		} else if (chnlStatus == ChnlStatus.reged) {
			throw new CumExcpIllegalChnlStatus(chnlName, chnlStatus.name());
		} else if (chnlStatus == ChnlStatus.clsing) {
			throw new CumExcpIllegalChnlStatus(chnlName, chnlStatus.name());
		} else if (chnlStatus == ChnlStatus.clsed) {
			throw new CumExcpIllegalChnlStatus(chnlName, chnlStatus.name());
		} else if (chnlStatus == ChnlStatus.dscned) {
			throw new CumExcpIgnoreChnlStatus(chnlName, chnlStatus.name());
		}
	}

}
