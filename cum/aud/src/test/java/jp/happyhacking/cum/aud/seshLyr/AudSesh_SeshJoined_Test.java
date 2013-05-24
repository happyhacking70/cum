/**
 * 
 */
package jp.happyhacking.cum.aud.seshLyr;

import static org.junit.Assert.assertTrue;
import jp.happyhacking.cum.aud.chnlLyr.TestExcp;
import jp.happyhacking.cum.aud.excp.CumExcpIllegalSeshStatus;

import org.junit.Test;

/**
 * @author happyhacking70@gmail.com
 * 
 */
public class AudSesh_SeshJoined_Test extends AudSeshTestAbst {

	@Test(expected = CumExcpIllegalSeshStatus.class)
	public void TestInit() throws CumExcpIllegalSeshStatus {
		AudSesh sesh = getSesh();
		sesh.seshJoined();
	}

	@Test
	public void TestJoining() throws TestExcp, CumExcpIllegalSeshStatus {
		AudSesh sesh = gotoJoining();
		sesh.seshJoined();
		assertTrue(seshView.isJoined());
	}

	@Test(expected = CumExcpIllegalSeshStatus.class)
	public void TestJoined() throws TestExcp, CumExcpIllegalSeshStatus {
		AudSesh sesh = gotoJoined();
		sesh.seshJoined();
	}

	@Test(expected = CumExcpIllegalSeshStatus.class)
	public void TestLving() throws TestExcp, CumExcpIllegalSeshStatus {
		AudSesh sesh = gotoLving();
		sesh.seshJoined();
	}

	@Test(expected = CumExcpIllegalSeshStatus.class)
	public void TestClsed() throws TestExcp, CumExcpIllegalSeshStatus {
		AudSesh sesh = gotoClsed();
		sesh.seshJoined();
	}

	@Test(expected = CumExcpIllegalSeshStatus.class)
	public void TestDscned() throws TestExcp, CumExcpIllegalSeshStatus {
		AudSesh sesh = gotoDscned();
		sesh.seshJoined();
	}

}
