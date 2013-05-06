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
import jp.happyhacking70.cum.excp.CumExcpAudExists;
import jp.happyhacking70.cum.excp.prestr.CumExcpAudExist;
import jp.happyhacking70.cum.excp.prestr.CumExcpAudNotExist;
import jp.happyhacking70.cum.excp.prestr.CumExcpChnlExists;
import jp.happyhacking70.cum.excp.prestr.CumExcpChnlNotExist;
import jp.happyhacking70.cum.excp.prestr.CumExcpIllegalChnlStatuMulti;
import jp.happyhacking70.cum.excp.prestr.CumExcpIllegalChnlStatus;
import jp.happyhacking70.cum.excp.prestr.CumExcpIllegalSeshStatus;
import jp.happyhacking70.cum.prestr.adptrLyr.PrestrAdptrIntf;
import jp.happyhacking70.cum.prestr.chnlLyr.PrestrChnl;
import jp.happyhacking70.cum.prestr.chnlLyr.PrestrChnlIntfFromChnlView;
import jp.happyhacking70.cum.prestr.chnlLyr.PrestrChnlNotfyIntf;
import jp.happyhacking70.cum.prestr.prestrLyr.PrestrChnlViewIntf;
import jp.happyhacking70.cum.prestr.prestrLyr.PrestrSeshViewIntf;

/**
 * @author happyhacking70@gmail.com
 * 
 */
public class PrestrSesh implements PrestrSeshIntfForSeshView,
		PrestrSeshIntfForChnlView, PrestrSeshIntfFromSvrNtfy,
		PrestrSeshIntfForSvrRes
/*
 * , , PrestrSeshIntfFromSvrRes
 */

