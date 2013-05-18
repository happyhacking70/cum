/**
 * 
 */
package jp.happyhacking.cum.aud.seshLyr;

import java.util.HashMap;

import jp.happyhacking.cum.aud.chnlLyr.TestExcp;
import jp.happyhacking.cum.aud.excp.CumExcpChnlExists;
import jp.happyhacking.cum.aud.excp.CumExcpIgnoreChnlStatus;
import jp.happyhacking.cum.aud.excp.CumExcpIllegalChnlStatuMulti;
import jp.happyhacking.cum.aud.excp.CumExcpIllegalChnlStatus;
import jp.happyhacking.cum.aud.excp.CumExcpIllegalSeshStatus;
import jp.happyhacking70.cum.cmd.res.impl.ResCmdJoinChnl;
import jp.happyhacking70.cum.cmd.rsc.ChnlRscIntf;
import jp.happyhacking70.cum.cmd.rsc.ChnlRscJustName;

/**
 * @author happyhacking70@gmail.com
 * 
 */
public class AudSeshTestAbst {

	static final String seshName = "testSession";
	static final String audNameA = "testAudienceA";
	static final String chnlNameA = "testChannelA";
	static final String chnlType = "testChannelType";
	static final String actionName = "testAction";
	static final String rslt = ResCmdJoinChnl.RsltTypes.ChnlNotExist.name();
	protected DummySeshView seshView;
	protected DummyAdptr adptr;;
	static final ChnlRscJustName rscA = new ChnlRscJustName("rscA");
	static final ChnlRscJustName rscB = new ChnlRscJustName("rscB");
	static final HashMap<String, String> params = new HashMap<String, String>();
	protected HashMap<String, ChnlRscIntf> rsces = new HashMap<String, ChnlRscIntf>();

	/**
	 * 
	 */
	public AudSeshTestAbst() {
		super();
	}

	protected AudSesh getSesh() {
		rsces.put(rscA.getName(), rscA);
		rsces.put(rscB.getName(), rscB);
		seshView = new DummySeshView();
		adptr = new DummyAdptr();
		return new AudSesh(seshName, audNameA, seshView, adptr);
	}

	protected AudSesh gotoJoining() throws TestExcp {
		AudSesh sesh = getSesh();

		try {
			sesh.joinSesh();
		} catch (CumExcpIllegalSeshStatus e) {
			e.printStackTrace();
			throw new TestExcp("joinSesh");
		}
		return sesh;
	}

	protected AudSesh gotoJoined() throws TestExcp {
		AudSesh sesh = gotoJoining();

		try {
			sesh.seshJoined();
			sesh.chnlReged(chnlType, chnlNameA, rsces);
		} catch (CumExcpIllegalSeshStatus e) {
			e.printStackTrace();
			throw new TestExcp("seshJoined");
		} catch (CumExcpChnlExists e) {
			e.printStackTrace();
			throw new TestExcp("seshJoined");
		}
		return sesh;
	}

	protected AudSesh gotoLving() throws TestExcp {
		AudSesh sesh = gotoJoined();

		try {
			sesh.lvSesh();
		} catch (CumExcpIllegalSeshStatus e) {
			e.printStackTrace();
			throw new TestExcp("lvSesh");
		} catch (CumExcpIllegalChnlStatus e) {
			e.printStackTrace();
			throw new TestExcp("lvSesh");
		} catch (CumExcpIgnoreChnlStatus e) {
			e.printStackTrace();
			throw new TestExcp("lvSesh");
		}
		return sesh;
	}

	protected AudSesh gotoClsed() throws TestExcp {
		AudSesh sesh = gotoJoined();

		try {
			sesh.seshClsed();
		} catch (CumExcpIllegalSeshStatus e) {
			e.printStackTrace();
			throw new TestExcp("lvSesh");
		} catch (CumExcpIllegalChnlStatuMulti e) {
			e.printStackTrace();
			throw new TestExcp("lvSesh");
		}
		return sesh;
	}

	protected AudSesh gotoDscned() throws TestExcp {
		AudSesh sesh = gotoJoined();

		try {
			sesh.seshDscned();
		} catch (CumExcpIllegalSeshStatus e) {
			e.printStackTrace();
			throw new TestExcp("lvSesh");
		} catch (CumExcpIllegalChnlStatuMulti e) {
			e.printStackTrace();
			throw new TestExcp("lvSesh");
		}
		return sesh;
	}

}