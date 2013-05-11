/**
 * 
 */
package jp.happyhacking70.cum.prestr.seshLyr;

import java.util.HashMap;

import jp.happyhacking70.cum.cmd.res.impl.ResCmdClsChnl;
import jp.happyhacking70.cum.cmd.res.impl.ResCmdClsSesh;
import jp.happyhacking70.cum.cmd.res.impl.ResCmdRegChnl;
import jp.happyhacking70.cum.cmd.res.impl.ResCmdRegSesh;
import jp.happyhacking70.cum.cmd.rsc.ChnlRscIntf;
import jp.happyhacking70.cum.excp.prestr.CumExcpAudExists;
import jp.happyhacking70.cum.excp.prestr.CumExcpAudNotExist;
import jp.happyhacking70.cum.excp.prestr.CumExcpChnlExists;
import jp.happyhacking70.cum.excp.prestr.CumExcpChnlNotExist;
import jp.happyhacking70.cum.excp.prestr.CumExcpIgnoreSeshStatus;
import jp.happyhacking70.cum.excp.prestr.CumExcpIllegalChnlStatuMulti;
import jp.happyhacking70.cum.excp.prestr.CumExcpIllegalChnlStatus;
import jp.happyhacking70.cum.excp.prestr.CumExcpIllegalSeshStatus;
import jp.happyhacking70.cum.prestr.adptrLyr.PrestrAdptrIntf;
import jp.happyhacking70.cum.prestr.chnlLyr.PrestrChnl;
import jp.happyhacking70.cum.prestr.chnlLyr.PrestrChnlNotfyIntf;
import jp.happyhacking70.cum.prestr.prestrLyr.PrestrChnlViewIntf;
import jp.happyhacking70.cum.prestr.prestrLyr.PrestrSeshViewIntf;

/**
 * Presenter Session<BR>
 * <BR>
 * State Transition Diagram of Presenter Session <BR>
 * <img src="doc-files/PrestrSeshStateTransitionDiagram.jpg"
 * alt="State Transition Diagram of Presenter Session"/> <BR>
 * Session Status and Methods<BR>
 * <iframe src="doc-files/PrestrSeshStateAndMethods.html" width="1300"
 * height="350"></iframe>
 * 
 * @author happyhacking70@gmail.com
 * 
 */
public class PrestrSesh implements PrestrSeshIntf

{
	/** channles */
	protected HashMap<String, PrestrChnlNotfyIntf> chnls = new HashMap<String, PrestrChnlNotfyIntf>();
	/** name of this session */
	protected String seshName;
	/** session view */
	protected PrestrSeshViewIntf seshView;
	/** adaptor */
	protected PrestrAdptrIntf adptr;
	/** current status of this session */
	protected SeshStatus seshStatus = SeshStatus.init;
	/** session audiences */
	protected HashMap<String, String> auds = new HashMap<String, String>();

	/**
	 * Statuses of session
	 * 
	 * @author happyhacking70@gmail.com
	 * 
	 */
	public enum SeshStatus {
		/** initial status */
		init,
		/** registering */
		reging,
		/** registered */
		reged,
		/** under closing */
		clsing,
		/** closed */
		clsed,
		/** disconnected */
		discned
	}

	/**
	 * @param seshName
	 *            name of session
	 * @param seshView
	 *            session view
	 * @param adptr
	 *            adapter bridging session and communicator
	 */
	public PrestrSesh(String seshName, PrestrSeshViewIntf seshView,
			PrestrAdptrIntf adptr) {
		super();
		this.seshName = seshName;
		this.seshView = seshView;
		this.adptr = adptr;
	}