// extends
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

	// PrestrSeshIntfForSeshView ----------------------------

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * jp.happyhacking70.cum.prestr.seshLyr.PrestrSeshIntfForSeshView#regSesh
	 * (java.lang.String)
	 */
	@Override
	synchronized public void regSesh(String seshName)
			throws CumExcpIllegalSeshStatus {

		if (seshStatus == SeshStatus.init) {
			seshStatus = SeshStatus.reging;
			adptr.regSesh(seshName);
		} else {
			throw new CumExcpIllegalSeshStatus(seshName, seshStatus.name());
		}
	}

	@Override
	synchronized public void clsSesh() throws CumExcpIllegalSeshStatus,
			CumExcpIllegalChnlStatuMulti {
		if (seshStatus == SeshStatus.reged || seshStatus == SeshStatus.reging) {
			adptr.clsSesh(seshName);
			seshStatus = SeshStatus.clsing;

			CumExcpIllegalChnlStatuMulti mutiExcp = new CumExcpIllegalChnlStatuMulti();

			for (PrestrChnlNotfyIntf chnl : chnls.values()) {
				try {
					chnl.seshClsing();
				} catch (CumExcpIllegalChnlStatus e) {
					mutiExcp.add(e);

				}
			}
			if (mutiExcp.size() != 0) {
				throw mutiExcp;
			}
		} else {
			throw new CumExcpIllegalSeshStatus(seshName, seshStatus.name());
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
		if (seshStatus == SeshStatus.reged) {
			if (chnls.containsKey(chnlName)) {
				throw new CumExcpChnlExists(seshName, chnlName);
			}

			PrestrChnlIntfFromChnlView chnl = new PrestrChnl(chnlName, rsces,
					chnlView, this);
			adptr.regChnl(seshName, chnl, rsces);
		} else {
			throw new CumExcpIllegalSeshStatus(seshName, seshStatus.name());
		}
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

		if (seshStatus == SeshStatus.reged) {
			if (chnls.containsKey(chnlName)) {
				throw new CumExcpChnlNotExist(seshName, chnlName);
			}

			adptr.clsChnl(seshName, chnlName);
		} else {
			throw new CumExcpIllegalSeshStatus(seshName, seshStatus.name());
		}
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
		if (seshStatus == SeshStatus.reged) {
			if (chnls.containsKey(chnlName)) {
				throw new CumExcpChnlNotExist(seshName, chnlName);
			}
			adptr.sendChnlCmd(seshName, chnlName, params);
		} else {
			throw new CumExcpIllegalSeshStatus(seshName, seshStatus.name());
		}
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
			throws CumExcpAudExists, CumExcpIllegalSeshStatus {
		if (seshStatus == SeshStatus.reging || seshStatus == SeshStatus.reged) {
			if (auds.containsKey(audName)) {
				throw new CumExcpAudExists(seshName, "", audName);
			}
			auds.put(audName, audName);
			seshView.audJoined(audName);
		} else {
			throw new CumExcpIllegalSeshStatus(seshName, seshStatus.name());
		}

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
			throws CumExcpAudNotExist, CumExcpChnlNotExist, CumExcpAudExist,
			CumExcpIllegalChnlStatus, CumExcpIllegalSeshStatus {

		if (seshStatus == SeshStatus.reging || seshStatus == SeshStatus.reged) {
			if (auds.containsKey(audName) == false) {
				throw new CumExcpAudNotExist(seshName, chnlName, audName);
			}
			PrestrChnlNotfyIntf chnl = chnls.get(chnlName);
			if (chnl == null) {
				throw new CumExcpChnlNotExist(seshName, chnlName);
			}

			chnl.audJoinedChnl(audName);
		} else {
			throw new CumExcpIllegalSeshStatus(seshName, seshStatus.name());
		}

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
			CumExcpIllegalChnlStatus, CumExcpIllegalSeshStatus {
		if (seshStatus == SeshStatus.reging || seshStatus == SeshStatus.reged) {
			if (auds.containsKey(audName) == false) {
				throw new CumExcpAudNotExist(seshName, chnlName, audName);
			}
			PrestrChnlNotfyIntf chnl = chnls.get(chnlName);
			if (chnl == null) {
				throw new CumExcpChnlNotExist(seshName, chnlName);
			}

			chnl.audRjctedChnl(audName);

		} else {
			throw new CumExcpIllegalSeshStatus(seshName, seshStatus.name());
		}

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
			CumExcpChnlNotExist, CumExcpIllegalSeshStatus {
		if (seshStatus == SeshStatus.reging || seshStatus == SeshStatus.reged) {
			if (auds.containsKey(audName) == false) {
				throw new CumExcpAudNotExist(seshName, chnlName, audName);
			}
			PrestrChnlNotfyIntf chnl = chnls.get(chnlName);
			if (chnl == null) {
				throw new CumExcpChnlNotExist(seshName, chnlName);
			}

			chnl.audLftChnl(audName);
		} else {
			throw new CumExcpIllegalSeshStatus(seshName, seshStatus.name());
		}

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
			CumExcpIllegalChnlStatuMulti {
		if (seshStatus == SeshStatus.reging || seshStatus == SeshStatus.reged) {
			if (auds.containsKey(audName)) {
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
		} else {
			throw new CumExcpIllegalSeshStatus(seshName, seshStatus.name());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * jp.happyhacking70.cum3.prestr.seshLyr.PrestrSeshIntfFromSvrNtfy#discned()
	 */
	@Override
	synchronized public void discned() {
		seshStatus = SeshStatus.discned;
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
			CumExcpIllegalChnlStatuMulti {
		if (seshStatus == SeshStatus.reging || seshStatus == SeshStatus.reged) {
			if (auds.containsKey(audName)) {
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
		} else {
			throw new CumExcpIllegalSeshStatus(seshName, seshStatus.name());
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
	public void regSeshRslt(String rslt) {
		if (seshStatus == SeshStatus.reging) {
			if (rslt == ResCmdRegSesh.RsltTypes.Reged.name()) {
				seshStatus = SeshStatus.reged;
			} else {
				seshView.regSeshFailed(rslt);
				seshStatus = SeshStatus.init;
			}
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
	public void clsSeshRslt(String rslt) throws CumExcpIllegalSeshStatus {
		if (seshStatus == SeshStatus.reged) {
			if (rslt == ResCmdClsSesh.RsltTypes.Clsed.name()) {
				seshStatus = SeshStatus.clsed;

			} else {
				seshView.clsSeshFailed(rslt);
			}
		} else {
			throw new CumExcpIllegalSeshStatus(seshName, seshStatus.name());
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
			CumExcpChnlNotExist {
		if (seshStatus == SeshStatus.reged) {
			PrestrChnlNotfyIntf chnl = chnls.get(chnlName);
			if (chnl == null) {
				throw new CumExcpChnlNotExist(seshName, chnlName);
			}
			if (rslt == ResCmdRegChnl.RsltTypes.Reged.name()) {
				chnl.chnlReged();
			} else {
				seshView.regChnlFailed(rslt);
			}

		} else {
			throw new CumExcpIllegalSeshStatus(seshName, seshStatus.name());
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
			CumExcpIllegalChnlStatus {
		if (seshStatus == SeshStatus.reged) {
			PrestrChnlNotfyIntf chnl = chnls.get(chnlName);
			if (chnl == null) {
				throw new CumExcpChnlNotExist(seshName, chnlName);
			}

			if (rslt == ResCmdClsChnl.RsltTypes.Clsed.name()) {
				chnl.chnlClsed();
			} else {
				chnl.clsChnlFailed(rslt);
			}
		} else {
			throw new CumExcpIllegalSeshStatus(seshName, seshStatus.name());
		}

	}

}
