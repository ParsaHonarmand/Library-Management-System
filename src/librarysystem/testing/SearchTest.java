package librarysystem.testing;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import librarysystem.LibrarySystem;
import librarysystem.managers.MaterialManager;
import librarysystem.materials.Material;
import librarysystem.materials.MaterialStatus;

public class SearchTest {
	
	LibrarySystem library = new LibrarySystem();
	MaterialManager manager = library.getMaterialManager();

	@Test
	public void test1() {
		String sample = "astro";
		List<Material> output = manager.search(sample, MaterialStatus.AVAILABLE);
		String expected = output.get(0).toString();
		assertEquals("AVAILABLE|BOOK|25|ASTR-101|Astronomy 101 - 1st Edition|Mark Davis|1|-1|-1",expected);
	}
	
	@Test
	public void test2() {
		String sample = "AstrO";
		List<Material> output = manager.search(sample, MaterialStatus.AVAILABLE);
		String expected = output.get(1).toString();
		assertEquals("AVAILABLE|BOOK|26|ASTR-101|Astronomy 101 - 1st Edition|Mark Davis|1|-1|-1",expected);
		System.out.println(output);	
		
	}
	
	@Test
	public void test3() {
		String sample = "Econ";
		List<Material> output = manager.search(sample, MaterialStatus.AVAILABLE);
		String expected = output.get(0).toString();
		assertEquals("AVAILABLE|BOOK|47|ECON-101|Economy 101 - 2nd Edition|Liam Scott|2|-1|-1",expected);
		
	}
	
	@Test
	public void test4() {
		String sample = "eCOn";
		List<Material> output = manager.search(sample, MaterialStatus.AVAILABLE);
		String expected = output.get(4).toString();
		assertEquals("AVAILABLE|BOOK|53|ECON-101|Economy 101 - 2nd Edition|Liam Scott|2|-1|-1",expected);
		
	}
	
	public static void main(String args[]) {
		SearchTest test = new SearchTest();
		test.test1();
	}

}
