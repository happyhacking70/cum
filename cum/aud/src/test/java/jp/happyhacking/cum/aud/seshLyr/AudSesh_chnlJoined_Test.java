/**
 * 
 */
package jp.happyhacking.cum.aud.seshLyr;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import jp.happyhacking.cum.aud.chnlLyr.TestExcp;
import jp.happyhacking.cum.aud.excp.CumExcpChnlNotExist;
import jp.happyhacking.cum.aud.excp.CumExcpIllegalChnlStatus;
import jp.happyhacking.cum.aud.excp.CumExcpIllegalSeshStatus;

import org.junit.Test;

/**
 * @author happyhacking70@gmail.com
 * 
 */
public class AudSesh_chnlJoined_Test extends AudSeshTestAbst {

	@Test(expected = CumExcpIllegalSeshStatus.class)
	public void TestInit() throws CumExcpIllegalSeshStatus,
			CumExcpChnlNotExist, CumExcpIllegalChnlStatus {
		AudSesh sesh = getSesh();

		sesh.chnlJoined(chnlNameA);
	}

	@Test(expected = CumExcpIllegalSeshStatus.class)
	public void TestJoining() throws TestExcp, CumExcpIllegalSeshStatus,
			CumExcpChnlNotExist, CumExcpIllegalChnlStatus {
		AudSesh sesh = gotoJoining();
		sesh.chnlJoined(chnlNameA);
	}

	@Test
	public void TestJoined() throws TestExcp, CumExcpIllegalSeshStatus,
			CumExcpChnlNotExist, CumExcpIllegalChnlStatus {

		AudSesh sesh = gotoJoined();
		try {
			sesh.joinChnl(chnlNameA);
		} catch (CumExcpIllegalSeshStatus e) {
			throw new TestExcp("joinChnl");
		} catch (CumExcpChnlNotExist e) {
			throw new TestExcp("joinChnl");
		} catch (CumExcpIllegalChnlStatus e) {
			e.printStackTrace();
			throw new TestExcp("joinChnl");
		}
		sesh.chnlJoined(chnlNameA);
		assertTrue(seshView.getChnlView().isJoined());
	}

	@Test(expected = CumExcpChnlNotExist.class)
	public void TestJoined_NOCHNL() throws TestExcp, CumExcpIllegalSeshStatus,
			CumExcpChnlNotExist, CumExcpIllegalChnlStatus {

		AudSesh sesh = gotoJoined();
		try {
			sesh.joinChnl(chnlNameA);
		} catch (CumExcpIllegalSeshStatus e) {
			throw new TestExcp("joinChnl");
		} catch (CumExcpChnlNotExist e) {
			throw new TestExcp("joinChnl");
		} catch (CumExcpIllegalChnlStatus e) {
			e.printStackTrace();
			throw new TestExcp("joinChnl");
		}
		sesh.chnlJoined("XXXX");
	}

	@Test
	public void TestLving() throws TestExcp, CumExcpIllegalSeshStatus,
			CumExcpChnlNotExist, CumExcpIllegalChnlStatus {
		AudSesh sesh = gotoLving();
		sesh.chnlJoined(chnlNameA);
		assertNull(seshView.getChnlView().getActionName());
		assertNull(seshView.getChnlView().getParams());
	}

	@Test(expected = CumExcpIllegalSeshStatus.class)
	public void TestClsed() throws TestExcp, CumExcpIllegalSeshStatus,
			CumExcpChnlNotExist, CumExcpIllegalChnlStatus {
		AudSesh sesh = gotoClsed();
		sesh.chnlJoined(chnlNameA);
	}

	@Test(expected = CumExcpIllegalSeshStatus.class)
	public void TestDscned() throws TestExcp, CumExcpIllegalSeshStatus,
			CumExcpChnlNotExist, CumExcpIllegalChnlStatus {
		AudSesh sesh = gotoDscned();
		sesh.chnlJoined(chnlNameA);
	}

}
