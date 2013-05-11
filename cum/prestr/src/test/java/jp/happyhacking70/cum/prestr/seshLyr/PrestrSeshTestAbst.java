/**
 * 
 */
package jp.happyhacking70.cum.prestr.seshLyr;

import java.util.HashMap;

import jp.happyhacking70.cum.cmd.res.impl.ResCmdClsSesh;
import jp.happyhacking70.cum.cmd.res.impl.ResCmdRegChnl;
import jp.happyhacking70.cum.cmd.res.impl.ResCmdRegSesh;
import jp.happyhacking70.cum.cmd.rsc.ChnlRscIntf;
import jp.happyhacking70.cum.cmd.rsc.ChnlRscJustName;
import jp.happyhacking70.cum.excp.CumExcpAudExists;
import jp.happyhacking70.cum.excp.prestr.CumExcpAudExist;
import jp.happyhacking70.cum.excp.prestr.CumExcpAudNotExist;
import jp.happyhacking70.cum.excp.prestr.CumExcpChnlExists;
import jp.happyhacking70.cum.excp.prestr.CumExcpChnlNotExist;
import jp.happyhacking70.cum.excp.prestr.CumExcpIgnoreSeshStatus;
import jp.happyhacking70.cum.excp.prestr.CumExcpIllegalChnlStatuMulti;
import jp.happyhacking70.cum.excp.prestr.CumExcpIllegalChnlStatus;
import jp.happyhacking70.cum.excp.prestr.CumExcpIllegalSeshStatus;

/**
 * @author happyhacking70@gmail.com
 *
 */
public class PrestrSeshTestAbst {

	protected static final String seshName = "testSession";
	protected static final String chnlNameA = "testChannelA";
	static final String chnlNameB = "testChannelB";
	protected static final String chnlNameC = "testChannelC";
	static final ChnlRscJustName rscA = new ChnlRscJustName("a");
	static final ChnlRscJustName rscB = new ChnlRscJustName("b");
	protected static final String audNameA = "testAudienceA";
	static final String audNameB = "testAudienceB";
	protected static final String actionName = "testAction";
	protected static final HashMap<String, String> params = new HashMap<String, String>();
	protected DummyAdtr adptr = null;
	protected DummySeshView seshView = null;
	protected HashMap<String, ChnlRscIntf> rsces;
	protected DummyChnlView chnlViewA;
	protected DummyChnlView chnlViewB;
	protected DummyChnlView chnlViewC;

	/**
	 * 
	 */
	public PrestrSeshTestAbst() {
		super();
	}

	protected PrestrSesh getSesh() {
		rsces = new HashMap<String, ChnlRscIntf>();
	
		rsces.put(rscA.getName(), rscA);
		rsces.put(rscB.getName(), rscB);
	
		adptr = new DummyAdtr();
		seshView = new DummySeshView();
		chnlViewA = new DummyChnlView();
		chnlViewB = new DummyChnlView();
		chnlViewC = new DummyChnlView();
	
		PrestrSesh sesh = new PrestrSesh(seshName, seshView, adptr);
	
		return sesh;
	}

	protected PrestrSesh gotoReging() throws TestExcp {
		PrestrSesh sesh = getSesh();
	
		try {
			sesh.regSesh();
		} catch (CumExcpIllegalSeshStatus e) {
			e.printStackTrace();
			throw new TestExcp("regSesh");
		}
	
		return sesh;
	}

	protected PrestrSesh gotoReged() throws TestExcp {
		PrestrSesh sesh = gotoReging();
	
		try {
			sesh.regSeshRslt(ResCmdRegSesh.RsltTypes.Reged.name());
			sesh.regChnl(chnlNameA, rsces, chnlViewA);
			sesh.regChnl(chnlNameB, rsces, chnlViewB);
			sesh.regChnlRslt(chnlNameA, ResCmdRegChnl.RsltTypes.Reged.name());
			sesh.audJoinedSesh(audNameA);
			sesh.audJoinedSesh(audNameB);
			sesh.audJoinedChnl(chnlNameA, audNameA);
			sesh.audRjctedChnl(chnlNameA, audNameB);
		} catch (CumExcpIllegalSeshStatus e) {
			e.printStackTrace();
			throw new TestExcp("regSeshRslt");
		} catch (CumExcpIgnoreSeshStatus e) {
			e.printStackTrace();
			throw new TestExcp("regSeshRslt");
		} catch (CumExcpChnlExists e) {
			e.printStackTrace();
			throw new TestExcp("regSeshRslt");
		} catch (CumExcpAudNotExist e) {
			e.printStackTrace();
			throw new TestExcp("regSeshRslt");
		} catch (CumExcpAudExist e) {
			e.printStackTrace();
			throw new TestExcp("regSeshRslt");
		} catch (CumExcpAudExists e) {
			e.printStackTrace();
			throw new TestExcp("regSeshRslt");
		} catch (CumExcpIllegalChnlStatus e) {
			e.printStackTrace();
			throw new TestExcp("regSeshRslt");
		} catch (CumExcpChnlNotExist e) {
			e.printStackTrace();
			throw new TestExcp("regSeshRslt");
		}
	
		return sesh;
	}

	protected PrestrSesh gotoClsing() throws TestExcp {
		PrestrSesh sesh = gotoReged();
	
		try {
			sesh.clsSesh();
		} catch (CumExcpIllegalSeshStatus e) {
			e.printStackTrace();
			throw new TestExcp("clsSesh");
		} catch (CumExcpIllegalChnlStatuMulti e) {
			e.printStackTrace();
			throw new TestExcp("clsSesh");
		}
	
		return sesh;
	}

	protected PrestrSesh gotoClsed() throws TestExcp {
		PrestrSesh sesh = gotoClsing();
	
		try {
			sesh.clsSeshRslt(ResCmdClsSesh.RsltTypes.Clsed.name());
		} catch (CumExcpIllegalSeshStatus e) {
			e.printStackTrace();
			throw new TestExcp("clsSeshRslt");
		} catch (CumExcpIgnoreSeshStatus e) {
			e.printStackTrace();
			throw new TestExcp("clsSeshRslt");
		}
	
		return sesh;
	}

	protected PrestrSesh gotoDiscned() throws TestExcp {
		PrestrSesh sesh = gotoReging();
	
		try {
			sesh.discned();
		} catch (CumExcpIllegalSeshStatus e) {
			e.printStackTrace();
			throw new TestExcp("discned");
		}
	
		return sesh;
	}

}