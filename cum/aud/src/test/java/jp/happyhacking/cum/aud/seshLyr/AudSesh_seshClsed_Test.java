/**
 * 
 */
package jp.happyhacking.cum.aud.seshLyr;

import static org.junit.Assert.assertTrue;
import jp.happyhacking.cum.aud.chnlLyr.DummyChniView;
import jp.happyhacking.cum.aud.chnlLyr.TestExcp;
import jp.happyhacking.cum.aud.excp.CumExcpIllegalChnlStatuMulti;
import jp.happyhacking.cum.aud.excp.CumExcpIllegalSeshStatus;

import org.junit.Test;

/**
 * @author happyhacking70@gmail.com
 * 
 */
public class AudSesh_seshClsed_Test extends AudSeshTestAbst {

	@Test(expected = CumExcpIllegalSeshStatus.class)
	public void TestInit() throws CumExcpIllegalChnlStatuMulti,
			CumExcpIllegalSeshStatus {
		AudSesh sesh = getSesh();
		sesh.seshClsed();
	}

	@Test
	public void TestJoining() throws TestExcp, CumExcpIllegalChnlStatuMulti,
			CumExcpIllegalSeshStatus {
		AudSesh sesh = gotoJoining();
		sesh.seshClsed();
		assertTrue(seshView.isClsed());
	}

	@Test
	public void TestJoined() throws TestExcp, CumExcpIllegalChnlStatuMulti,
			CumExcpIllegalSeshStatus {
		AudSesh sesh = gotoJoined();
		sesh.seshClsed();
		assertTrue(seshView.isClsed());
		DummyChniView chnlView = seshView.getChnlView();
		assertTrue(chnlView.isSeshClsed());
	}

	@Test
	public void TestLving() throws TestExcp, CumExcpIllegalChnlStatuMulti,
			CumExcpIllegalSeshStatus {
		AudSesh sesh = gotoLving();
		sesh.seshClsed();
		assertTrue(seshView.isClsed());
		DummyChniView chnlView = seshView.getChnlView();
		assertTrue(chnlView.isSeshClsed());
	}

	@Test(expected = CumExcpIllegalSeshStatus.class)
	public void TestClsed() throws TestExcp, CumExcpIllegalChnlStatuMulti,
			CumExcpIllegalSeshStatus {
		AudSesh sesh = gotoClsed();
		sesh.seshClsed();
	}

	@Test(expected = CumExcpIllegalSeshStatus.class)
	public void TestDscned() throws TestExcp, CumExcpIllegalChnlStatuMulti,
			CumExcpIllegalSeshStatus {
		AudSesh sesh = gotoDscned();
		sesh.seshClsed();
	}
}
