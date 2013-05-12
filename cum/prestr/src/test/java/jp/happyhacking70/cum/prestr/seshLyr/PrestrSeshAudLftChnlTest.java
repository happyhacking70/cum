/**
 * 
 */
package jp.happyhacking70.cum.prestr.seshLyr;

import static org.junit.Assert.assertEquals;
import jp.happyhacking70.cum.excp.prestr.CumExcpAudNotExist;
import jp.happyhacking70.cum.excp.prestr.CumExcpChnlNotExist;
import jp.happyhacking70.cum.excp.prestr.CumExcpIgnoreSeshStatus;
import jp.happyhacking70.cum.excp.prestr.CumExcpIllegalChnlStatus;
import jp.happyhacking70.cum.excp.prestr.CumExcpIllegalSeshStatus;

import org.junit.Test;

/**
 * @author happyhacking70@gmail.com
 * 
 */
public class PrestrSeshAudLftChnlTest extends PrestrSeshAbstTest {

	@Test(expected = CumExcpIllegalSeshStatus.class)
	public void TEST_audLftChnl_INIT() throws CumExcpAudNotExist,
			CumExcpIllegalChnlStatus, CumExcpChnlNotExist,
			CumExcpIllegalSeshStatus, CumExcpIgnoreSeshStatus {
		PrestrSesh sesh = getSesh();
		sesh.audLftChnl(chnlNameA, audNameA);
	}

	@Test(expected = CumExcpIllegalSeshStatus.class)
	public void TEST_audLftChnl_REGING() throws TestExcp, CumExcpAudNotExist,
			CumExcpIllegalChnlStatus, CumExcpChnlNotExist,
			CumExcpIllegalSeshStatus, CumExcpIgnoreSeshStatus {
		PrestrSesh sesh = gotoReging();
		sesh.audLftChnl(chnlNameA, audNameA);

	}

	@Test
	public void TEST_audLftChnl_REGED() throws TestExcp, CumExcpAudNotExist,
			CumExcpIllegalChnlStatus, CumExcpChnlNotExist,
			CumExcpIllegalSeshStatus, CumExcpIgnoreSeshStatus {
		PrestrSesh sesh = gotoReged();
		sesh.audLftChnl(chnlNameA, audNameA);
		assertEquals(audNameA, chnlViewA.getAudJoined());
	}

	@Test(expected = CumExcpAudNotExist.class)
	public void TEST_audLftChnl_REGED_NOAUD() throws TestExcp,
			CumExcpAudNotExist, CumExcpIllegalChnlStatus, CumExcpChnlNotExist,
			CumExcpIllegalSeshStatus, CumExcpIgnoreSeshStatus {
		PrestrSesh sesh = gotoReged();
		sesh.audLftChnl(chnlNameA, audNameC);
	}

	@Test(expected = CumExcpChnlNotExist.class)
	public void TEST_audLftChnl_REGED_NOCHNL() throws TestExcp,
			CumExcpAudNotExist, CumExcpIllegalChnlStatus, CumExcpChnlNotExist,
			CumExcpIllegalSeshStatus, CumExcpIgnoreSeshStatus {
		PrestrSesh sesh = gotoReged();
		sesh.audLftChnl(chnlNameC, audNameA);
	}

	@Test(expected = CumExcpIgnoreSeshStatus.class)
	public void TEST_audLftChnl_CLSING() throws TestExcp, CumExcpAudNotExist,
			CumExcpIllegalChnlStatus, CumExcpChnlNotExist,
			CumExcpIllegalSeshStatus, CumExcpIgnoreSeshStatus {
		PrestrSesh sesh = gotoClsing();
		sesh.audLftChnl(chnlNameA, audNameA);
	}

	@Test(expected = CumExcpIgnoreSeshStatus.class)
	public void TEST_audLftChnl_CLSED() throws TestExcp, CumExcpAudNotExist,
			CumExcpIllegalChnlStatus, CumExcpChnlNotExist,
			CumExcpIllegalSeshStatus, CumExcpIgnoreSeshStatus {
		PrestrSesh sesh = gotoClsed();
		sesh.audLftChnl(chnlNameA, audNameA);

	}

	@Test(expected = CumExcpIgnoreSeshStatus.class)
	public void TEST_audLftChnl_DISCNED() throws TestExcp, CumExcpAudNotExist,
			CumExcpIllegalChnlStatus, CumExcpChnlNotExist,
			CumExcpIllegalSeshStatus, CumExcpIgnoreSeshStatus {
		PrestrSesh sesh = gotoDiscned();
		sesh.audLftChnl(chnlNameA, audNameA);
	}

}
