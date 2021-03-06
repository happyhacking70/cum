/**
 * 
 */
package jp.happyhacking.cum.aud.chnlLyr;

import java.util.HashMap;

import jp.happyhacking.cum.aud.audLyr.AudChnlViewIntf;
import jp.happyhacking.cum.aud.excp.CumExcpChnlNotExist;
import jp.happyhacking.cum.aud.excp.CumExcpIgnoreChnlStatus;
import jp.happyhacking.cum.aud.excp.CumExcpIllegalChnlStatus;
import jp.happyhacking.cum.aud.excp.CumExcpIllegalSeshStatus;
import jp.happyhacking.cum.aud.seshLyr.AudSeshChnlIntf;
import jp.happyhacking70.cum.cmd.rsc.ChnlRscIntf;

/**
 * Audience Channel<BR>
 * <BR>
 * State Transition Diagram of Aud Channel <BR>
 * <img src="doc-files/AudChnlStateTransitionDiagram.bmp"
 * alt="State Transition Diagram of Aud Channel"/> <BR>
 * Channel Status and Methods<BR>
 * <iframe src="doc-files/AudChnlStateAndMethods.html" width="1700"
 * height="300"></iframe>
 * 
 * @author happyhacking70@gmail.com
 * 
 */
public class AudChnl implements AudChnlIntfForSesh, AudChnlIntfForView {
	/** name of channel */
	protected String chnlName;
	/** channel resources */
	protected HashMap<String, ChnlRscIntf> rsces;
	/** channel state */
	protected Status chnlStatus = Status.init;
	/** channel view */
	protected AudChnlViewIntf chnlView;
	/** Audience Session */
	protected AudSeshChnlIntf sesh;
	/** Type Name of Channel */
	protected String chnlType;

	/**
	 * 
	 * status of channel
	 * 
	 * @author happyhacking70@gmail.com
	 * 
	 */
	public enum Status {
		/**
		 * initial state. When
		 * {@link AudSesh#chnlReged(String, String, HashMap)} is invoked, this
		 * class is instantiated and should be set to this. state
		 */
		init,
		/** under joining */
		joining,
		/** joined */
		joined,
		/** rejecting */
		rjcting,
		/** leaving */
		lving,
		/** closed */
		clsed,
		/** disconnected */
		dscned
	}

	/**
	 * @param chnlName
	 *            name of channel
	 * @param rsces
	 *            channel resources
	 */
	public AudChnl(String chnlType, String chnlName,
			HashMap<String, ChnlRscIntf> rsces, AudSeshChnlIntf sesh,
			AudChnlViewIntf chnlView) {
		super();
		this.chnlName = chnlName;
		this.rsces = rsces;
		this.sesh = sesh;
		this.chnlView = chnlView;
		this.chnlType = chnlType;
	}

