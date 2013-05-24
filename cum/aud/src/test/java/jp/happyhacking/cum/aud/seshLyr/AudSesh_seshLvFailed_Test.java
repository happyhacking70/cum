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
public class AudSesh_seshLvFailed_Test extends AudSeshTestAbst {

	@Test(expected = CumExcpIllegalSeshStatus.class)
	public void TestInit() throws CumExcpIllegalSeshStatus {
		AudSesh sesh = getSesh();
		sesh.seshLvFailed(rslt);
	}

	@Test(expected = CumExcpIllegalSeshStatus.class)
	public void TestJoining() throws TestExcp, CumExcpIllegalSeshStatus {
		AudSesh sesh = gotoJoining();
		sesh.seshLvFailed(rslt);
	}

	@Test(expected = CumExcpIllegalSeshStatus.class)
	public void TestJoined() throws TestExcp, CumExcpIllegalSeshStatus {
		AudSesh sesh = gotoJoined();
		sesh.seshLvFailed(rslt);
	}

	@Test
	public void TestLving() throws TestExcp, CumExcpIllegalSeshStatus {
		AudSesh sesh = gotoLving();
		sesh.seshLvFailed(rslt);
		assertEquals(rslt, seshView.getLvSeshFailedRslt());
	}

	@Test(expected = CumExcpIllegalSeshStatus.class)
	public void TestClsed() throws TestExcp, CumExcpIllegalSeshStatus {
		AudSesh sesh = gotoClsed();
		sesh.seshLvFailed(rslt);
	}

	@Test(expected = CumExcpIllegalSeshStatus.class)
	public void TestDscned() throws TestExcp, CumExcpIllegalSeshStatus {
		AudSesh sesh = gotoDscned();
		sesh.seshLvFailed(rslt);
	}
}
