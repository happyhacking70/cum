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
public class AudChnl_chnlDscned_Test extends AudChnlTestAbst {

	@Test
	public void test_init() throws CumExcpIllegalChnlStatus {
		AudChnl chnl = getChnl();
		assertFalse(chnlView.isDscned());
		chnl.chnlDsconed();
		assertTrue(chnlView.isDscned());
	}

	@Test
	public void test_joining() throws TestExcp, CumExcpIllegalChnlStatus {
		AudChnl chnl = goto_joining();
		assertFalse(chnlView.isDscned());
		chnl.chnlDsconed();
		assertTrue(chnlView.isDscned());
	}

	@Test
	public void test_joined() throws TestExcp, CumExcpIllegalChnlStatus {
		AudChnl chnl = goto_joined();
		assertFalse(chnlView.isDscned());
		chnl.chnlDsconed();
		assertTrue(chnlView.isDscned());
	}

	@Test
	public void test_rjcting() throws TestExcp, CumExcpIllegalChnlStatus {
		AudChnl chnl = goto_rjcting();
		assertFalse(chnlView.isDscned());
		chnl.chnlDsconed();
		assertTrue(chnlView.isDscned());
	}

	@Test
	public void test_lving() throws TestExcp, CumExcpIllegalChnlStatus {
		AudChnl chnl = goto_lving();
		assertFalse(chnlView.isDscned());
		chnl.chnlDsconed();
		assertTrue(chnlView.isDscned());
	}

	@Test(expected = CumExcpIllegalChnlStatus.class)
	public void test_clsed() throws TestExcp, CumExcpIllegalChnlStatus {
		AudChnl chnl = goto_clsed();
		assertFalse(chnlView.isDscned());
		chnl.chnlDsconed();
	}

	@Test(expected = CumExcpIllegalChnlStatus.class)
	public void test_dscned() throws TestExcp, CumExcpIllegalChnlStatus {
		AudChnl chnl = goto_dscned();
		chnl.chnlDsconed();
	}

}