	public Status getStatus() {
		return chnlStatus;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * jp.happyhacking.cum.aud.chnlLyr.AudChnlIntfForSesh#chnlJoined(jp.happyhacking
	 * .cum.aud.audLyr.AudChnlViewIntf)
	 */
	@Override
	synchronized public void chnlJoined() throws CumExcpIllegalChnlStatus {
		checkStatuschnlJoined();
		chnlStatus = Status.joined;

		for (String rscName : rsces.keySet()) {
			rsces.put(rscName, sesh.fetchRsc(chnlType, chnlName,
					rsces.get(rscName).getName()));
		}

		chnlView.chnlJoined();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see jp.happyhacking.cum.aud.chnlLyr.AudChnlIntfForView#lvChnl()
	 */
	@Override
	synchronized public void lvChnl() throws CumExcpIllegalChnlStatus,
			CumExcpIllegalSeshStatus, CumExcpChnlNotExist {
		checkStatuslvChnl();
		chnlStatus = Status.lving;
		sesh.lvChnl(chnlName);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see jp.happyhacking.cum.aud.chnlLyr.AudChnlIntfForSesh#chnlClsed()
	 */
	@Override
	synchronized public void chnlClsed() throws CumExcpIllegalChnlStatus {
		checkStatuschnlClsed();
		chnlStatus = Status.clsed;
		chnlView.chnlClsed();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see jp.happyhacking.cum.aud.chnlLyr.AudChnlIntfForSesh#seshClsed()
	 */
	@Override
	synchronized public void seshClsed() throws CumExcpIllegalChnlStatus {
		try {
			checkStatusseshClsed();
			chnlStatus = Status.clsed;
			chnlView.seshClsed();
		} catch (CumExcpIgnoreChnlStatus e) {
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * jp.happyhacking.cum.aud.chnlLyr.AudChnlIntfForSesh#chnlJoinedFailed(java
	 * .lang.String)
	 */
	@Override
	synchronized public void joinChnlFailed(String rslt)
			throws CumExcpIllegalChnlStatus {
		// since join channel is session level, channel just change status
		// without notifying channel view.
		checkStatuschnlJoinedFailed();
		chnlStatus = Status.init;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * jp.happyhacking.cum.aud.chnlLyr.AudChnlIntfForSesh#chnlCmdRcved(java.
	 * lang.String, java.util.HashMap)
	 */
	@Override
	synchronized public void chnlCmdRcved(String actionName,
			HashMap<String, String> params) throws CumExcpIllegalChnlStatus {
		try {
			checkStatuschnlCmdRcved();
			chnlView.chnlCmdRcved(actionName, params);
		} catch (CumExcpIgnoreChnlStatus e) {
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see jp.happyhacking.cum.aud.chnlLyr.AudChnlIntfForSesh#chnlLft()
	 */
	@Override
	synchronized public void chnlLft() throws CumExcpIllegalChnlStatus {
		checkStatuschnlLft();
		chnlStatus = Status.init;
		chnlView.chnlLft();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * jp.happyhacking.cum.aud.chnlLyr.AudChnlIntfForSesh#chnlLftFailed(java
	 * .lang.String)
	 */
	@Override
	synchronized public void chnlLvFailed(String rslt)
			throws CumExcpIllegalChnlStatus {
		checkStatuschnlLvFailed();
		chnlStatus = Status.init;
		chnlView.chnlLvFailed(rslt);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see jp.happyhacking.cum.aud.chnlLyr.AudChnlIntfForSesh#chnlJoining()
	 */
	@Override
	synchronized public void chnlJoining() throws CumExcpIllegalChnlStatus {
		checkStatuschnlJoining();
		chnlStatus = Status.joining;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see jp.happyhacking.cum.aud.chnlLyr.AudChnlIntfForSesh#chnlRjcting()
	 */
	@Override
	synchronized public void chnlRjcting() throws CumExcpIllegalChnlStatus {
		checkStatuschnlRjcting();
		chnlStatus = Status.rjcting;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see jp.happyhacking.cum.aud.chnlLyr.AudChnlIntfForSesh#chnlRjected()
	 */
	@Override
	synchronized public void chnlRjcted() throws CumExcpIllegalChnlStatus {
		checkStatuschnlRjected();
		chnlStatus = Status.init;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * jp.happyhacking.cum.aud.chnlLyr.AudChnlIntfForSesh#chnlRjctFailed(java
	 * .lang.String)
	 */
	@Override
	synchronized public void chnlRjctFailed() throws CumExcpIllegalChnlStatus {
		checkStatuschnlRjctFailed();
		chnlStatus = Status.init;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see jp.happyhacking.cum.aud.chnlLyr.AudChnlIntfForSesh#chnlDscned()
	 */
	@Override
	public void chnlDscned() throws CumExcpIllegalChnlStatus {
		checkStatuschnlDscned();
		chnlStatus = Status.dscned;
		chnlView.chnlDscned();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see jp.happyhacking.cum.aud.chnlLyr.AudChnlIntfForSesh#seshLving()
	 */
	@Override
	synchronized public void seshLving() throws CumExcpIllegalChnlStatus {
		try {
			checkStatusSeshLving();
			chnlView.seshLving();
			chnlStatus = Status.init;
		} catch (CumExcpIgnoreChnlStatus e) {
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see jp.happyhacking.cum.aud.chnlLyr.AudChnlIntfForSesh#getChnlType()
	 */
	@Override
	public String getChnlType() {
		return chnlType;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see jp.happyhacking.cum.aud.chnlLyr.AudChnlIntfForSesh#getRsces()
	 */
	@Override
	public HashMap<String, ChnlRscIntf> getRsces() {
		return rsces;
	}

	void checkStatuslvChnl() throws CumExcpIllegalChnlStatus {
		if (chnlStatus == Status.init) {
			throw new CumExcpIllegalChnlStatus(chnlName, chnlStatus.name());
		} else if (chnlStatus == Status.joining) {
			throw new CumExcpIllegalChnlStatus(chnlName, chnlStatus.name());
		} else if (chnlStatus == Status.joined) {
		} else if (chnlStatus == Status.lving) {
			throw new CumExcpIllegalChnlStatus(chnlName, chnlStatus.name());
		} else if (chnlStatus == Status.clsed) {
			throw new CumExcpIllegalChnlStatus(chnlName, chnlStatus.name());
		} else if (chnlStatus == Status.dscned) {
			throw new CumExcpIllegalChnlStatus(chnlName, chnlStatus.name());
		} else if (chnlStatus == Status.rjcting) {
			throw new CumExcpIllegalChnlStatus(chnlName, chnlStatus.name());
		}
	}

	void checkStatuschnlClsed() throws CumExcpIllegalChnlStatus {
		if (chnlStatus == Status.init) {
		} else if (chnlStatus == Status.joining) {
		} else if (chnlStatus == Status.joined) {
		} else if (chnlStatus == Status.lving) {
		} else if (chnlStatus == Status.clsed) {
			throw new CumExcpIllegalChnlStatus(chnlName, chnlStatus.name());
		} else if (chnlStatus == Status.dscned) {
			throw new CumExcpIllegalChnlStatus(chnlName, chnlStatus.name());
		} else if (chnlStatus == Status.rjcting) {
		}
	}

	void checkStatusseshClsed() throws CumExcpIgnoreChnlStatus,
			CumExcpIllegalChnlStatus {
		if (chnlStatus == Status.init) {
		} else if (chnlStatus == Status.joining) {
		} else if (chnlStatus == Status.joined) {
		} else if (chnlStatus == Status.lving) {
		} else if (chnlStatus == Status.clsed) {
			throw new CumExcpIgnoreChnlStatus(chnlName, chnlStatus.name());
		} else if (chnlStatus == Status.dscned) {
			throw new CumExcpIllegalChnlStatus(chnlName, chnlStatus.name());
		} else if (chnlStatus == Status.rjcting) {
		}
	}

	void checkStatuschnlJoining() throws CumExcpIllegalChnlStatus {
		if (chnlStatus == Status.init) {
		} else if (chnlStatus == Status.joining) {
			throw new CumExcpIllegalChnlStatus(chnlName, chnlStatus.name());
		} else if (chnlStatus == Status.joined) {
			throw new CumExcpIllegalChnlStatus(chnlName, chnlStatus.name());
		} else if (chnlStatus == Status.lving) {
			throw new CumExcpIllegalChnlStatus(chnlName, chnlStatus.name());
		} else if (chnlStatus == Status.clsed) {
			throw new CumExcpIllegalChnlStatus(chnlName, chnlStatus.name());
		} else if (chnlStatus == Status.dscned) {
			throw new CumExcpIllegalChnlStatus(chnlName, chnlStatus.name());
		} else if (chnlStatus == Status.rjcting) {
			throw new CumExcpIllegalChnlStatus(chnlName, chnlStatus.name());
		}
	}

	void checkStatuschnlJoined() throws CumExcpIllegalChnlStatus {
		if (chnlStatus == Status.init) {
			throw new CumExcpIllegalChnlStatus(chnlName, chnlStatus.name());
		} else if (chnlStatus == Status.joining) {
		} else if (chnlStatus == Status.joined) {
			throw new CumExcpIllegalChnlStatus(chnlName, chnlStatus.name());
		} else if (chnlStatus == Status.lving) {
			throw new CumExcpIllegalChnlStatus(chnlName, chnlStatus.name());
		} else if (chnlStatus == Status.clsed) {
			throw new CumExcpIllegalChnlStatus(chnlName, chnlStatus.name());
		} else if (chnlStatus == Status.dscned) {
			throw new CumExcpIllegalChnlStatus(chnlName, chnlStatus.name());
		} else if (chnlStatus == Status.rjcting) {
			throw new CumExcpIllegalChnlStatus(chnlName, chnlStatus.name());
		}
	}

	void checkStatuschnlJoinedFailed() throws CumExcpIllegalChnlStatus {
		if (chnlStatus == Status.init) {
			throw new CumExcpIllegalChnlStatus(chnlName, chnlStatus.name());
		} else if (chnlStatus == Status.joining) {
		} else if (chnlStatus == Status.joined) {
			throw new CumExcpIllegalChnlStatus(chnlName, chnlStatus.name());
		} else if (chnlStatus == Status.lving) {
			throw new CumExcpIllegalChnlStatus(chnlName, chnlStatus.name());
		} else if (chnlStatus == Status.clsed) {
			throw new CumExcpIllegalChnlStatus(chnlName, chnlStatus.name());
		} else if (chnlStatus == Status.dscned) {
			throw new CumExcpIllegalChnlStatus(chnlName, chnlStatus.name());
		} else if (chnlStatus == Status.rjcting) {
			throw new CumExcpIllegalChnlStatus(chnlName, chnlStatus.name());
		}
	}

	void checkStatuschnlRjcting() throws CumExcpIllegalChnlStatus {
		if (chnlStatus == Status.init) {
		} else if (chnlStatus == Status.joining) {
			throw new CumExcpIllegalChnlStatus(chnlName, chnlStatus.name());
		} else if (chnlStatus == Status.joined) {
			throw new CumExcpIllegalChnlStatus(chnlName, chnlStatus.name());
		} else if (chnlStatus == Status.lving) {
			throw new CumExcpIllegalChnlStatus(chnlName, chnlStatus.name());
		} else if (chnlStatus == Status.clsed) {
			throw new CumExcpIllegalChnlStatus(chnlName, chnlStatus.name());
		} else if (chnlStatus == Status.dscned) {
			throw new CumExcpIllegalChnlStatus(chnlName, chnlStatus.name());
		} else if (chnlStatus == Status.rjcting) {
			throw new CumExcpIllegalChnlStatus(chnlName, chnlStatus.name());
		}
	}

	void checkStatuschnlRjected() throws CumExcpIllegalChnlStatus {
		if (chnlStatus == Status.init) {
			throw new CumExcpIllegalChnlStatus(chnlName, chnlStatus.name());
		} else if (chnlStatus == Status.joining) {
			throw new CumExcpIllegalChnlStatus(chnlName, chnlStatus.name());
		} else if (chnlStatus == Status.joined) {
			throw new CumExcpIllegalChnlStatus(chnlName, chnlStatus.name());
		} else if (chnlStatus == Status.lving) {
			throw new CumExcpIllegalChnlStatus(chnlName, chnlStatus.name());
		} else if (chnlStatus == Status.clsed) {
			throw new CumExcpIllegalChnlStatus(chnlName, chnlStatus.name());
		} else if (chnlStatus == Status.dscned) {
			throw new CumExcpIllegalChnlStatus(chnlName, chnlStatus.name());
		} else if (chnlStatus == Status.rjcting) {
		}
	}

	void checkStatuschnlRjctFailed() throws CumExcpIllegalChnlStatus {
		if (chnlStatus == Status.init) {
			throw new CumExcpIllegalChnlStatus(chnlName, chnlStatus.name());
		} else if (chnlStatus == Status.joining) {
			throw new CumExcpIllegalChnlStatus(chnlName, chnlStatus.name());
		} else if (chnlStatus == Status.joined) {
			throw new CumExcpIllegalChnlStatus(chnlName, chnlStatus.name());
		} else if (chnlStatus == Status.lving) {
			throw new CumExcpIllegalChnlStatus(chnlName, chnlStatus.name());
		} else if (chnlStatus == Status.clsed) {
			throw new CumExcpIllegalChnlStatus(chnlName, chnlStatus.name());
		} else if (chnlStatus == Status.dscned) {
			throw new CumExcpIllegalChnlStatus(chnlName, chnlStatus.name());
		} else if (chnlStatus == Status.rjcting) {
		}
	}

	void checkStatuschnlLft() throws CumExcpIllegalChnlStatus {
		if (chnlStatus == Status.init) {
			throw new CumExcpIllegalChnlStatus(chnlName, chnlStatus.name());
		} else if (chnlStatus == Status.joining) {
			throw new CumExcpIllegalChnlStatus(chnlName, chnlStatus.name());
		} else if (chnlStatus == Status.joined) {
			throw new CumExcpIllegalChnlStatus(chnlName, chnlStatus.name());
		} else if (chnlStatus == Status.lving) {
		} else if (chnlStatus == Status.clsed) {
			throw new CumExcpIllegalChnlStatus(chnlName, chnlStatus.name());
		} else if (chnlStatus == Status.dscned) {
			throw new CumExcpIllegalChnlStatus(chnlName, chnlStatus.name());
		} else if (chnlStatus == Status.rjcting) {
			throw new CumExcpIllegalChnlStatus(chnlName, chnlStatus.name());
		}
	}

	void checkStatuschnlLvFailed() throws CumExcpIllegalChnlStatus {
		if (chnlStatus == Status.init) {
			throw new CumExcpIllegalChnlStatus(chnlName, chnlStatus.name());
		} else if (chnlStatus == Status.joining) {
			throw new CumExcpIllegalChnlStatus(chnlName, chnlStatus.name());
		} else if (chnlStatus == Status.joined) {
			throw new CumExcpIllegalChnlStatus(chnlName, chnlStatus.name());
		} else if (chnlStatus == Status.lving) {
		} else if (chnlStatus == Status.clsed) {
			throw new CumExcpIllegalChnlStatus(chnlName, chnlStatus.name());
		} else if (chnlStatus == Status.dscned) {
			throw new CumExcpIllegalChnlStatus(chnlName, chnlStatus.name());
		} else if (chnlStatus == Status.rjcting) {
			throw new CumExcpIllegalChnlStatus(chnlName, chnlStatus.name());
		}
	}

	void checkStatuschnlCmdRcved() throws CumExcpIllegalChnlStatus,
			CumExcpIgnoreChnlStatus {
		if (chnlStatus == Status.init) {
			throw new CumExcpIllegalChnlStatus(chnlName, chnlStatus.name());
		} else if (chnlStatus == Status.joining) {
		} else if (chnlStatus == Status.joined) {
		} else if (chnlStatus == Status.lving) {
			throw new CumExcpIgnoreChnlStatus(chnlName, chnlStatus.name());
		} else if (chnlStatus == Status.clsed) {
			throw new CumExcpIgnoreChnlStatus(chnlName, chnlStatus.name());
		} else if (chnlStatus == Status.dscned) {
			throw new CumExcpIllegalChnlStatus(chnlName, chnlStatus.name());
		} else if (chnlStatus == Status.rjcting) {
			throw new CumExcpIllegalChnlStatus(chnlName, chnlStatus.name());
		}
	}

	void checkStatuschnlDscned() throws CumExcpIllegalChnlStatus {
		if (chnlStatus == Status.init) {
		} else if (chnlStatus == Status.joining) {
		} else if (chnlStatus == Status.joined) {
		} else if (chnlStatus == Status.lving) {
		} else if (chnlStatus == Status.clsed) {
			throw new CumExcpIllegalChnlStatus(chnlName, chnlStatus.name());
		} else if (chnlStatus == Status.dscned) {
			throw new CumExcpIllegalChnlStatus(chnlName, chnlStatus.name());
		} else if (chnlStatus == Status.rjcting) {
		}
	}

	void checkStatusSeshLving() throws CumExcpIllegalChnlStatus,
			CumExcpIgnoreChnlStatus {
		if (chnlStatus == Status.init) {
		} else if (chnlStatus == Status.joining) {
		} else if (chnlStatus == Status.joined) {
		} else if (chnlStatus == Status.lving) {
		} else if (chnlStatus == Status.clsed) {
			throw new CumExcpIgnoreChnlStatus(chnlName, chnlStatus.name());
		} else if (chnlStatus == Status.dscned) {
			throw new CumExcpIllegalChnlStatus(chnlName, chnlStatus.name());
		} else if (chnlStatus == Status.rjcting) {
		}
	}

}
