/**
 * 
 */
package jp.happyhacking.cum.aud.chnlLyr;

import java.util.HashMap;

import jp.happyhacking.cum.aud.excp.CumExcpIllegalChnlStatus;
import jp.happyhacking70.cum.cmd.rsc.ChnlRscIntf;
import jp.happyhacking70.cum.cmd.rsc.ChnlRscJustName;

/**
 * @author happyhacking70@gmail.com
 * 
 */
abstract class AudChnlTestAbst {
	static final String chnlName = "testChnl";
	static final ChnlRscJustName rscA = new ChnlRscJustName("rscA");
	static final ChnlRscJustName rscB = new ChnlRscJustName("rscB");
	protected HashMap<String, ChnlRscIntf> rsces = new HashMap<String, ChnlRscIntf>();
	protected DummyAudSesh sesh;
	protected DummyChniView chnlView;

	protected AudChnl getChnl() {
		rsces.put(rscA.getName(), rscA);
		rsces.put(rscB.getName(), rscB);
		sesh = new DummyAudSesh();
		chnlView = new DummyChniView();
		AudChnl chnl = new AudChnl(chnlName, rsces, sesh, chnlView);

		return chnl;
	}

	protected AudChnl goto_joining() throws TestExcp {
		AudChnl chnl = getChnl();
		try {
			chnl.chnlJoining();
		} catch (CumExcpIllegalChnlStatus e) {
			e.printStackTrace();
			throw new TestExcp("chnlJoining");
		}
		return chnl;
	}

	protected AudChnl goto_joined() throws TestExcp {
		AudChnl chnl = goto_joining();
		try {
			chnl.chnlJoined();
		} catch (CumExcpIllegalChnlStatus e) {
			e.printStackTrace();
			throw new TestExcp("chnlJoining");
		}
		return chnl;
	}

	protected AudChnl goto_rjcting() throws TestExcp {
		AudChnl chnl = getChnl();
		try {
			chnl.chnlRjcting();
		} catch (CumExcpIllegalChnlStatus e) {
			e.printStackTrace();
			throw new TestExcp("chnlJoining");
		}
		return chnl;
	}

	protected AudChnl goto_lving() throws TestExcp {
		AudChnl chnl = goto_joined();
		try {
			chnl.lvChnl();
		} catch (CumExcpIllegalChnlStatus e) {
			e.printStackTrace();
			throw new TestExcp("chnlJoining");
		}
		return chnl;

	}

	protected AudChnl goto_clsed() throws TestExcp {
		AudChnl chnl = goto_joined();
		try {
			chnl.chnlClsed();
		} catch (CumExcpIllegalChnlStatus e) {
			e.printStackTrace();
			throw new TestExcp("chnlJoining");
		}
		return chnl;
	}

	protected AudChnl goto_dscned() throws TestExcp {
		AudChnl chnl = goto_joined();
		chnl.chnlDsconed();
		return chnl;

	}

	// abstract public void test_init();
	//
	// abstract public void test_joining();
	//
	// abstract public void test_joined();
	//
	// abstract public void test_rjcting();
	//
	// abstract public void test_lving();
	//
	// abstract public void test_clsed();
	//
	// abstract public void test_dscned();

}
