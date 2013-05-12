/**
 * 
 */
package jp.happyhacking70.cum.prestr.seshLyr;

import jp.happyhacking70.cum.cmd.res.impl.ResCmdClsSesh;
import jp.happyhacking70.cum.excp.prestr.CumExcpIgnoreSeshStatus;
import jp.happyhacking70.cum.excp.prestr.CumExcpIllegalSeshStatus;

import org.junit.Test;

/**
 * @author happyhacking70@gmail.com
 * 
 */
public class PrestrSeshClsSeshRsltTest extends PrestrSeshAbstTest {

	@Test(expected = CumExcpIllegalSeshStatus.class)
	public void TEST_clsSeshRslt_INIT() throws CumExcpIllegalSeshStatus,
			CumExcpIgnoreSeshStatus {
		PrestrSesh sesh = getSesh();

		sesh.clsSeshRslt(ResCmdClsSesh.RsltTypes.Clsed.name());

	}

	@Test(expected = CumExcpIllegalSeshStatus.class)
	public void TEST_clsSeshRslt_REGING() throws TestExcp,
			CumExcpIllegalSeshStatus, CumExcpIgnoreSeshStatus {
		PrestrSesh sesh = gotoReging();

		sesh.clsSeshRslt(ResCmdClsSesh.RsltTypes.Clsed.name());

	}

	@Test(expected = CumExcpIllegalSeshStatus.class)
	public void TEST_clsSeshRslt_REGED() throws TestExcp,
			CumExcpIllegalSeshStatus, CumExcpIgnoreSeshStatus {
		PrestrSesh sesh = gotoReged();

		sesh.clsSeshRslt(ResCmdClsSesh.RsltTypes.Clsed.name());

	}

	@Test
	public void TEST_clsSeshRslt_CLSING() throws TestExcp,
			CumExcpIllegalSeshStatus, CumExcpIgnoreSeshStatus {
		PrestrSesh sesh = gotoClsing();

		sesh.clsSeshRslt(ResCmdClsSesh.RsltTypes.Clsed.name());

	}

	@Test(expected = CumExcpIllegalSeshStatus.class)
	public void TEST_clsSeshRslt_CLSED() throws TestExcp,
			CumExcpIllegalSeshStatus, CumExcpIgnoreSeshStatus {
		PrestrSesh sesh = gotoClsed();

		sesh.clsSeshRslt(ResCmdClsSesh.RsltTypes.Clsed.name());
	}

	@Test(expected = CumExcpIgnoreSeshStatus.class)
	public void TEST_clsSeshRslt_DISCNED() throws TestExcp,
			CumExcpIllegalSeshStatus, CumExcpIgnoreSeshStatus {
		PrestrSesh sesh = gotoDiscned();

		sesh.clsSeshRslt(ResCmdClsSesh.RsltTypes.Clsed.name());
	}

}
