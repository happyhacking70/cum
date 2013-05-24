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
public class AudSesh_seshLft_Test extends AudSeshTestAbst {

	@Test(expected = CumExcpIllegalSeshStatus.class)
	public void TestInit() throws CumExcpIllegalSeshStatus {
		AudSesh sesh = getSesh();
		sesh.seshLft();
	}

	@Test(expected = CumExcpIllegalSeshStatus.class)
	public void TestJoining() throws TestExcp, CumExcpIllegalSeshStatus {
		AudSesh sesh = gotoJoining();
		sesh.seshLft();
	}

	@Test(expected = CumExcpIllegalSeshStatus.class)
	public void TestJoined() throws TestExcp, CumExcpIllegalSeshStatus {
		AudSesh sesh = gotoJoined();
		sesh.seshLft();
	}

	@Test
	public void TestLving() throws TestExcp, CumExcpIllegalSeshStatus {
		AudSesh sesh = gotoLving();
		sesh.seshLft();
		assertTrue(seshView.isLft());
	}

	@Test(expected = CumExcpIllegalSeshStatus.class)
	public void TestClsed() throws TestExcp, CumExcpIllegalSeshStatus {
		AudSesh sesh = gotoClsed();
		sesh.seshLft();
	}

	@Test(expected = CumExcpIllegalSeshStatus.class)
	public void TestDscned() throws TestExcp, CumExcpIllegalSeshStatus {
		AudSesh sesh = gotoDscned();
		sesh.seshLft();
	}

}
