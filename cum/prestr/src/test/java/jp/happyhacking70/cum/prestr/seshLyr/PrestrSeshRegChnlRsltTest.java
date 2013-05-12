/**
 * 
 */
package jp.happyhacking70.cum.prestr.seshLyr;

import jp.happyhacking70.cum.cmd.res.impl.ResCmdClsChnl;
import jp.happyhacking70.cum.cmd.res.impl.ResCmdRegChnl;
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
public class PrestrSeshRegChnlRsltTest extends PrestrSeshAbstTest {

	@Test(expected = CumExcpIllegalSeshStatus.class)
	public void TEST_regChnlRslt_INIT() throws CumExcpIllegalSeshStatus,
			CumExcpIllegalChnlStatus, CumExcpChnlNotExist,
			CumExcpIgnoreSeshStatus {
		PrestrSesh sesh = getSesh();
		sesh.regChnlRslt(chnlNameA, ResCmdRegChnl.RsltTypes.Reged.name());
	}

	@Test(expected = CumExcpIllegalSeshStatus.class)
	public void TEST_regChnlRslt_REGING() throws TestExcp,
			CumExcpIllegalSeshStatus, CumExcpIllegalChnlStatus,
			CumExcpChnlNotExist, CumExcpIgnoreSeshStatus {
		PrestrSesh sesh = gotoReging();
		sesh.regChnlRslt(chnlNameA, ResCmdRegChnl.RsltTypes.Reged.name());
	}

	@Test
	public void TEST_regChnlRslt_REGED() throws TestExcp,
			CumExcpIllegalChnlStatus, CumExcpChnlNotExist,
			CumExcpIllegalSeshStatus, CumExcpSeshDiscned,
			CumExcpIgnoreSeshStatus {
		PrestrSesh sesh = gotoReged();
		chnlViewA.clsChnl();
		sesh.clsChnlRslt(chnlNameA, ResCmdClsChnl.RsltTypes.Clsed.name());

	}

	@Test(expected = CumExcpChnlNotExist.class)
	public void TEST_regChnlRslt_REGED_NOCHNL() throws TestExcp,
			CumExcpIllegalChnlStatus, CumExcpChnlNotExist,
			CumExcpIllegalSeshStatus, CumExcpSeshDiscned,
			CumExcpIgnoreSeshStatus {
		PrestrSesh sesh = gotoReged();

		sesh.clsChnlRslt(chnlNameC, ResCmdClsChnl.RsltTypes.Clsed.name());

	}

	@Test(expected = CumExcpIgnoreSeshStatus.class)
	public void TEST_regChnlRslt_CLSING() throws TestExcp,
			CumExcpIllegalSeshStatus, CumExcpIllegalChnlStatus,
			CumExcpChnlNotExist, CumExcpIgnoreSeshStatus {
		PrestrSesh sesh = gotoClsing();
		sesh.regChnlRslt(chnlNameA, ResCmdRegChnl.RsltTypes.Reged.name());
	}

	@Test(expected = CumExcpIgnoreSeshStatus.class)
	public void TEST_regChnlRslt_CLSED() throws TestExcp,
			CumExcpIllegalSeshStatus, CumExcpIllegalChnlStatus,
			CumExcpChnlNotExist, CumExcpIgnoreSeshStatus {
		PrestrSesh sesh = gotoClsed();
		sesh.regChnlRslt(chnlNameA, ResCmdRegChnl.RsltTypes.Reged.name());
	}

	@Test(expected = CumExcpIgnoreSeshStatus.class)
	public void TEST_regChnlRslt_DISCNED() throws TestExcp,
			CumExcpIllegalSeshStatus, CumExcpIllegalChnlStatus,
			CumExcpChnlNotExist, CumExcpIgnoreSeshStatus {
		PrestrSesh sesh = gotoDiscned();
		sesh.regChnlRslt(chnlNameA, ResCmdRegChnl.RsltTypes.Reged.name());
	}

}
