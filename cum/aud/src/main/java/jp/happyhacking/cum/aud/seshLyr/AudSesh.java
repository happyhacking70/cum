/**
 * 
 */
package jp.happyhacking.cum.aud.seshLyr;

import java.util.HashMap;

import jp.happyhacking.cum.aud.adptrLyr.AudAdptrIntf;
import jp.happyhacking.cum.aud.audLyr.AudSeshViewIntf;
import jp.happyhacking.cum.aud.chnlLyr.AudChnl;
import jp.happyhacking.cum.aud.chnlLyr.AudChnlIntfForSesh;
import jp.happyhacking.cum.aud.excp.CumExcpChnlExists;
import jp.happyhacking.cum.aud.excp.CumExcpChnlNotExist;
import jp.happyhacking.cum.aud.excp.CumExcpIgnoreChnlStatus;
import jp.happyhacking.cum.aud.excp.CumExcpIgnoreSeshStatus;
import jp.happyhacking.cum.aud.excp.CumExcpIllegalChnlStatuMulti;
import jp.happyhacking.cum.aud.excp.CumExcpIllegalChnlStatus;
import jp.happyhacking.cum.aud.excp.CumExcpIllegalSeshStatus;
import jp.happyhacking70.cum.cmd.res.impl.ResCmdJoinChnl;
import jp.happyhacking70.cum.cmd.rsc.ChnlRscIntf;

/**
 * @author happyhacking70@gmail.com
 * 
 */
