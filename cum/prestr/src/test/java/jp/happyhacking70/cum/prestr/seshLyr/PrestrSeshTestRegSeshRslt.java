/**
 * 
 */
package jp.happyhacking70.cum.prestr.seshLyr;

import jp.happyhacking70.cum.cmd.res.impl.ResCmdRegSesh;
import jp.happyhacking70.cum.excp.prestr.CumExcpIgnoreSeshStatus;
import jp.happyhacking70.cum.excp.prestr.CumExcpIllegalSeshStatus;

import org.junit.Test;

/**
 * @author happyhacking70@gmail.com
 * 
 */
public class PrestrSeshTestRegSeshRslt extends PrestrSeshTestAbst {

	@Test(expected = CumExcpIllegalSeshStatus.class)
	public void TEST_regSeshRslt_INIT() throws CumExcpIllegalSeshStatus,
			CumExcpIgnoreSeshStatus {
		PrestrSesh sesh = getSesh();
		sesh.regSeshRslt(ResCmdRegSesh.RsltTypes.Reged.name());
	}

	@Test
	public void TEST_regSeshRslt_REGING() throws TestExcp,
			CumExcpIllegalSeshStatus, CumExcpIgnoreSeshStatus {
		PrestrSesh sesh = gotoReging();
		sesh.regSeshRslt(ResCmdRegSesh.RsltTypes.Reged.name());
	}

	@Test(expected = CumExcpIllegalSeshStatus.class)
	public void TEST_regSeshRslt_REGED() throws TestExcp,
			CumExcpIllegalSeshStatus, CumExcpIgnoreSeshStatus {
		PrestrSesh sesh = gotoReged();
		sesh.regSeshRslt(ResCmdRegSesh.RsltTypes.Reged.name());

	}

	@Test(expected = CumExcpIllegalSeshStatus.class)
	public void TEST_regSeshRslt_CLSING() throws TestExcp,
			CumExcpIllegalSeshStatus, CumExcpIgnoreSeshStatus {
		PrestrSesh sesh = gotoClsing();
		sesh.regSeshRslt(ResCmdRegSesh.RsltTypes.Reged.name());

	}

	@Test(expected = CumExcpIllegalSeshStatus.class)
	public void TEST_regSeshRslt_CLSED() throws TestExcp,
			CumExcpIllegalSeshStatus, CumExcpIgnoreSeshStatus {
		PrestrSesh sesh = gotoClsed();
		sesh.regSeshRslt(ResCmdRegSesh.RsltTypes.Reged.name());

	}

	@Test(expected = CumExcpIgnoreSeshStatus.class)
	public void TEST_regSeshRslt_DISCNED() throws TestExcp,
			CumExcpIllegalSeshStatus, CumExcpIgnoreSeshStatus {
		PrestrSesh sesh = gotoDiscned();
		sesh.regSeshRslt(ResCmdRegSesh.RsltTypes.Reged.name());

	}

}
