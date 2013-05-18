/**
 * 
 */
package jp.happyhacking.cum.aud.chnlLyr;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import jp.happyhacking.cum.aud.excp.CumExcpIgnoreChnlStatus;
import jp.happyhacking.cum.aud.excp.CumExcpIllegalChnlStatus;

import org.junit.Test;

/**
 * @author happyhacking70@gmail.com
 * 
 */
public class AudChnl_seshLving_Test extends AudChnlTestAbst {

	@Test
	public void test_init() throws CumExcpIgnoreChnlStatus,
			CumExcpIllegalChnlStatus {
		AudChnl chnl = getChnl();
		chnl.seshLving();
		assertTrue(chnlView.isSeshLving());
		assertEquals(AudChnl.Status.init, chnl.chnlStatus);

	}

	@Test
	public void test_joining() throws TestExcp, CumExcpIgnoreChnlStatus,
			CumExcpIllegalChnlStatus {
		AudChnl chnl = goto_joining();
		chnl.seshLving();
		assertTrue(chnlView.isSeshLving());
		assertEquals(AudChnl.Status.init, chnl.chnlStatus);
	}

	@Test
	public void test_joined() throws TestExcp, CumExcpIgnoreChnlStatus,
			CumExcpIllegalChnlStatus {
		AudChnl chnl = goto_joined();
		chnl.seshLving();
		assertTrue(chnlView.isSeshLving());
		assertEquals(AudChnl.Status.init, chnl.chnlStatus);
	}

	@Test
	public void test_rjcting() throws TestExcp, CumExcpIgnoreChnlStatus,
			CumExcpIllegalChnlStatus {
		AudChnl chnl = goto_rjcting();
		chnl.seshLving();
		assertTrue(chnlView.isSeshLving());
		assertEquals(AudChnl.Status.init, chnl.chnlStatus);
	}

	@Test
	public void test_lving() throws TestExcp, CumExcpIgnoreChnlStatus,
			CumExcpIllegalChnlStatus {
		AudChnl chnl = goto_lving();
		chnl.seshLving();
		assertTrue(chnlView.isSeshLving());
		assertEquals(AudChnl.Status.init, chnl.chnlStatus);

	}

	@Test
	public void test_clsed() throws TestExcp, CumExcpIgnoreChnlStatus,
			CumExcpIllegalChnlStatus {
		AudChnl chnl = goto_clsed();

		AudChnl.Status status = chnl.chnlStatus;
		chnl.seshLving();
		assertEquals(status, chnl.chnlStatus);

	}

	@Test(expected = CumExcpIllegalChnlStatus.class)
	public void test_dscned() throws TestExcp, CumExcpIgnoreChnlStatus,
			CumExcpIllegalChnlStatus {
		AudChnl chnl = goto_dscned();
		chnl.seshLving();
	}
}
