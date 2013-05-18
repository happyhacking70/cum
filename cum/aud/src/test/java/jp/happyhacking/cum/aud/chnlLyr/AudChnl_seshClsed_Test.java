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
public class AudChnl_seshClsed_Test extends AudChnlTestAbst {

	@Test
	public void test_init() throws CumExcpIgnoreChnlStatus,
			CumExcpIllegalChnlStatus {
		AudChnl chnl = getChnl();
		chnl.seshClsed();
		assertTrue(chnlView.isSeshClsed());
		assertEquals(AudChnl.Status.clsed, chnl.chnlStatus);

	}

	@Test
	public void test_joining() throws TestExcp, CumExcpIgnoreChnlStatus,
			CumExcpIllegalChnlStatus {
		AudChnl chnl = goto_joining();
		chnl.seshClsed();
		assertTrue(chnlView.isSeshClsed());
		assertEquals(AudChnl.Status.clsed, chnl.chnlStatus);
	}

	@Test
	public void test_joined() throws TestExcp, CumExcpIgnoreChnlStatus,
			CumExcpIllegalChnlStatus {
		AudChnl chnl = goto_joined();
		chnl.seshClsed();
		assertTrue(chnlView.isSeshClsed());
		assertEquals(AudChnl.Status.clsed, chnl.chnlStatus);
	}

	@Test
	public void test_rjcting() throws TestExcp, CumExcpIgnoreChnlStatus,
			CumExcpIllegalChnlStatus {
		AudChnl chnl = goto_rjcting();
		chnl.seshClsed();
		assertTrue(chnlView.isSeshClsed());
		assertEquals(AudChnl.Status.clsed, chnl.chnlStatus);
	}

	@Test
	public void test_lving() throws TestExcp, CumExcpIgnoreChnlStatus,
			CumExcpIllegalChnlStatus {
		AudChnl chnl = goto_lving();
		chnl.seshClsed();
		assertTrue(chnlView.isSeshClsed());
		assertEquals(AudChnl.Status.clsed, chnl.chnlStatus);

	}

	@Test
	public void test_clsed() throws TestExcp, CumExcpIgnoreChnlStatus,
			CumExcpIllegalChnlStatus {
		AudChnl chnl = goto_clsed();

		AudChnl.Status status = chnl.chnlStatus;

		chnl.seshClsed();

		assertEquals(status, chnl.chnlStatus);

	}

	@Test(expected = CumExcpIllegalChnlStatus.class)
	public void test_dscned() throws TestExcp, CumExcpIgnoreChnlStatus,
			CumExcpIllegalChnlStatus {
		AudChnl chnl = goto_dscned();
		chnl.seshClsed();
	}
}