public class AudSesh implements AudSeshAdptrIntf, AudSeshChnlIntf,
		AudSeshIntfForSeshView {
	/** Audience Session Status */
	public enum Status {
		/** initial state */
		init,
		/** joining session */
		joining,
		/** joined session successfully */
		joined,
		/** leaving session */
		lving,
		/** session closed by presenter */
		clsed,
		/** session disconnected */
		dscned
	}

	protected Status seshStatus = Status.init;
	protected String seshName;
	protected String audName;
	protected AudSeshViewIntf seshView;
	protected AudAdptrIntf adptr;
	protected HashMap<String, AudChnlIntfForSesh> chnls = new HashMap<String, AudChnlIntfForSesh>();

	/**
	 * @param seshName
	 * @param audName
	 * @param seshView
	 */
	public AudSesh(String seshName, String audName, AudSeshViewIntf seshView,
			AudAdptrIntf adptr) {
		super();
		this.seshName = seshName;
		this.seshView = seshView;
		this.audName = audName;
		this.adptr = adptr;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see jp.happyhacking.cum.aud.seshLyr.AudSeshIntfForSeshView#joinSesh()
	 */
	@Override
	public void joinSesh() throws CumExcpIllegalSeshStatus {
		joinSeshCheckStatus();
		seshStatus = Status.joining;
		adptr.joinSesh(seshName, audName);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see jp.happyhacking.cum.aud.seshLyr.AudSeshIntf#joined()
	 */
	@Override
	public void seshJoined() throws CumExcpIllegalSeshStatus {
		seshJoinedCheckStatus();
		seshStatus = Status.joined;
		seshView.joinSesh();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see jp.happyhacking.cum.aud.seshLyr.AudSeshIntf#joinFailed()
	 */
	@Override
	public void seshJoinFailed(String rslt) throws CumExcpIllegalSeshStatus {
		seshJoinFailedCheckStatus();
		seshStatus = Status.init;
		seshView.seshJoinFailed(rslt);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see jp.happyhacking.cum.aud.seshLyr.AudSeshIntf#lv()
	 */
	@Override
	public void lvSesh() throws CumExcpIllegalSeshStatus,
			CumExcpIllegalChnlStatus, CumExcpIgnoreChnlStatus {
		lvSeshCheckStatus();
		seshStatus = Status.lving;
		for (AudChnlIntfForSesh chnl : chnls.values()) {
			chnl.seshLving();
		}
		adptr.lvSesh(seshName, audName);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see jp.happyhacking.cum.aud.seshLyr.AudSeshIntf#lft()
	 */
	@Override
	public void seshLft() throws CumExcpIllegalSeshStatus {
		seshLftCheckStatus();
		seshStatus = Status.init;
		seshView.seshLft();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see jp.happyhacking.cum.aud.seshLyr.AudSeshIntf#lvFailed()
	 */
	@Override
	public void seshLvFailed(String rslt) throws CumExcpIllegalSeshStatus {
		lvSeshFailedCheckStatus();
		seshStatus = Status.init;
		seshView.seshLvFailed(rslt);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see jp.happyhacking.cum.aud.seshLyr.AudSeshIntf#clsed()
	 */
	@Override
	public void seshClsed() throws CumExcpIllegalChnlStatuMulti,
			CumExcpIllegalSeshStatus {
		seshClsedCheckStatus();
		seshStatus = Status.clsed;

		CumExcpIllegalChnlStatuMulti multiExcp = new CumExcpIllegalChnlStatuMulti();

		for (AudChnlIntfForSesh chnl : chnls.values()) {
			try {
				chnl.seshClsed();
			} catch (CumExcpIllegalChnlStatus e) {
				multiExcp.add(e);
			}
		}

		if (multiExcp.size() > 0) {
			throw multiExcp;
		}

		seshView.seshClsed();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see jp.happyhacking.cum.aud.seshLyr.AudSeshIntf#dscned()
	 */
	@Override
	public void seshDscned() throws CumExcpIllegalChnlStatuMulti,
			CumExcpIllegalSeshStatus {
		try {
			seshDscnedCheckStatus();
			seshStatus = Status.dscned;
			CumExcpIllegalChnlStatuMulti multiExcp = new CumExcpIllegalChnlStatuMulti();

			for (AudChnlIntfForSesh chnl : chnls.values()) {
				try {
					chnl.chnlDscned();
				} catch (CumExcpIllegalChnlStatus e) {
					multiExcp.add(e);
				}
			}

			if (multiExcp.size() > 0) {
				throw multiExcp;
			}

			seshView.seshDscned();
		} catch (CumExcpIgnoreSeshStatus e1) {
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * jp.happyhacking.cum.aud.seshLyr.AudSeshIntf#chnlReged(java.lang.String,
	 * java.util.HashMap)
	 */
	@Override
	public void chnlReged(String chnlType, String chnlName,
			HashMap<String, ChnlRscIntf> rsces) throws CumExcpChnlExists,
			CumExcpIllegalSeshStatus {
		try {
			chnlRjctedCheckStatus();
			if (chnls.containsKey(chnlName)) {
				throw new CumExcpChnlExists(seshName, chnlName);
			}
			AudChnlIntfForSesh chnl = new AudChnl(chnlType, chnlName, rsces,
					this, seshView.getChnlView(chnlType));
			chnls.put(chnlName, chnl);
			seshView.chnlRged(chnlName);
		} catch (CumExcpIgnoreSeshStatus e) {
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * jp.happyhacking.cum.aud.seshLyr.AudSeshIntf#joinChnl(java.lang.String)
	 */
	@Override
	public void joinChnl(String chnlName) throws CumExcpIllegalSeshStatus,
			CumExcpChnlNotExist {
		joinChnlCheckStatus();
		getChnl(chnlName);

		adptr.joinChnl(seshName, chnlName, audName);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * jp.happyhacking.cum.aud.seshLyr.AudSeshIntf#chnlJoined(java.lang.String)
	 */
	@Override
	public void chnlJoined(String chnlName) throws CumExcpIllegalSeshStatus,
			CumExcpChnlNotExist, CumExcpIllegalChnlStatus {
		try {
			chnlJoinedCheckStatus();
			AudChnlIntfForSesh chnl = getChnl(chnlName);
			chnl.chnlJoined();
		} catch (CumExcpIgnoreSeshStatus e) {
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * jp.happyhacking.cum.aud.seshLyr.AudSeshIntf#chnlJoinFailed(java.lang.
	 * String, java.lang.String)
	 */
	@Override
	public void chnlJoinFailed(String chnlName, String rslt)
			throws CumExcpIllegalSeshStatus, CumExcpChnlNotExist {
		try {
			chnlJoinedCheckStatus();
			getChnl(chnlName);

			if (rslt.equals(ResCmdJoinChnl.RsltTypes.ChnlNotExist.name())) {
				chnls.remove(chnlName);
			}
			seshView.chnlJoinFailed(chnlName, rslt);

		} catch (CumExcpIgnoreSeshStatus e) {
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see jp.happyhacking.cum.aud.seshLyr.AudSeshIntf#lvChnl(java.lang.String)
	 */
	@Override
	public void lvChnl(String chnlName) throws CumExcpIllegalSeshStatus,
			CumExcpChnlNotExist {
		lvChnlCheckStatus();
		AudChnlIntfForSesh chnl = getChnl(chnlName);

		adptr.lvChnl(seshName, chnl.getChnlType(), chnlName);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * jp.happyhacking.cum.aud.seshLyr.AudSeshIntf#lftChnl(java.lang.String)
	 */
	@Override
	public void lftChnl(String chnlName) throws CumExcpIllegalSeshStatus,
			CumExcpChnlNotExist, CumExcpIllegalChnlStatus {
		try {
			lftChnlCheckStatus();

			AudChnlIntfForSesh chnl = getChnl(chnlName);
			chnl.chnlLft();
		} catch (CumExcpIgnoreSeshStatus e) {
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * jp.happyhacking.cum.aud.seshLyr.AudSeshIntf#lvChnlFailed(java.lang.String
	 * , java.lang.String)
	 */
	@Override
	public void lvChnlFailed(String chnlName, String rslt)
			throws CumExcpIllegalSeshStatus, CumExcpChnlNotExist,
			CumExcpIllegalChnlStatus {

		lvChnlCheckStatus();

		AudChnlIntfForSesh chnl = getChnl(chnlName);
		chnl.chnlLvFailed(rslt);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * jp.happyhacking.cum.aud.seshLyr.AudSeshIntf#rjctChnl(java.lang.String)
	 */
	@Override
	public void rjctChnl(String chnlName) throws CumExcpIllegalSeshStatus,
			CumExcpChnlNotExist, CumExcpIllegalChnlStatus {

		rjctChnlCheckStatus();

		AudChnlIntfForSesh chnl = getChnl(chnlName);
		chnl.chnlRjcting();
		adptr.rjctChnl(seshName, chnl.getChnlType(), chnlName);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * jp.happyhacking.cum.aud.seshLyr.AudSeshIntf#chnlRjcted(java.lang.String)
	 */
	@Override
	public void chnlRjcted(String chnlName) throws CumExcpIllegalSeshStatus,
			CumExcpChnlNotExist, CumExcpIllegalChnlStatus {
		try {
			chnlRjctedCheckStatus();
			getChnl(chnlName);
			seshView.chnlRjcted(chnlName);
		} catch (CumExcpIgnoreSeshStatus e) {
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * jp.happyhacking.cum.aud.seshLyr.AudSeshIntf#chnlRjctFailed(java.lang.
	 * String, java.lang.String)
	 */
	@Override
	public void chnlRjctFailed(String chnlName, String rslt)
			throws CumExcpIllegalSeshStatus, CumExcpChnlNotExist {
		try {
			chnlRjctFailedCheckStatus();
			getChnl(chnlName);
			seshView.chnlRjctFailed(chnlName, rslt);
		} catch (CumExcpIgnoreSeshStatus e) {
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * jp.happyhacking.cum.aud.seshLyr.AudSeshIntf#chnlClses(java.lang.String)
	 */
	@Override
	public void chnlClsed(String chnlName) throws CumExcpIllegalSeshStatus,
			CumExcpChnlNotExist, CumExcpIllegalChnlStatus {
		try {
			chnlClsedCheckStatus();
			AudChnlIntfForSesh chnl = getChnl(chnlName);
			chnl.chnlClsed();
		} catch (CumExcpIgnoreSeshStatus e) {
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * jp.happyhacking.cum.aud.seshLyr.AudSeshIntf#chnlCmdRcved(java.lang.String
	 * , java.lang.String, java.util.HashMap)
	 */
	@Override
	public void chnlCmdRcved(String chnlName, String actionName,
			HashMap<String, String> params) throws CumExcpIllegalSeshStatus,
			CumExcpChnlNotExist, CumExcpIllegalChnlStatus {

		try {
			chnlCmdRcvedCheckStatus();
			AudChnlIntfForSesh chnl = getChnl(chnlName);
			chnl.chnlCmdRcved(actionName, params);
		} catch (CumExcpIgnoreSeshStatus e) {
		}

	}

	protected void seshJoinedCheckStatus() throws CumExcpIllegalSeshStatus {
		if (seshStatus == Status.init) {
			throw new CumExcpIllegalSeshStatus(seshName, seshStatus.name());
		} else if (seshStatus == Status.joining) {
		} else if (seshStatus == Status.joined) {
			throw new CumExcpIllegalSeshStatus(seshName, seshStatus.name());
		} else if (seshStatus == Status.lving) {
			throw new CumExcpIllegalSeshStatus(seshName, seshStatus.name());
		} else if (seshStatus == Status.clsed) {
			throw new CumExcpIllegalSeshStatus(seshName, seshStatus.name());
		} else if (seshStatus == Status.dscned) {
			throw new CumExcpIllegalSeshStatus(seshName, seshStatus.name());
		}
	}

	protected void seshJoinFailedCheckStatus() throws CumExcpIllegalSeshStatus {
		if (seshStatus == Status.init) {
			throw new CumExcpIllegalSeshStatus(seshName, seshStatus.name());
		} else if (seshStatus == Status.joining) {
		} else if (seshStatus == Status.joined) {
			throw new CumExcpIllegalSeshStatus(seshName, seshStatus.name());
		} else if (seshStatus == Status.lving) {
			throw new CumExcpIllegalSeshStatus(seshName, seshStatus.name());
		} else if (seshStatus == Status.clsed) {
			throw new CumExcpIllegalSeshStatus(seshName, seshStatus.name());
		} else if (seshStatus == Status.dscned) {
			throw new CumExcpIllegalSeshStatus(seshName, seshStatus.name());
		}
	}

	protected void seshLftCheckStatus() throws CumExcpIllegalSeshStatus {
		if (seshStatus == Status.init) {
			throw new CumExcpIllegalSeshStatus(seshName, seshStatus.name());
		} else if (seshStatus == Status.joining) {
			throw new CumExcpIllegalSeshStatus(seshName, seshStatus.name());
		} else if (seshStatus == Status.joined) {
			throw new CumExcpIllegalSeshStatus(seshName, seshStatus.name());
		} else if (seshStatus == Status.lving) {
		} else if (seshStatus == Status.clsed) {
			throw new CumExcpIllegalSeshStatus(seshName, seshStatus.name());
		} else if (seshStatus == Status.dscned) {
			throw new CumExcpIllegalSeshStatus(seshName, seshStatus.name());
		}
	}

	protected void lvSeshFailedCheckStatus() throws CumExcpIllegalSeshStatus {
		if (seshStatus == Status.init) {
			throw new CumExcpIllegalSeshStatus(seshName, seshStatus.name());
		} else if (seshStatus == Status.joining) {
			throw new CumExcpIllegalSeshStatus(seshName, seshStatus.name());
		} else if (seshStatus == Status.joined) {
			throw new CumExcpIllegalSeshStatus(seshName, seshStatus.name());
		} else if (seshStatus == Status.lving) {
		} else if (seshStatus == Status.clsed) {
			throw new CumExcpIllegalSeshStatus(seshName, seshStatus.name());
		} else if (seshStatus == Status.dscned) {
			throw new CumExcpIllegalSeshStatus(seshName, seshStatus.name());
		}
	}

	protected void seshClsedCheckStatus() throws CumExcpIllegalSeshStatus {
		if (seshStatus == Status.init) {
			throw new CumExcpIllegalSeshStatus(seshName, seshStatus.name());
		} else if (seshStatus == Status.joining) {
		} else if (seshStatus == Status.joined) {
		} else if (seshStatus == Status.lving) {
		} else if (seshStatus == Status.clsed) {
			throw new CumExcpIllegalSeshStatus(seshName, seshStatus.name());
		} else if (seshStatus == Status.dscned) {
			throw new CumExcpIllegalSeshStatus(seshName, seshStatus.name());
		}
	}

	protected void seshDscnedCheckStatus() throws CumExcpIllegalSeshStatus,
			CumExcpIgnoreSeshStatus {
		if (seshStatus == Status.init) {
			throw new CumExcpIllegalSeshStatus(seshName, seshStatus.name());
		} else if (seshStatus == Status.joining) {
		} else if (seshStatus == Status.joined) {
		} else if (seshStatus == Status.lving) {
		} else if (seshStatus == Status.clsed) {
			throw new CumExcpIgnoreSeshStatus(seshName, seshStatus.name());
		} else if (seshStatus == Status.dscned) {
			throw new CumExcpIllegalSeshStatus(seshName, seshStatus.name());
		}
	}

	protected void chnlRegedCheckStatus() throws CumExcpIllegalSeshStatus,
			CumExcpIgnoreSeshStatus {
		if (seshStatus == Status.init) {
			throw new CumExcpIllegalSeshStatus(seshName, seshStatus.name());
		} else if (seshStatus == Status.joining) {
			throw new CumExcpIllegalSeshStatus(seshName, seshStatus.name());
		} else if (seshStatus == Status.joined) {
		} else if (seshStatus == Status.lving) {
			throw new CumExcpIgnoreSeshStatus(seshName, seshStatus.name());
		} else if (seshStatus == Status.clsed) {
			throw new CumExcpIllegalSeshStatus(seshName, seshStatus.name());
		} else if (seshStatus == Status.dscned) {
			throw new CumExcpIllegalSeshStatus(seshName, seshStatus.name());
		}
	}

	protected void chnlJoinedCheckStatus() throws CumExcpIllegalSeshStatus,
			CumExcpIgnoreSeshStatus {
		if (seshStatus == Status.init) {
			throw new CumExcpIllegalSeshStatus(seshName, seshStatus.name());
		} else if (seshStatus == Status.joining) {
			throw new CumExcpIllegalSeshStatus(seshName, seshStatus.name());
		} else if (seshStatus == Status.joined) {
		} else if (seshStatus == Status.lving) {
			throw new CumExcpIgnoreSeshStatus(seshName, seshStatus.name());
		} else if (seshStatus == Status.clsed) {
			throw new CumExcpIllegalSeshStatus(seshName, seshStatus.name());
		} else if (seshStatus == Status.dscned) {
			throw new CumExcpIllegalSeshStatus(seshName, seshStatus.name());
		}
	}

	protected void chnlJoinFailedCheckStatus() throws CumExcpIllegalSeshStatus,
			CumExcpIgnoreSeshStatus {
		if (seshStatus == Status.init) {
			throw new CumExcpIllegalSeshStatus(seshName, seshStatus.name());
		} else if (seshStatus == Status.joining) {
			throw new CumExcpIllegalSeshStatus(seshName, seshStatus.name());
		} else if (seshStatus == Status.joined) {
		} else if (seshStatus == Status.lving) {
			throw new CumExcpIgnoreSeshStatus(seshName, seshStatus.name());
		} else if (seshStatus == Status.clsed) {
			throw new CumExcpIllegalSeshStatus(seshName, seshStatus.name());
		} else if (seshStatus == Status.dscned) {
			throw new CumExcpIllegalSeshStatus(seshName, seshStatus.name());
		}
	}

	protected void chnlRjctedCheckStatus() throws CumExcpIllegalSeshStatus,
			CumExcpIgnoreSeshStatus {
		if (seshStatus == Status.init) {
			throw new CumExcpIllegalSeshStatus(seshName, seshStatus.name());
		} else if (seshStatus == Status.joining) {
			throw new CumExcpIllegalSeshStatus(seshName, seshStatus.name());
		} else if (seshStatus == Status.joined) {
		} else if (seshStatus == Status.lving) {
			throw new CumExcpIgnoreSeshStatus(seshName, seshStatus.name());
		} else if (seshStatus == Status.clsed) {
			throw new CumExcpIllegalSeshStatus(seshName, seshStatus.name());
		} else if (seshStatus == Status.dscned) {
			throw new CumExcpIllegalSeshStatus(seshName, seshStatus.name());
		}
	}

	protected void chnlRjctFailedCheckStatus() throws CumExcpIllegalSeshStatus,
			CumExcpIgnoreSeshStatus {
		if (seshStatus == Status.init) {
			throw new CumExcpIllegalSeshStatus(seshName, seshStatus.name());
		} else if (seshStatus == Status.joining) {
			throw new CumExcpIllegalSeshStatus(seshName, seshStatus.name());
		} else if (seshStatus == Status.joined) {
		} else if (seshStatus == Status.lving) {
			throw new CumExcpIgnoreSeshStatus(seshName, seshStatus.name());
		} else if (seshStatus == Status.clsed) {
			throw new CumExcpIllegalSeshStatus(seshName, seshStatus.name());
		} else if (seshStatus == Status.dscned) {
			throw new CumExcpIllegalSeshStatus(seshName, seshStatus.name());
		}
	}

	protected void lftChnlCheckStatus() throws CumExcpIllegalSeshStatus,
			CumExcpIgnoreSeshStatus {
		if (seshStatus == Status.init) {
			throw new CumExcpIllegalSeshStatus(seshName, seshStatus.name());
		} else if (seshStatus == Status.joining) {
			throw new CumExcpIllegalSeshStatus(seshName, seshStatus.name());
		} else if (seshStatus == Status.joined) {
		} else if (seshStatus == Status.lving) {
			throw new CumExcpIgnoreSeshStatus(seshName, seshStatus.name());
		} else if (seshStatus == Status.clsed) {
			throw new CumExcpIllegalSeshStatus(seshName, seshStatus.name());
		} else if (seshStatus == Status.dscned) {
			throw new CumExcpIllegalSeshStatus(seshName, seshStatus.name());
		}
	}

	protected void lvChnlFailedCheckStatus() throws CumExcpIllegalSeshStatus,
			CumExcpIgnoreSeshStatus {
		if (seshStatus == Status.init) {
			throw new CumExcpIllegalSeshStatus(seshName, seshStatus.name());
		} else if (seshStatus == Status.joining) {
			throw new CumExcpIllegalSeshStatus(seshName, seshStatus.name());
		} else if (seshStatus == Status.joined) {
		} else if (seshStatus == Status.lving) {
			throw new CumExcpIgnoreSeshStatus(seshName, seshStatus.name());
		} else if (seshStatus == Status.clsed) {
			throw new CumExcpIllegalSeshStatus(seshName, seshStatus.name());
		} else if (seshStatus == Status.dscned) {
			throw new CumExcpIllegalSeshStatus(seshName, seshStatus.name());
		}
	}

	protected void chnlClsedCheckStatus() throws CumExcpIllegalSeshStatus,
			CumExcpIgnoreSeshStatus {
		if (seshStatus == Status.init) {
			throw new CumExcpIllegalSeshStatus(seshName, seshStatus.name());
		} else if (seshStatus == Status.joining) {
			throw new CumExcpIllegalSeshStatus(seshName, seshStatus.name());
		} else if (seshStatus == Status.joined) {
		} else if (seshStatus == Status.lving) {
			throw new CumExcpIgnoreSeshStatus(seshName, seshStatus.name());
		} else if (seshStatus == Status.clsed) {
			throw new CumExcpIllegalSeshStatus(seshName, seshStatus.name());
		} else if (seshStatus == Status.dscned) {
			throw new CumExcpIllegalSeshStatus(seshName, seshStatus.name());
		}
	}

	protected void chnlCmdRcvedCheckStatus() throws CumExcpIllegalSeshStatus,
			CumExcpIgnoreSeshStatus {
		if (seshStatus == Status.init) {
			throw new CumExcpIllegalSeshStatus(seshName, seshStatus.name());
		} else if (seshStatus == Status.joining) {
			throw new CumExcpIllegalSeshStatus(seshName, seshStatus.name());
		} else if (seshStatus == Status.joined) {
		} else if (seshStatus == Status.lving) {
			throw new CumExcpIgnoreSeshStatus(seshName, seshStatus.name());
		} else if (seshStatus == Status.clsed) {
			throw new CumExcpIllegalSeshStatus(seshName, seshStatus.name());
		} else if (seshStatus == Status.dscned) {
			throw new CumExcpIllegalSeshStatus(seshName, seshStatus.name());
		}
	}

	protected void joinSeshCheckStatus() throws CumExcpIllegalSeshStatus {
		if (seshStatus == Status.init) {
		} else if (seshStatus == Status.joining) {
			throw new CumExcpIllegalSeshStatus(seshName, seshStatus.name());
		} else if (seshStatus == Status.joined) {
			throw new CumExcpIllegalSeshStatus(seshName, seshStatus.name());
		} else if (seshStatus == Status.lving) {
			throw new CumExcpIllegalSeshStatus(seshName, seshStatus.name());
		} else if (seshStatus == Status.clsed) {
			throw new CumExcpIllegalSeshStatus(seshName, seshStatus.name());
		} else if (seshStatus == Status.dscned) {
			throw new CumExcpIllegalSeshStatus(seshName, seshStatus.name());
		}
	}

	protected void lvSeshCheckStatus() throws CumExcpIllegalSeshStatus {
		if (seshStatus == Status.init) {
			throw new CumExcpIllegalSeshStatus(seshName, seshStatus.name());
		} else if (seshStatus == Status.joining) {
			throw new CumExcpIllegalSeshStatus(seshName, seshStatus.name());
		} else if (seshStatus == Status.joined) {
		} else if (seshStatus == Status.lving) {
			throw new CumExcpIllegalSeshStatus(seshName, seshStatus.name());
		} else if (seshStatus == Status.clsed) {
			throw new CumExcpIllegalSeshStatus(seshName, seshStatus.name());
		} else if (seshStatus == Status.dscned) {
			throw new CumExcpIllegalSeshStatus(seshName, seshStatus.name());
		}
	}

	protected void joinChnlCheckStatus() throws CumExcpIllegalSeshStatus {
		if (seshStatus == Status.init) {
			throw new CumExcpIllegalSeshStatus(seshName, seshStatus.name());
		} else if (seshStatus == Status.joining) {
			throw new CumExcpIllegalSeshStatus(seshName, seshStatus.name());
		} else if (seshStatus == Status.joined) {
		} else if (seshStatus == Status.lving) {
			throw new CumExcpIllegalSeshStatus(seshName, seshStatus.name());
		} else if (seshStatus == Status.clsed) {
			throw new CumExcpIllegalSeshStatus(seshName, seshStatus.name());
		} else if (seshStatus == Status.dscned) {
			throw new CumExcpIllegalSeshStatus(seshName, seshStatus.name());
		}
	}

	protected void rjctChnlCheckStatus() throws CumExcpIllegalSeshStatus {
		if (seshStatus == Status.init) {
			throw new CumExcpIllegalSeshStatus(seshName, seshStatus.name());
		} else if (seshStatus == Status.joining) {
			throw new CumExcpIllegalSeshStatus(seshName, seshStatus.name());
		} else if (seshStatus == Status.joined) {
		} else if (seshStatus == Status.lving) {
			throw new CumExcpIllegalSeshStatus(seshName, seshStatus.name());
		} else if (seshStatus == Status.clsed) {
			throw new CumExcpIllegalSeshStatus(seshName, seshStatus.name());
		} else if (seshStatus == Status.dscned) {
			throw new CumExcpIllegalSeshStatus(seshName, seshStatus.name());
		}
	}

	protected void lvChnlCheckStatus() throws CumExcpIllegalSeshStatus {
		if (seshStatus == Status.init) {
			throw new CumExcpIllegalSeshStatus(seshName, seshStatus.name());
		} else if (seshStatus == Status.joining) {
			throw new CumExcpIllegalSeshStatus(seshName, seshStatus.name());
		} else if (seshStatus == Status.joined) {
		} else if (seshStatus == Status.lving) {
			throw new CumExcpIllegalSeshStatus(seshName, seshStatus.name());
		} else if (seshStatus == Status.clsed) {
			throw new CumExcpIllegalSeshStatus(seshName, seshStatus.name());
		} else if (seshStatus == Status.dscned) {
			throw new CumExcpIllegalSeshStatus(seshName, seshStatus.name());
		}
	}

	protected AudChnlIntfForSesh getChnl(String chnlName)
			throws CumExcpChnlNotExist {
		AudChnlIntfForSesh chnl = chnls.get(chnlName);
		if (chnl == null) {
			throw new CumExcpChnlNotExist(seshName, chnlName);
		}
		return chnl;
	}
}
