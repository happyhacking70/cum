/**
 * 
 */
package jp.happyhacking70.cum.prestr.seshLyr;

import static org.junit.Assert.assertEquals;
import jp.happyhacking70.cum.excp.prestr.CumExcpAudExists;
import jp.happyhacking70.cum.excp.prestr.CumExcpAudNotExist;
import jp.happyhacking70.cum.excp.prestr.CumExcpIgnoreSeshStatus;
import jp.happyhacking70.cum.excp.prestr.CumExcpIllegalChnlStatuMulti;
import jp.happyhacking70.cum.excp.prestr.CumExcpIllegalSeshStatus;

import org.junit.Test;

/**
 * @author happyhacking70@gmail.com
 * 
 */
public class PrestrSeshAudLftSeshTest extends PrestrSeshAbstTest {

	@Test(expected = CumExcpIllegalSeshStatus.class)
	public void TEST_audLftSesh_INIT() throws CumExcpAudNotExist,
			CumExcpIllegalSeshStatus, CumExcpIllegalChnlStatuMulti,
			CumExcpIgnoreSeshStatus {
		PrestrSesh sesh = getSesh();
		sesh.audLftSesh(audNameA);
	}

	@Test
	public void TEST_audLftSesh_REGING() throws TestExcp, CumExcpAudExists,
			CumExcpIllegalSeshStatus, CumExcpIgnoreSeshStatus,
			CumExcpAudNotExist, CumExcpIllegalChnlStatuMulti {
		PrestrSesh sesh = gotoReging();
		sesh.audJoinedSesh(audNameA);
		sesh.audLftSesh(audNameA);
		assertEquals(audNameA, seshView.getAudLftName());
	}

	@Test(expected = CumExcpAudNotExist.class)
	public void TEST_audLftSesh_REGING_NOAUD() throws TestExcp,
			CumExcpAudExists, CumExcpIllegalSeshStatus,
			CumExcpIgnoreSeshStatus, CumExcpAudNotExist,
			CumExcpIllegalChnlStatuMulti {
		PrestrSesh sesh = gotoReging();

		sesh.audLftSesh(audNameA);
	}

	@Test
	public void TEST_audLftSesh_REGED() throws TestExcp, CumExcpAudExists,
			CumExcpIllegalSeshStatus, CumExcpIgnoreSeshStatus,
			CumExcpAudNotExist, CumExcpIllegalChnlStatuMulti {
		PrestrSesh sesh = gotoReged();
		sesh.audLftSesh(audNameA);
		assertEquals(audNameA, seshView.getAudLftName());
	}

	@Test(expected = CumExcpAudNotExist.class)
	public void TEST_audLftSesh_REGED_NOAUD() throws TestExcp,
			CumExcpAudExists, CumExcpIllegalSeshStatus,
			CumExcpIgnoreSeshStatus, CumExcpAudNotExist,
			CumExcpIllegalChnlStatuMulti {
		PrestrSesh sesh = gotoReged();

		sesh.audLftSesh(audNameC);
	}

	@Test(expected = CumExcpIgnoreSeshStatus.class)
	public void TEST_audLftSesh_CLSING() throws TestExcp, CumExcpAudNotExist,
			CumExcpIllegalSeshStatus, CumExcpIllegalChnlStatuMulti,
			CumExcpIgnoreSeshStatus {
		PrestrSesh sesh = gotoClsing();
		sesh.audLftSesh(audNameA);
	}

	@Test(expected = CumExcpIgnoreSeshStatus.class)
	public void TEST_audLftSesh_CLSED() throws TestExcp, CumExcpAudNotExist,
			CumExcpIllegalSeshStatus, CumExcpIllegalChnlStatuMulti,
			CumExcpIgnoreSeshStatus {
		PrestrSesh sesh = gotoClsed();
		sesh.audLftSesh(audNameA);
	}

	@Test(expected = CumExcpIgnoreSeshStatus.class)
	public void TEST_audLftSesh_DISCNED() throws TestExcp, CumExcpAudNotExist,
			CumExcpIllegalSeshStatus, CumExcpIllegalChnlStatuMulti,
			CumExcpIgnoreSeshStatus {
		PrestrSesh sesh = gotoDiscned();

		sesh.audLftSesh(audNameA);

	}

}
