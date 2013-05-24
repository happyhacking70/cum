/**
 * 
 */
package jp.happyhacking.cum.aud.seshLyr;

import static org.junit.Assert.assertEquals;
import jp.happyhacking.cum.aud.chnlLyr.TestExcp;
import jp.happyhacking.cum.aud.excp.CumExcpIllegalSeshStatus;

import org.junit.Test;

/**
 * @author happyhacking70@gmail.com
 * 
 */
public class AudSesh_seshJoinFailed_Test extends AudSeshTestAbst {
	@Test(expected = CumExcpIllegalSeshStatus.class)
	public void TestInit() throws CumExcpIllegalSeshStatus {
		AudSesh sesh = getSesh();
		sesh.seshJoinFailed(rslt);
	}

	@Test
	public void TestJoining() throws TestExcp, CumExcpIllegalSeshStatus {
		AudSesh sesh = gotoJoining();
		sesh.seshJoinFailed(rslt);
		assertEquals(rslt, seshView.getJoinSeshRslt());
	}

	@Test(expected = CumExcpIllegalSeshStatus.class)
	public void TestJoined() throws TestExcp, CumExcpIllegalSeshStatus {
		AudSesh sesh = gotoJoined();
		sesh.seshJoinFailed(rslt);
	}

	@Test(expected = CumExcpIllegalSeshStatus.class)
	public void TestLving() throws TestExcp, CumExcpIllegalSeshStatus {
		AudSesh sesh = gotoLving();
		sesh.seshJoinFailed(rslt);
	}

	@Test(expected = CumExcpIllegalSeshStatus.class)
	public void TestClsed() throws TestExcp, CumExcpIllegalSeshStatus {
		AudSesh sesh = gotoClsed();
		sesh.seshJoinFailed(rslt);
	}

	@Test(expected = CumExcpIllegalSeshStatus.class)
	public void TestDscned() throws TestExcp, CumExcpIllegalSeshStatus {
		AudSesh sesh = gotoDscned();
		sesh.seshJoinFailed(rslt);
	}
}
