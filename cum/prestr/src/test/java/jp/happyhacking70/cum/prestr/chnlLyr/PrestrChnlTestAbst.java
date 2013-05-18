/**
 * 
 */
package jp.happyhacking70.cum.prestr.chnlLyr;

import java.util.HashMap;

import jp.happyhacking70.cum.cmd.rsc.ChnlRscIntf;
import jp.happyhacking70.cum.cmd.rsc.ChnlRscJustName;
import jp.happyhacking70.cum.excp.prestr.CumExcpAudExists;
import jp.happyhacking70.cum.excp.prestr.CumExcpChnlNotExist;
import jp.happyhacking70.cum.excp.prestr.CumExcpIllegalChnlStatus;
import jp.happyhacking70.cum.excp.prestr.CumExcpIllegalSeshStatus;
import jp.happyhacking70.cum.prestr.seshLyr.TestExcp;

/**
 * @author happyhacking70@gmail.com
 * 
 */
public class PrestrChnlTestAbst {

	protected static final String chnlName = "testChannel";
	protected static final String chnlType = "testChannelType";
	static final ChnlRscJustName rscA = new ChnlRscJustName("a");
	static final ChnlRscJustName rscB = new ChnlRscJustName("b");
	protected static final String audName = "testAudience";
	protected static final String audNameB = "testAudienceB";
	protected DummyPrestrSeshIntfForChnlView sesh;
	protected DummyPrestrChnlViewIntf chnlView;
	protected static final String actionName = "testAction";
	protected static final HashMap<String, String> params = new HashMap<String, String>();

	/**
	 * 
	 */
	public PrestrChnlTestAbst() {
		super();
	}

	protected PrestrChnl getChnl() {
		HashMap<String, ChnlRscIntf> rsces = new HashMap<String, ChnlRscIntf>();

		rsces.put(rscA.getName(), rscA);
		rsces.put(rscB.getName(), rscB);
		chnlView = new DummyPrestrChnlViewIntf();
		sesh = new DummyPrestrSeshIntfForChnlView();
		PrestrChnl chnl = new PrestrChnl(chnlType, chnlName, rsces, chnlView,
				sesh);

		return chnl;
	}

	protected PrestrChnl gotoReged() throws TestExcp {
		PrestrChnl chnl = getChnl();
		try {
			chnl.chnlReged();
		} catch (CumExcpIllegalChnlStatus e) {
			e.printStackTrace();
			throw new TestExcp("chnlReged");
		}

		try {
			chnl.audJoinedChnl(audName);
		} catch (CumExcpAudExists e) {
			e.printStackTrace();
			throw new TestExcp("audJoinedChnl");
		} catch (CumExcpIllegalChnlStatus e) {
			e.printStackTrace();
			throw new TestExcp("audJoinedChnl");
		}
		return chnl;
	}

	protected PrestrChnl gotoClsing() throws TestExcp {
		PrestrChnl chnl = gotoReged();
		try {
			chnl.clsChnl();
		} catch (CumExcpIllegalChnlStatus e) {
			e.printStackTrace();
			throw new TestExcp("clsChnl");
		} catch (CumExcpChnlNotExist e) {
			e.printStackTrace();
			throw new TestExcp("clsChnl");
		} catch (CumExcpIllegalSeshStatus e) {
			e.printStackTrace();
			throw new TestExcp("clsChnl");
		}
		return chnl;
	}

	protected PrestrChnl gotoClsed() throws TestExcp {
		PrestrChnl chnl = gotoClsing();
		try {
			chnl.chnlClsed();
		} catch (CumExcpIllegalChnlStatus e) {
			throw new TestExcp("chnlClsed");
		}
		return chnl;
	}

	protected PrestrChnl gotoDscned() throws TestExcp {
		PrestrChnl chnl = getChnl();
		try {
			chnl.discnded();
		} catch (CumExcpIllegalChnlStatus e) {
			throw new TestExcp("discnded");
		}
		return chnl;
	}
}