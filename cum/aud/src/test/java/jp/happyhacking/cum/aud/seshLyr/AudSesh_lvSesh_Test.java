/**
 * 
 */
package jp.happyhacking.cum.aud.seshLyr;

import static org.junit.Assert.assertEquals;
import jp.happyhacking.cum.aud.chnlLyr.TestExcp;
import jp.happyhacking.cum.aud.excp.CumExcpIgnoreChnlStatus;
import jp.happyhacking.cum.aud.excp.CumExcpIllegalChnlStatus;
import jp.happyhacking.cum.aud.excp.CumExcpIllegalSeshStatus;

import org.junit.Test;

/**
 * @author happyhacking70@gmail.com
 * 
 */
public class AudSesh_lvSesh_Test extends AudSeshTestAbst {

	@Test(expected = CumExcpIllegalSeshStatus.class)
	public void TestInit() throws CumExcpIllegalSeshStatus,
			CumExcpIllegalChnlStatus, CumExcpIgnoreChnlStatus {
		AudSesh sesh = getSesh();
		sesh.lvSesh();
	}

	@Test(expected = CumExcpIllegalSeshStatus.class)
	public void TestJoining() throws TestExcp, CumExcpIllegalSeshStatus,
			CumExcpIllegalChnlStatus, CumExcpIgnoreChnlStatus {
		AudSesh sesh = gotoJoining();
		sesh.lvSesh();
	}

	@Test
	public void TestJoined() throws TestExcp, CumExcpIllegalSeshStatus,
			CumExcpIllegalChnlStatus, CumExcpIgnoreChnlStatus {
		AudSesh sesh = gotoJoined();
		sesh.lvSesh();
		assertEquals(seshName, adptr.getSeshNameToLv());
		assertEquals(audNameA, adptr.getAudNameToLv());

	}

	@Test(expected = CumExcpIllegalSeshStatus.class)
	public void TestLving() throws TestExcp, CumExcpIllegalSeshStatus,
			CumExcpIllegalChnlStatus, CumExcpIgnoreChnlStatus {
		AudSesh sesh = gotoLving();
		sesh.lvSesh();
	}

	@Test(expected = CumExcpIllegalSeshStatus.class)
	public void TestClsed() throws TestExcp, CumExcpIllegalSeshStatus,
			CumExcpIllegalChnlStatus, CumExcpIgnoreChnlStatus {
		AudSesh sesh = gotoClsed();
		sesh.lvSesh();
	}

	@Test(expected = CumExcpIllegalSeshStatus.class)
	public void TestDscned() throws TestExcp, CumExcpIllegalSeshStatus,
			CumExcpIllegalChnlStatus, CumExcpIgnoreChnlStatus {
		AudSesh sesh = gotoDscned();
		sesh.lvSesh();
	}
}
