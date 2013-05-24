/**
 * 
 */
package jp.happyhacking.cum.aud.seshLyr;

import static org.junit.Assert.assertTrue;
import jp.happyhacking.cum.aud.chnlLyr.AudChnl;
import jp.happyhacking.cum.aud.chnlLyr.TestExcp;
import jp.happyhacking.cum.aud.excp.CumExcpChnlNotExist;
import jp.happyhacking.cum.aud.excp.CumExcpIllegalChnlStatus;
import jp.happyhacking.cum.aud.excp.CumExcpIllegalSeshStatus;

import org.junit.Test;

/**
 * @author happyhacking70@gmail.com
 * 
 */
public class AudSesh_chnlLft_Test extends AudSeshTestAbst {

	@Test(expected = CumExcpIllegalSeshStatus.class)
	public void TestInit() throws CumExcpIllegalSeshStatus,
			CumExcpChnlNotExist, CumExcpIllegalChnlStatus {
		AudSesh sesh = getSesh();
		sesh.chnlLft(chnlNameA);
	}

	@Test(expected = CumExcpIllegalSeshStatus.class)
	public void TestJoining() throws TestExcp, CumExcpIllegalSeshStatus,
			CumExcpChnlNotExist, CumExcpIllegalChnlStatus {
		AudSesh sesh = gotoJoining();
		sesh.chnlLft(chnlNameA);
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

		sesh.chnlLft(chnlNameA);
		assertTrue(seshView.getChnlView().isChnlLft());
	}

	@Test
	public void TestLving() throws TestExcp, CumExcpIllegalSeshStatus,
			CumExcpChnlNotExist, CumExcpIllegalChnlStatus {
		AudSesh sesh = gotoLving();
		sesh.chnlLft(chnlNameA);

	}

	@Test(expected = CumExcpIllegalSeshStatus.class)
	public void TestClsed() throws TestExcp, CumExcpIllegalSeshStatus,
			CumExcpChnlNotExist, CumExcpIllegalChnlStatus {
		AudSesh sesh = gotoClsed();
		sesh.chnlLft(chnlNameA);
	}

	@Test(expected = CumExcpIllegalSeshStatus.class)
	public void TestDscned() throws TestExcp, CumExcpIllegalSeshStatus,
			CumExcpChnlNotExist, CumExcpIllegalChnlStatus {
		AudSesh sesh = gotoDscned();
		sesh.chnlLft(chnlNameA);
	}

}
