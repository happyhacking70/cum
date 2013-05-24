/**
 * 
 */
package jp.happyhacking.cum.aud.seshLyr;

import static org.junit.Assert.assertTrue;
import jp.happyhacking.cum.aud.chnlLyr.TestExcp;
import jp.happyhacking.cum.aud.excp.CumExcpIllegalChnlStatuMulti;
import jp.happyhacking.cum.aud.excp.CumExcpIllegalSeshStatus;

import org.junit.Test;

/**
 * @author happyhacking70@gmail.com
 * 
 */
public class AudSesh_seshDscned_Test extends AudSeshTestAbst {

	@Test(expected = CumExcpIllegalSeshStatus.class)
	public void TestInit() throws CumExcpIllegalChnlStatuMulti,
			CumExcpIllegalSeshStatus {
		AudSesh sesh = getSesh();
		sesh.seshDscned();
	}

	@Test
	public void TestJoining() throws TestExcp, CumExcpIllegalChnlStatuMulti,
			CumExcpIllegalSeshStatus {
		AudSesh sesh = gotoJoining();
		sesh.seshDscned();
		assertTrue(seshView.isDscned());
	}

	@Test
	public void TestJoined() throws TestExcp, CumExcpIllegalChnlStatuMulti,
			CumExcpIllegalSeshStatus {
		AudSesh sesh = gotoJoined();
		sesh.seshDscned();
		assertTrue(seshView.isDscned());
		assertTrue(seshView.getChnlView().isDscned());
	}

	@Test
	public void TestLving() throws TestExcp, CumExcpIllegalChnlStatuMulti,
			CumExcpIllegalSeshStatus {
		AudSesh sesh = gotoLving();
		sesh.seshDscned();
		assertTrue(seshView.isDscned());
		assertTrue(seshView.getChnlView().isDscned());
	}

	@Test(expected = CumExcpIllegalSeshStatus.class)
	public void TestClsed() throws TestExcp, CumExcpIllegalChnlStatuMulti,
			CumExcpIllegalSeshStatus {
		AudSesh sesh = gotoClsed();
		sesh.seshDscned();
	}

	@Test(expected = CumExcpIllegalSeshStatus.class)
	public void TestDscned() throws TestExcp, CumExcpIllegalChnlStatuMulti,
			CumExcpIllegalSeshStatus {
		AudSesh sesh = gotoDscned();
		sesh.seshDscned();
	}
}
