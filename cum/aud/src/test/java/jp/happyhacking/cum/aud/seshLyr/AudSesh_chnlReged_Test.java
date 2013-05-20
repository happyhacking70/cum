/**
 * 
 */
package jp.happyhacking.cum.aud.seshLyr;

import static org.junit.Assert.assertEquals;
import jp.happyhacking.cum.aud.chnlLyr.TestExcp;
import jp.happyhacking.cum.aud.excp.CumExcpChnlExists;
import jp.happyhacking.cum.aud.excp.CumExcpIllegalSeshStatus;

import org.junit.Test;

/**
 * @author happyhacking70@gmail.com
 * 
 */
public class AudSesh_chnlReged_Test extends AudSeshTestAbst {

	@Test(expected = CumExcpIllegalSeshStatus.class)
	public void TestInit() throws CumExcpChnlExists, CumExcpIllegalSeshStatus {
		AudSesh sesh = getSesh();
		sesh.chnlReged(chnlType, chnlNameA, rsces);
	}

	@Test(expected = CumExcpIllegalSeshStatus.class)
	public void TestJoining() throws TestExcp, CumExcpChnlExists,
			CumExcpIllegalSeshStatus {
		AudSesh sesh = gotoJoining();
		sesh.chnlReged(chnlType, chnlNameA, rsces);
	}

	@Test
	public void TestJoined() throws TestExcp, CumExcpChnlExists,
			CumExcpIllegalSeshStatus {

		AudSesh sesh = gotoJoined();
		sesh.chnlReged(chnlType, chnlNameB, rsces);
		assertEquals(chnlNameB, seshView.getChnlNameReged());
		assertEquals(chnlType, seshView.getChnlTypeReged());
	}

	@Test(expected = CumExcpChnlExists.class)
	public void TestJoined_CHNLEXIST() throws TestExcp, CumExcpChnlExists,
			CumExcpIllegalSeshStatus {

		AudSesh sesh = gotoJoined();
		sesh.chnlReged(chnlType, chnlNameA, rsces);
	}

	@Test
	public void TestLving() throws TestExcp, CumExcpChnlExists,
			CumExcpIllegalSeshStatus {
		AudSesh sesh = gotoLving();
		sesh.chnlReged(chnlType, chnlNameB, rsces);
		// chnlB reged should be ignored
		assertEquals(chnlNameA, seshView.getChnlNameReged());
	}

	@Test(expected = CumExcpIllegalSeshStatus.class)
	public void TestClsed() throws TestExcp, CumExcpChnlExists,
			CumExcpIllegalSeshStatus {
		AudSesh sesh = gotoClsed();
		sesh.chnlReged(chnlType, chnlNameA, rsces);
	}

	@Test(expected = CumExcpIllegalSeshStatus.class)
	public void TestDscned() throws TestExcp, CumExcpChnlExists,
			CumExcpIllegalSeshStatus {
		AudSesh sesh = gotoDscned();
		sesh.chnlReged(chnlType, chnlNameA, rsces);
	}

}
