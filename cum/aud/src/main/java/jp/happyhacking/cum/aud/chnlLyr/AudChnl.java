/**
 * 
 */
package jp.happyhacking.cum.aud.chnlLyr;

import java.util.HashMap;

import jp.happyhacking.cum.aud.audLyr.AudChnlViewIntf;
import jp.happyhacking.cum.aud.excp.CumExcpIgnoreChnlStatus;
import jp.happyhacking.cum.aud.excp.CumExcpIllegalChnlStatus;
import jp.happyhacking.cum.aud.seshLyr.AudSesh;
import jp.happyhacking.cum.aud.seshLyr.AudSeshIntf;
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
	protected AudSeshIntf sesh;

	/**
	 * 
	 * status of channel
	 * 
	 * @author happyhacking70@gmail.com
	 * 
	 */
	public enum Status {
		/**
		 * initial state. When {@link AudSesh#chnlReged(String, HashMap)} is
		 * invoked, this class is instantiated and should be set to this. state
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
	public AudChnl(String chnlName, HashMap<String, ChnlRscIntf> rsces,
			AudSeshIntf sesh, AudChnlViewIntf chnlView) {
		super();
		this.chnlName = chnlName;
		this.rsces = rsces;
		this.sesh = sesh;
		this.chnlView = chnlView;
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
	public void chnlJoined(AudChnlViewIntf chnlView)
			throws CumExcpIllegalChnlStatus {
		checkStatuschnlJoined();
		this.chnlView = chnlView;
		chnlView.chnlJoined();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see jp.happyhacking.cum.aud.chnlLyr.AudChnlIntfForView#lvChnl()
	 */
	@Override
	public void lvChnl() throws CumExcpIllegalChnlStatus {
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
	public void chnlClsed() throws CumExcpIllegalChnlStatus {
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
	public void seshClsed() throws CumExcpIgnoreChnlStatus,
			CumExcpIllegalChnlStatus {
		checkStatusseshClsed();
		chnlStatus = Status.clsed;
		chnlView.seshClsed();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see jp.happyhacking.cum.aud.chnlLyr.AudChnlIntfForSesh#chnlJoined()
	 */
	public void chnlJoined() throws CumExcpIllegalChnlStatus {
		checkStatuschnlJoined();
		chnlStatus = Status.joined;
		chnlView.chnlJoined();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * jp.happyhacking.cum.aud.chnlLyr.AudChnlIntfForSesh#chnlJoinedFailed(java
	 * .lang.String)
	 */
	@Override
	public void chnlJoinedFailed(String rslt) throws CumExcpIllegalChnlStatus {
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
	public void chnlCmdRcved(String actionName, HashMap<String, String> params)
			throws CumExcpIllegalChnlStatus, CumExcpIgnoreChnlStatus {
		checkStatuschnlCmdRcved();
		chnlView.chnlCmdRcved(actionName, params);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see jp.happyhacking.cum.aud.chnlLyr.AudChnlIntfForSesh#chnlLft()
	 */
	@Override
	public void chnlLft() throws CumExcpIllegalChnlStatus {
		checkStatuschnlLft();
		chnlStatus = Status.init;
		chnlView.chnlLeft();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * jp.happyhacking.cum.aud.chnlLyr.AudChnlIntfForSesh#chnlLftFailed(java
	 * .lang.String)
	 */
	@Override
	public void chnlLvFailed(String rslt) throws CumExcpIllegalChnlStatus {
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
	public void chnlJoining() throws CumExcpIllegalChnlStatus {
		checkStatuschnlJoining();
		chnlStatus = Status.joining;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see jp.happyhacking.cum.aud.chnlLyr.AudChnlIntfForSesh#chnlRjcting()
	 */
	@Override
	public void chnlRjcting() throws CumExcpIllegalChnlStatus {
		checkStatuschnlRjcting();
		chnlStatus = Status.rjcting;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see jp.happyhacking.cum.aud.chnlLyr.AudChnlIntfForSesh#chnlRjected()
	 */
	@Override
	public void chnlRjcted() throws CumExcpIllegalChnlStatus {
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
	public void chnlRjctFailed(String rslt) throws CumExcpIllegalChnlStatus {
		checkStatuschnlRjctFailed();
		chnlStatus = Status.init;

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

	/*
	 * (non-Javadoc)
	 * 
	 * @see jp.happyhacking.cum.aud.chnlLyr.AudChnlIntfForSesh#chnlDscned()
	 */
	@Override
	public void chnlDsconed() throws CumExcpIllegalChnlStatus {
		checkStatuschnlDscned();
		chnlStatus = Status.dscned;
		chnlView.chnlDscned();
	}

}
