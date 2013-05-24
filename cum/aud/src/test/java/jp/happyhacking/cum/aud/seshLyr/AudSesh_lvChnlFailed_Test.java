/**
 * 
 */
package jp.happyhacking.cum.aud.seshLyr;

import static org.junit.Assert.assertEquals;
import jp.happyhacking.cum.aud.chnlLyr.AudChnl;
import jp.happyhacking.cum.aud.chnlLyr.DummyChniView;
import jp.happyhacking.cum.aud.chnlLyr.TestExcp;
import jp.happyhacking.cum.aud.excp.CumExcpChnlNotExist;
import jp.happyhacking.cum.aud.excp.CumExcpIllegalChnlStatus;
import jp.happyhacking.cum.aud.excp.CumExcpIllegalSeshStatus;

import org.junit.Test;

/**
 * @author happyhacking70@gmail.com
 * 
 */
public class AudSesh_lvChnlFailed_Test extends AudSeshTestAbst {

	@Test(expected = CumExcpIllegalSeshStatus.class)
	public void TestInit() throws CumExcpIllegalSeshStatus,
			CumExcpChnlNotExist, CumExcpIllegalChnlStatus {
		AudSesh sesh = getSesh();
		sesh.lvChnlFailed(chnlNameA, rslt);
	}

	@Test(expected = CumExcpIllegalSeshStatus.class)
	public void TestJoining() throws TestExcp, CumExcpIllegalSeshStatus,
			CumExcpChnlNotExist, CumExcpIllegalChnlStatus {
		AudSesh sesh = gotoJoining();
		sesh.lvChnlFailed(chnlNameA, rslt);
	}

	@Test
	public void TestJoined() throws TestExcp, CumExcpIllegalSeshStatus,
			CumExcpChnlNotExist, CumExcpIllegalChnlStatus {
		AudSesh sesh = gotoJoined();

		try {
			sesh.joinChnl(chnlNameA);
			sesh.chnlJoined(chnlNameA);
			AudChnl chnl = (AudChnl) sesh.chnls.get(chnlNameA);
			chnl.lvChnl();
		} catch (CumExcpIllegalSeshStatus e) {
			e.printStackTrace();
			throw new TestExcp("lvChnl");
		} catch (CumExcpChnlNotExist e) {
			e.printStackTrace();
			throw new TestExcp("lvChnl");
		}

		sesh.lvChnlFailed(chnlNameA, rslt);
		DummyChniView chnlView = seshView.getChnlView();

		assertEquals(rslt, chnlView.getRslt());
	}

	@Test(expected = CumExcpChnlNotExist.class)
	public void TestJoined_NOCHNL() throws TestExcp, CumExcpIllegalSeshStatus,
			CumExcpChnlNotExist, CumExcpIllegalChnlStatus {
		AudSesh sesh = gotoJoined();

		try {
			sesh.joinChnl(chnlNameA);
			sesh.chnlJoined(chnlNameA);
			AudChnl chnl = (AudChnl) sesh.chnls.get(chnlNameA);
			chnl.lvChnl();
		} catch (CumExcpIllegalSeshStatus e) {
			e.printStackTrace();
			throw new TestExcp("lvChnl");
		} catch (CumExcpChnlNotExist e) {
			e.printStackTrace();
			throw new TestExcp("lvChnl");
		}

		sesh.lvChnlFailed("XXX", rslt);
	}

	@Test
	public void TestLving() throws TestExcp, CumExcpIllegalSeshStatus,
			CumExcpChnlNotExist, CumExcpIllegalChnlStatus {
		AudSesh sesh = gotoLving();
		sesh.lvChnlFailed(chnlNameA, rslt);
	}

	@Test(expected = CumExcpIllegalSeshStatus.class)
	public void TestClsed() throws TestExcp, CumExcpIllegalSeshStatus,
			CumExcpChnlNotExist, CumExcpIllegalChnlStatus {
		AudSesh sesh = gotoClsed();
		sesh.lvChnlFailed(chnlNameA, rslt);
	}

	@Test(expected = CumExcpIllegalSeshStatus.class)
	public void TestDscned() throws TestExcp, CumExcpIllegalSeshStatus,
			CumExcpChnlNotExist, CumExcpIllegalChnlStatus {
		AudSesh sesh = gotoDscned();
		sesh.lvChnlFailed(chnlNameA, rslt);
	}

}
