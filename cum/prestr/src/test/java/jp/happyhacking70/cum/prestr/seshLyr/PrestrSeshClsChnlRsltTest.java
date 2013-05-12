/**
 * 
 */
package jp.happyhacking70.cum.prestr.seshLyr;

import jp.happyhacking70.cum.cmd.res.impl.ResCmdClsChnl;
import jp.happyhacking70.cum.excp.prestr.CumExcpChnlNotExist;
import jp.happyhacking70.cum.excp.prestr.CumExcpIgnoreSeshStatus;
import jp.happyhacking70.cum.excp.prestr.CumExcpIllegalChnlStatus;
import jp.happyhacking70.cum.excp.prestr.CumExcpIllegalSeshStatus;
import jp.happyhacking70.cum.excp.prestr.CumExcpSeshDiscned;

import org.junit.Test;

/**
 * @author happyhacking70@gmail.com
 * 
 */
public class PrestrSeshClsChnlRsltTest extends PrestrSeshAbstTest {

	@Test(expected = CumExcpIllegalSeshStatus.class)
	public void TEST_clsChnlRslt_INIT() throws CumExcpChnlNotExist,
			CumExcpIllegalSeshStatus, CumExcpIllegalChnlStatus,
			CumExcpIgnoreSeshStatus {
		PrestrSesh sesh = getSesh();
		sesh.clsChnlRslt(chnlNameA, ResCmdClsChnl.RsltTypes.Clsed.name());
	}

	@Test(expected = CumExcpIllegalSeshStatus.class)
	public void TEST_clsChnlRslt_REGING() throws TestExcp, CumExcpChnlNotExist,
			CumExcpIllegalSeshStatus, CumExcpIllegalChnlStatus,
			CumExcpIgnoreSeshStatus {
		PrestrSesh sesh = gotoReging();
		sesh.clsChnlRslt(chnlNameA, ResCmdClsChnl.RsltTypes.Clsed.name());
	}

	@Test
	public void TEST_clsChnlRslt_REGED() throws TestExcp, CumExcpChnlNotExist,
			CumExcpIllegalSeshStatus, CumExcpIllegalChnlStatus,
			CumExcpIgnoreSeshStatus, CumExcpSeshDiscned {
		PrestrSesh sesh = gotoReged();

		// sesh.clsChnl(chnlNameA);

		chnlViewA.clsChnl();
		sesh.clsChnlRslt(chnlNameA, ResCmdClsChnl.RsltTypes.Clsed.name());

	}

	@Test(expected = CumExcpChnlNotExist.class)
	public void TEST_clsChnlRslt_REGED_NOCHNL() throws TestExcp,
			CumExcpChnlNotExist, CumExcpIllegalSeshStatus,
			CumExcpIllegalChnlStatus, CumExcpIgnoreSeshStatus {
		PrestrSesh sesh = gotoReged();

		sesh.clsChnlRslt(chnlNameC, ResCmdClsChnl.RsltTypes.Clsed.name());

	}

	@Test(expected = CumExcpIgnoreSeshStatus.class)
	public void TEST_clsChnlRslt_CLSING() throws TestExcp, CumExcpChnlNotExist,
			CumExcpIllegalSeshStatus, CumExcpIllegalChnlStatus,
			CumExcpIgnoreSeshStatus {
		PrestrSesh sesh = gotoClsing();
		sesh.clsChnlRslt(chnlNameA, ResCmdClsChnl.RsltTypes.Clsed.name());
	}

	@Test(expected = CumExcpIllegalSeshStatus.class)
	public void TEST_clsChnlRslt_CLSED() throws TestExcp, CumExcpChnlNotExist,
			CumExcpIllegalSeshStatus, CumExcpIllegalChnlStatus,
			CumExcpIgnoreSeshStatus {
		PrestrSesh sesh = gotoClsed();
		sesh.clsChnlRslt(chnlNameA, ResCmdClsChnl.RsltTypes.Clsed.name());

	}

	@Test(expected = CumExcpIgnoreSeshStatus.class)
	public void TEST_clsChnlRslt_DISCNED() throws TestExcp,
			CumExcpChnlNotExist, CumExcpIllegalSeshStatus,
			CumExcpIllegalChnlStatus, CumExcpIgnoreSeshStatus {
		PrestrSesh sesh = gotoDiscned();
		sesh.clsChnlRslt(chnlNameA, ResCmdClsChnl.RsltTypes.Clsed.name());

	}

}
