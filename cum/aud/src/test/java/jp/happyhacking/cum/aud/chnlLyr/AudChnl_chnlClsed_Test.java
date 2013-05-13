/**
 * 
 */
package jp.happyhacking.cum.aud.chnlLyr;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import jp.happyhacking.cum.aud.excp.CumExcpIllegalChnlStatus;

import org.junit.Test;

/**
 * @author happyhacking70@gmail.com
 * 
 */
public class AudChnl_chnlClsed_Test extends AudChnlTestAbst {

	@Test
	public void test_init() throws CumExcpIllegalChnlStatus {
		AudChnl chnl = getChnl();

		assertFalse(chnlView.isClosed());
		chnl.chnlClsed();
		assertTrue(chnlView.isClosed());
	}

	@Test
	public void test_joining() throws TestExcp, CumExcpIllegalChnlStatus {
		AudChnl chnl = goto_joining();
		assertFalse(chnlView.isClosed());
		chnl.chnlClsed();
		assertTrue(chnlView.isClosed());
	}

	@Test
	public void test_joined() throws TestExcp, CumExcpIllegalChnlStatus {
		AudChnl chnl = goto_joined();
		assertFalse(chnlView.isClosed());
		chnl.chnlClsed();
		assertTrue(chnlView.isClosed());
	}

	@Test
	public void test_rjcting() throws TestExcp, CumExcpIllegalChnlStatus {
		AudChnl chnl = goto_rjcting();
		assertFalse(chnlView.isClosed());
		chnl.chnlClsed();
		assertTrue(chnlView.isClosed());

	}

	@Test
	public void test_lving() throws CumExcpIllegalChnlStatus, TestExcp {
		AudChnl chnl = goto_lving();
		assertFalse(chnlView.isClosed());
		chnl.chnlClsed();
		assertTrue(chnlView.isClosed());

	}

	@Test(expected = CumExcpIllegalChnlStatus.class)
	public void test_clsed() throws TestExcp, CumExcpIllegalChnlStatus {
		AudChnl chnl = goto_clsed();
		chnl.chnlClsed();
	}

	@Test(expected = CumExcpIllegalChnlStatus.class)
	public void test_dscned() throws TestExcp, CumExcpIllegalChnlStatus {
		AudChnl chnl = goto_dscned();
		chnl.chnlClsed();
	}

}