	// PrestrSeshIntfForSeshView ----------------------------

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * jp.happyhacking70.cum.prestr.seshLyr.PrestrSeshIntfForSeshView#regSesh
	 * (java.lang.String)
	 */
	@Override
	synchronized public void regSesh() throws CumExcpIllegalSeshStatus {
		regSeshCheckStates();
		seshStatus = SeshStatus.reging;
		adptr.regSesh(seshName);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * jp.happyhacking70.cum.prestr.seshLyr.PrestrSeshIntfForSeshView#clsSesh()
	 */
	@Override
	synchronized public void clsSesh() throws CumExcpIllegalSeshStatus,
			CumExcpIllegalChnlStatuMulti {

		clsSeshCheckStates();

		seshStatus = SeshStatus.clsing;
		adptr.clsSesh(seshName);

		CumExcpIllegalChnlStatuMulti mutiExcp = new CumExcpIllegalChnlStatuMulti();

		for (PrestrChnlNotfyIntf chnl : chnls.values()) {

			// this session know if disconnected or not.
			// channel should not raise, channel is disconnected.
			try {
				chnl.seshClsing();
			} catch (CumExcpIllegalChnlStatus e) {

				mutiExcp.add(e);

			}
		}
		if (mutiExcp.size() != 0) {
			throw mutiExcp;
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * jp.happyhacking70.cum.prestr.seshLyr.PrestrSeshIntfForSeshView#regChnl
	 * (java.lang.String, java.util.HashMap,
	 * jp.happyhacking70.cum.prestr.prestrLyr.PrestrChnlViewIntf)
	 */
	@Override
	synchronized public void regChnl(String chnlName,
			HashMap<String, ChnlRscIntf> rsces, PrestrChnlViewIntf chnlView)
			throws CumExcpChnlExists, CumExcpIllegalSeshStatus {

		regChnlCheckStates();
		if (chnls.containsKey(chnlName)) {
			throw new CumExcpChnlExists(seshName, chnlName);
		}

		PrestrChnl chnl = new PrestrChnl(chnlName, rsces, chnlView, this);
		chnls.put(chnlName, chnl);
		adptr.regChnl(seshName, chnlName, chnl, rsces);
	}

	// PrestrSeshIntfForChnlView ----------------------------
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * jp.happyhacking70.cum3.prestr.seshLyr.PrestrSeshIntfForChnlView#clsChnl
	 * (java.lang.String)
	 */
	@Override
	synchronized public void clsChnl(String chnlName)
			throws CumExcpChnlNotExist, CumExcpIllegalSeshStatus {
		clsChnlCheckStates();

		if (!chnls.containsKey(chnlName)) {
			throw new CumExcpChnlNotExist(seshName, chnlName);
		}

		adptr.clsChnl(seshName, chnlName);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * jp.happyhacking70.cum3.prestr.seshLyr.PrestrSeshIntfForChnlView#sendChnlCmd
	 * (java.lang.String, java.lang.String, java.util.HashMap)
	 */
	@Override
	synchronized public void sendChnlCmd(String chnlName, String actionName,
			HashMap<String, String> params) throws CumExcpChnlNotExist,
			CumExcpIllegalSeshStatus {
		sendChnlCmdCheckStates();
		if (chnls.containsKey(chnlName)) {
			throw new CumExcpChnlNotExist(seshName, chnlName);
		}
		adptr.sendChnlCmd(seshName, chnlName, params);
	}

	// PrestrSeshIntfFromSvrNtfy ----------------------------

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * jp.happyhacking70.cum3.prestr.seshLyr.PrestrSeshIntfFromSvrNtfy#audJoinedSesh
	 * (java.lang.String)
	 */
	@Override
	synchronized public void audJoinedSesh(String audName)
			throws CumExcpAudExists, CumExcpIllegalSeshStatus,
			CumExcpIgnoreSeshStatus {
		audJoinedSeshCheckStates();
		if (auds.containsKey(audName)) {
			throw new CumExcpAudExists(seshName, "", audName);
		}
		auds.put(audName, audName);
		seshView.audJoined(audName);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * jp.happyhacking70.cum3.prestr.seshLyr.PrestrSeshIntfFromSvrNtfy#audJoinedChnl
	 * (java.lang.String, java.lang.String)
	 */
	@Override
	synchronized public void audJoinedChnl(String chnlName, String audName)
			throws CumExcpAudNotExist, CumExcpChnlNotExist, CumExcpAudExists,
			CumExcpIllegalChnlStatus, CumExcpIllegalSeshStatus,
			CumExcpIgnoreSeshStatus {
		audJoinedChnlCheckStates();
		if (auds.containsKey(audName) == false) {
			throw new CumExcpAudNotExist(seshName, chnlName, audName);
		}
		PrestrChnlNotfyIntf chnl = chnls.get(chnlName);
		if (chnl == null) {
			throw new CumExcpChnlNotExist(seshName, chnlName);
		}

		chnl.audJoinedChnl(audName);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * jp.happyhacking70.cum3.prestr.seshLyr.PrestrSeshIntfFromSvrNtfy#audRjctedChnl
	 * (java.lang.String, java.lang.String)
	 */
	@Override
	synchronized public void audRjctedChnl(String chnlName, String audName)
			throws CumExcpAudNotExist, CumExcpChnlNotExist,
			CumExcpIllegalChnlStatus, CumExcpIllegalSeshStatus,
			CumExcpIgnoreSeshStatus, CumExcpAudExists {

		audRjctedChnlCheckStates();
		if (auds.containsKey(audName) == false) {
			throw new CumExcpAudNotExist(seshName, chnlName, audName);
		}
		PrestrChnlNotfyIntf chnl = chnls.get(chnlName);
		if (chnl == null) {
			throw new CumExcpChnlNotExist(seshName, chnlName);
		}

		chnl.audRjctedChnl(audName);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * jp.happyhacking70.cum3.prestr.seshLyr.PrestrSeshIntfFromSvrNtfy#audLftChnl
	 * (java.lang.String, java.lang.String)
	 */
	@Override
	synchronized public void audLftChnl(String chnlName, String audName)
			throws CumExcpAudNotExist, CumExcpIllegalChnlStatus,
			CumExcpChnlNotExist, CumExcpIllegalSeshStatus,
			CumExcpIgnoreSeshStatus {

		audLftChnlCheckStates();
		if (auds.containsKey(audName) == false) {
			throw new CumExcpAudNotExist(seshName, chnlName, audName);
		}
		PrestrChnlNotfyIntf chnl = chnls.get(chnlName);
		if (chnl == null) {
			throw new CumExcpChnlNotExist(seshName, chnlName);
		}

		chnl.audLftChnl(audName);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * jp.happyhacking70.cum3.prestr.seshLyr.PrestrSeshIntfFromSvrNtfy#audLftSesh
	 * (java.lang.String)
	 */
	@Override
	synchronized public void audLftSesh(String audName)
			throws CumExcpAudNotExist, CumExcpIllegalSeshStatus,
			CumExcpIllegalChnlStatuMulti, CumExcpIgnoreSeshStatus {

		audLftSeshCheckStates();
		if (auds.containsKey(audName)) {
			seshView.audLft(audName);
			CumExcpIllegalChnlStatuMulti mutiExcp = new CumExcpIllegalChnlStatuMulti();
			for (PrestrChnlNotfyIntf chnl : chnls.values()) {

				try {
					chnl.audLftChnl(audName);
				} catch (CumExcpIllegalChnlStatus e) {
					mutiExcp.add(e);
				}

			}
			if (mutiExcp.size() != 0) {
				throw mutiExcp;
			}
			auds.remove(audName);
		} else {
			throw new CumExcpAudNotExist(seshName, "", audName);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * jp.happyhacking70.cum3.prestr.seshLyr.PrestrSeshIntfFromSvrNtfy#discned()
	 */
	@Override
	synchronized public void discned() throws CumExcpIllegalSeshStatus {
		discnedCheckStates();
		seshStatus = SeshStatus.discned;
		seshView.dscned();
		for (PrestrChnlNotfyIntf chnl : chnls.values()) {
			chnl.discnded();
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * jp.happyhacking70.cum3.prestr.seshLyr.PrestrSeshIntfFromSvrNtfy#audDiscned
	 * (java.lang.String)
	 */
	@Override
	synchronized public void audDiscned(String audName)
			throws CumExcpIllegalSeshStatus, CumExcpAudNotExist,
			CumExcpIllegalChnlStatuMulti, CumExcpIgnoreSeshStatus {

		audDiscnedCheckStates();

		if (auds.containsKey(audName)) {
			seshView.audDscned(audName);
			auds.remove(audName);
			CumExcpIllegalChnlStatuMulti mutiExcp = new CumExcpIllegalChnlStatuMulti();
			for (PrestrChnlNotfyIntf chnl : chnls.values()) {
				try {
					chnl.audDiscned(audName);
				} catch (CumExcpIllegalChnlStatus e) {
					mutiExcp.add(e);
				}
			}
			if (mutiExcp.size() != 0) {
				throw mutiExcp;
			}
		} else {
			throw new CumExcpAudNotExist(seshName, "", audName);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * jp.happyhacking70.cum3.prestr.seshLyr.PrestrSeshIntfForSvrRes#regSeshRslt
	 * (java.lang.String)
	 */
	@Override
	public void regSeshRslt(String rslt) throws CumExcpIllegalSeshStatus,
			CumExcpIgnoreSeshStatus {
		regSeshRsltCheckStates();

		if (rslt == ResCmdRegSesh.RsltTypes.Reged.name()) {
			seshStatus = SeshStatus.reged;
		} else {
			seshView.regSeshFailed(rslt);
			seshStatus = SeshStatus.init;
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * jp.happyhacking70.cum3.prestr.seshLyr.PrestrSeshIntfForSvrRes#clsSeshRslt
	 * (java.lang.String)
	 */
	@Override
	public void clsSeshRslt(String rslt) throws CumExcpIllegalSeshStatus,
			CumExcpIgnoreSeshStatus {
		clsSeshRsltCheckStates();
		if (rslt == ResCmdClsSesh.RsltTypes.Clsed.name()) {
			seshStatus = SeshStatus.clsed;

		} else {
			seshStatus = SeshStatus.init;
			seshView.clsSeshFailed(rslt);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * jp.happyhacking70.cum3.prestr.seshLyr.PrestrSeshIntfForSvrRes#regChnlRslt
	 * (java.lang.String, java.lang.String)
	 */
	@Override
	public void regChnlRslt(String chnlName, String rslt)
			throws CumExcpIllegalSeshStatus, CumExcpIllegalChnlStatus,
			CumExcpChnlNotExist, CumExcpIgnoreSeshStatus {
		regChnlRsltCheckStates();
		PrestrChnlNotfyIntf chnl = chnls.get(chnlName);
		if (chnl == null) {
			throw new CumExcpChnlNotExist(seshName, chnlName);
		}
		if (rslt == ResCmdRegChnl.RsltTypes.Reged.name()) {
			chnl.chnlReged();
		} else {
			seshView.regChnlFailed(rslt);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * jp.happyhacking70.cum3.prestr.seshLyr.PrestrSeshIntfForSvrRes#clsChnlRslt
	 * (java.lang.String, java.lang.String)
	 */
	@Override
	public void clsChnlRslt(String chnlName, String rslt)
			throws CumExcpChnlNotExist, CumExcpIllegalSeshStatus,
			CumExcpIllegalChnlStatus, CumExcpIgnoreSeshStatus {

		clsChnlRsltCheckStates();
		PrestrChnlNotfyIntf chnl = chnls.get(chnlName);
		if (chnl == null) {
			throw new CumExcpChnlNotExist(seshName, chnlName);
		}

		if (rslt == ResCmdClsChnl.RsltTypes.Clsed.name()) {
			chnl.chnlClsed();
		} else {
			chnl.clsChnlFailed(rslt);
		}

	}

	protected void clsSeshCheckStates() throws CumExcpIllegalSeshStatus {
		if (seshStatus == SeshStatus.init) {
			throw new CumExcpIllegalSeshStatus(seshName, seshStatus.name());
		} else if (seshStatus == SeshStatus.reging) {
			throw new CumExcpIllegalSeshStatus(seshName, seshStatus.name());
		} else if (seshStatus == SeshStatus.reged) {
		} else if (seshStatus == SeshStatus.clsing) {
			throw new CumExcpIllegalSeshStatus(seshName, seshStatus.name());
		} else if (seshStatus == SeshStatus.clsed) {
			throw new CumExcpIllegalSeshStatus(seshName, seshStatus.name());
		} else if (seshStatus == SeshStatus.discned) {
			throw new CumExcpIllegalSeshStatus(seshName, seshStatus.name());
		}
	}

	protected void regChnlCheckStates() throws CumExcpIllegalSeshStatus {
		if (seshStatus == SeshStatus.init) {
			throw new CumExcpIllegalSeshStatus(seshName, seshStatus.name());
		} else if (seshStatus == SeshStatus.reging) {
			throw new CumExcpIllegalSeshStatus(seshName, seshStatus.name());
		} else if (seshStatus == SeshStatus.reged) {
		} else if (seshStatus == SeshStatus.clsing) {
			throw new CumExcpIllegalSeshStatus(seshName, seshStatus.name());
		} else if (seshStatus == SeshStatus.clsed) {
			throw new CumExcpIllegalSeshStatus(seshName, seshStatus.name());
		} else if (seshStatus == SeshStatus.discned) {
			throw new CumExcpIllegalSeshStatus(seshName, seshStatus.name());
		}
	}

	protected void regSeshCheckStates() throws CumExcpIllegalSeshStatus {
		if (seshStatus == SeshStatus.init) {
		} else if (seshStatus == SeshStatus.reging) {
			throw new CumExcpIllegalSeshStatus(seshName, seshStatus.name());
		} else if (seshStatus == SeshStatus.reged) {
			throw new CumExcpIllegalSeshStatus(seshName, seshStatus.name());
		} else if (seshStatus == SeshStatus.clsing) {
			throw new CumExcpIllegalSeshStatus(seshName, seshStatus.name());
		} else if (seshStatus == SeshStatus.clsed) {
			throw new CumExcpIllegalSeshStatus(seshName, seshStatus.name());
		} else if (seshStatus == SeshStatus.discned) {
			throw new CumExcpIllegalSeshStatus(seshName, seshStatus.name());
		}
	}

	protected void clsChnlCheckStates() throws CumExcpIllegalSeshStatus {
		if (seshStatus == SeshStatus.init) {
			throw new CumExcpIllegalSeshStatus(seshName, seshStatus.name());
		} else if (seshStatus == SeshStatus.reging) {
			throw new CumExcpIllegalSeshStatus(seshName, seshStatus.name());
		} else if (seshStatus == SeshStatus.reged) {
		} else if (seshStatus == SeshStatus.clsing) {
			throw new CumExcpIllegalSeshStatus(seshName, seshStatus.name());
		} else if (seshStatus == SeshStatus.clsed) {
			throw new CumExcpIllegalSeshStatus(seshName, seshStatus.name());
		} else if (seshStatus == SeshStatus.discned) {
			throw new CumExcpIllegalSeshStatus(seshName, seshStatus.name());
		}
	}

	protected void sendChnlCmdCheckStates() throws CumExcpIllegalSeshStatus {
		if (seshStatus == SeshStatus.init) {
			throw new CumExcpIllegalSeshStatus(seshName, seshStatus.name());
		} else if (seshStatus == SeshStatus.reging) {
			throw new CumExcpIllegalSeshStatus(seshName, seshStatus.name());
		} else if (seshStatus == SeshStatus.reged) {
		} else if (seshStatus == SeshStatus.clsing) {
			throw new CumExcpIllegalSeshStatus(seshName, seshStatus.name());
		} else if (seshStatus == SeshStatus.clsed) {
			throw new CumExcpIllegalSeshStatus(seshName, seshStatus.name());
		} else if (seshStatus == SeshStatus.discned) {
			throw new CumExcpIllegalSeshStatus(seshName, seshStatus.name());
		}
	}

	protected void clsChnlRsltCheckStates() throws CumExcpIllegalSeshStatus,
			CumExcpIgnoreSeshStatus {
		if (seshStatus == SeshStatus.init) {
			throw new CumExcpIllegalSeshStatus(seshName, seshStatus.name());
		} else if (seshStatus == SeshStatus.reging) {
			throw new CumExcpIllegalSeshStatus(seshName, seshStatus.name());
		} else if (seshStatus == SeshStatus.reged) {
		} else if (seshStatus == SeshStatus.clsing) {
			throw new CumExcpIgnoreSeshStatus(seshName, seshStatus.name());
		} else if (seshStatus == SeshStatus.clsed) {
			throw new CumExcpIllegalSeshStatus(seshName, seshStatus.name());
		} else if (seshStatus == SeshStatus.discned) {
			throw new CumExcpIgnoreSeshStatus(seshName, seshStatus.name());
		}
	}

	protected void clsSeshRsltCheckStates() throws CumExcpIllegalSeshStatus,
			CumExcpIgnoreSeshStatus {
		if (seshStatus == SeshStatus.init) {
			throw new CumExcpIllegalSeshStatus(seshName, seshStatus.name());
		} else if (seshStatus == SeshStatus.reging) {
			throw new CumExcpIllegalSeshStatus(seshName, seshStatus.name());
		} else if (seshStatus == SeshStatus.reged) {
			throw new CumExcpIllegalSeshStatus(seshName, seshStatus.name());
		} else if (seshStatus == SeshStatus.clsing) {
		} else if (seshStatus == SeshStatus.clsed) {
			throw new CumExcpIllegalSeshStatus(seshName, seshStatus.name());
		} else if (seshStatus == SeshStatus.discned) {
			throw new CumExcpIgnoreSeshStatus(seshName, seshStatus.name());
		}
	}

	protected void regChnlRsltCheckStates() throws CumExcpIllegalSeshStatus,
			CumExcpIgnoreSeshStatus {
		if (seshStatus == SeshStatus.init) {
			throw new CumExcpIllegalSeshStatus(seshName, seshStatus.name());
		} else if (seshStatus == SeshStatus.reging) {
			throw new CumExcpIllegalSeshStatus(seshName, seshStatus.name());
		} else if (seshStatus == SeshStatus.reged) {
		} else if (seshStatus == SeshStatus.clsing) {
			throw new CumExcpIgnoreSeshStatus(seshName, seshStatus.name());
		} else if (seshStatus == SeshStatus.clsed) {
			throw new CumExcpIgnoreSeshStatus(seshName, seshStatus.name());
		} else if (seshStatus == SeshStatus.discned) {
			throw new CumExcpIgnoreSeshStatus(seshName, seshStatus.name());
		}
	}

	protected void regSeshRsltCheckStates() throws CumExcpIllegalSeshStatus,
			CumExcpIgnoreSeshStatus {
		if (seshStatus == SeshStatus.init) {
			throw new CumExcpIllegalSeshStatus(seshName, seshStatus.name());
		} else if (seshStatus == SeshStatus.reging) {
		} else if (seshStatus == SeshStatus.reged) {
			throw new CumExcpIllegalSeshStatus(seshName, seshStatus.name());
		} else if (seshStatus == SeshStatus.clsing) {
			throw new CumExcpIllegalSeshStatus(seshName, seshStatus.name());
		} else if (seshStatus == SeshStatus.clsed) {
			throw new CumExcpIllegalSeshStatus(seshName, seshStatus.name());
		} else if (seshStatus == SeshStatus.discned) {
			throw new CumExcpIgnoreSeshStatus(seshName, seshStatus.name());
		}
	}

	protected void audDiscnedCheckStates() throws CumExcpIllegalSeshStatus,
			CumExcpIgnoreSeshStatus {
		if (seshStatus == SeshStatus.init) {
			throw new CumExcpIllegalSeshStatus(seshName, seshStatus.name());
		} else if (seshStatus == SeshStatus.reging) {
		} else if (seshStatus == SeshStatus.reged) {
		} else if (seshStatus == SeshStatus.clsing) {
			throw new CumExcpIgnoreSeshStatus(seshName, seshStatus.name());
		} else if (seshStatus == SeshStatus.clsed) {
			throw new CumExcpIgnoreSeshStatus(seshName, seshStatus.name());
		} else if (seshStatus == SeshStatus.discned) {
			throw new CumExcpIgnoreSeshStatus(seshName, seshStatus.name());
		}
	}

	protected void audJoinedChnlCheckStates() throws CumExcpIllegalSeshStatus,
			CumExcpIgnoreSeshStatus {
		if (seshStatus == SeshStatus.init) {
			throw new CumExcpIllegalSeshStatus(seshName, seshStatus.name());
		} else if (seshStatus == SeshStatus.reging) {
			throw new CumExcpIllegalSeshStatus(seshName, seshStatus.name());
		} else if (seshStatus == SeshStatus.reged) {
		} else if (seshStatus == SeshStatus.clsing) {
			throw new CumExcpIgnoreSeshStatus(seshName, seshStatus.name());
		} else if (seshStatus == SeshStatus.clsed) {
			throw new CumExcpIgnoreSeshStatus(seshName, seshStatus.name());
		} else if (seshStatus == SeshStatus.discned) {
			throw new CumExcpIgnoreSeshStatus(seshName, seshStatus.name());
		}
	}

	protected void audJoinedSeshCheckStates() throws CumExcpIllegalSeshStatus,
			CumExcpIgnoreSeshStatus {
		if (seshStatus == SeshStatus.init) {
			throw new CumExcpIllegalSeshStatus(seshName, seshStatus.name());
		} else if (seshStatus == SeshStatus.reging) {
		} else if (seshStatus == SeshStatus.reged) {
		} else if (seshStatus == SeshStatus.clsing) {
			throw new CumExcpIgnoreSeshStatus(seshName, seshStatus.name());
		} else if (seshStatus == SeshStatus.clsed) {
			throw new CumExcpIgnoreSeshStatus(seshName, seshStatus.name());
		} else if (seshStatus == SeshStatus.discned) {
			throw new CumExcpIgnoreSeshStatus(seshName, seshStatus.name());
		}
	}

	protected void audLftChnlCheckStates() throws CumExcpIllegalSeshStatus,
			CumExcpIgnoreSeshStatus {
		if (seshStatus == SeshStatus.init) {
			throw new CumExcpIllegalSeshStatus(seshName, seshStatus.name());
		} else if (seshStatus == SeshStatus.reging) {
			throw new CumExcpIllegalSeshStatus(seshName, seshStatus.name());
		} else if (seshStatus == SeshStatus.reged) {
		} else if (seshStatus == SeshStatus.clsing) {
			throw new CumExcpIgnoreSeshStatus(seshName, seshStatus.name());
		} else if (seshStatus == SeshStatus.clsed) {
			throw new CumExcpIgnoreSeshStatus(seshName, seshStatus.name());
		} else if (seshStatus == SeshStatus.discned) {
			throw new CumExcpIgnoreSeshStatus(seshName, seshStatus.name());
		}
	}

	protected void audLftSeshCheckStates() throws CumExcpIllegalSeshStatus,
			CumExcpIgnoreSeshStatus {
		if (seshStatus == SeshStatus.init) {
			throw new CumExcpIllegalSeshStatus(seshName, seshStatus.name());
		} else if (seshStatus == SeshStatus.reging) {
		} else if (seshStatus == SeshStatus.reged) {
		} else if (seshStatus == SeshStatus.clsing) {
			throw new CumExcpIgnoreSeshStatus(seshName, seshStatus.name());
		} else if (seshStatus == SeshStatus.clsed) {
			throw new CumExcpIgnoreSeshStatus(seshName, seshStatus.name());
		} else if (seshStatus == SeshStatus.discned) {
			throw new CumExcpIgnoreSeshStatus(seshName, seshStatus.name());
		}
	}

	protected void audRjctedChnlCheckStates() throws CumExcpIllegalSeshStatus,
			CumExcpIgnoreSeshStatus {
		if (seshStatus == SeshStatus.init) {
			throw new CumExcpIllegalSeshStatus(seshName, seshStatus.name());
		} else if (seshStatus == SeshStatus.reging) {
			throw new CumExcpIllegalSeshStatus(seshName, seshStatus.name());
		} else if (seshStatus == SeshStatus.reged) {
		} else if (seshStatus == SeshStatus.clsing) {
			throw new CumExcpIgnoreSeshStatus(seshName, seshStatus.name());
		} else if (seshStatus == SeshStatus.clsed) {
			throw new CumExcpIgnoreSeshStatus(seshName, seshStatus.name());
		} else if (seshStatus == SeshStatus.discned) {
			throw new CumExcpIgnoreSeshStatus(seshName, seshStatus.name());
		}
	}

	protected void discnedCheckStates() throws CumExcpIllegalSeshStatus {
		if (seshStatus == SeshStatus.init) {
			throw new CumExcpIllegalSeshStatus(seshName, seshStatus.name());
		} else if (seshStatus == SeshStatus.reging) {
		} else if (seshStatus == SeshStatus.reged) {
		} else if (seshStatus == SeshStatus.clsing) {
		} else if (seshStatus == SeshStatus.clsed) {
		} else if (seshStatus == SeshStatus.discned) {
			throw new CumExcpIllegalSeshStatus(seshName, seshStatus.name());
		}
	}

}
