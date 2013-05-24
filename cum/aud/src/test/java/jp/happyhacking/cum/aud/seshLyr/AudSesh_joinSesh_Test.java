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
public class AudSesh_joinSesh_Test extends AudSeshTestAbst {

	@Test
	public void TestInit() throws CumExcpIllegalSeshStatus {
		AudSesh sesh = getSesh();
		sesh.joinSesh();
		assertEquals(seshName, adptr.getSeshNameToJin());
		assertEquals(audNameA, adptr.getAudNameToJoinSesh());
		assertEquals(AudSesh.Status.joining, sesh.seshStatus);
	}

	@Test(expected = CumExcpIllegalSeshStatus.class)
	public void TestJoining() throws TestExcp, CumExcpIllegalSeshStatus {
		AudSesh sesh = gotoJoining();
		sesh.joinSesh();
	}

	@Test(expected = CumExcpIllegalSeshStatus.class)
	public void TestJoined() throws TestExcp, CumExcpIllegalSeshStatus {
		AudSesh sesh = gotoJoined();
		sesh.joinSesh();
	}

	@Test(expected = CumExcpIllegalSeshStatus.class)
	public void TestLving() throws TestExcp, CumExcpIllegalSeshStatus {
		AudSesh sesh = gotoLving();
		sesh.joinSesh();
	}

	@Test(expected = CumExcpIllegalSeshStatus.class)
	public void TestClsed() throws TestExcp, CumExcpIllegalSeshStatus {
		AudSesh sesh = gotoClsed();
		sesh.joinSesh();
	}

	@Test(expected = CumExcpIllegalSeshStatus.class)
	public void TestDscned() throws TestExcp, CumExcpIllegalSeshStatus {
		AudSesh sesh = gotoDscned();
		sesh.joinSesh();
	}

}
