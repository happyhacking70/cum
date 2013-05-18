/**
 * 
 */
package jp.happyhacking.cum.aud.seshLyr;

import jp.happyhacking.cum.aud.chnlLyr.TestExcp;
import jp.happyhacking.cum.aud.excp.CumExcpChnlNotExist;
import jp.happyhacking.cum.aud.excp.CumExcpIllegalChnlStatus;
import jp.happyhacking.cum.aud.excp.CumExcpIllegalSeshStatus;

import org.junit.Test;

/**
 * @author happyhacking70@gmail.com
 * 
 */
public class AudSesh_chnlCmdRcvedl_Test extends AudSeshTestAbst {

	@Test(expected = CumExcpIllegalSeshStatus.class)
	public void TestInit() throws CumExcpIllegalSeshStatus,
			CumExcpChnlNotExist, CumExcpIllegalChnlStatus {
		AudSesh sesh = getSesh();
		sesh.chnlCmdRcved(chnlNameA, actionName, params);
	}

	@Test(expected = CumExcpIllegalSeshStatus.class)
	public void TestJoining() throws TestExcp, CumExcpIllegalSeshStatus,
			CumExcpChnlNotExist, CumExcpIllegalChnlStatus {
		AudSesh sesh = gotoJoining();
		sesh.chnlCmdRcved(chnlNameA, actionName, params);
	}

	@Test
	public void TestJoined() throws TestExcp, CumExcpIllegalSeshStatus,
			CumExcpChnlNotExist, CumExcpIllegalChnlStatus {
		AudSesh sesh = gotoJoined();

		sesh.joinChnl(chnlNameA);
		sesh.chnlJoined(chnlNameA);
		sesh.chnlCmdRcved(chnlNameA, actionName, params);

	}

	@Test(expected = CumExcpChnlNotExist.class)
	public void TestJoined_NOCHNL() throws TestExcp, CumExcpIllegalSeshStatus,
			CumExcpChnlNotExist, CumExcpIllegalChnlStatus {
		AudSesh sesh = gotoJoined();

		sesh.joinChnl(chnlNameA);
		sesh.chnlJoined(chnlNameA);
		sesh.chnlCmdRcved("XXXX", actionName, params);

	}

	@Test
	public void TestLving() throws TestExcp, CumExcpIllegalSeshStatus,
			CumExcpChnlNotExist, CumExcpIllegalChnlStatus {
		AudSesh sesh = gotoLving();
		sesh.chnlCmdRcved(chnlNameA, actionName, params);
	}

	@Test(expected = CumExcpIllegalSeshStatus.class)
	public void TestClsed() throws TestExcp, CumExcpIllegalSeshStatus,
			CumExcpChnlNotExist, CumExcpIllegalChnlStatus {
		AudSesh sesh = gotoClsed();
		sesh.chnlCmdRcved(chnlNameA, actionName, params);
	}

	@Test(expected = CumExcpIllegalSeshStatus.class)
	public void TestDscned() throws TestExcp, CumExcpIllegalSeshStatus,
			CumExcpChnlNotExist, CumExcpIllegalChnlStatus {
		AudSesh sesh = gotoDscned();
		sesh.chnlCmdRcved(chnlNameA, actionName, params);
	}
}
