package librarysystem.testing;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import librarysystem.LibrarySystem;
import librarysystem.managers.MaterialManager;
import librarysystem.materials.Material;
import librarysystem.materials.MaterialStatus;

public class GetUniqueMaterialsTest {
	
	LibrarySystem library = new LibrarySystem();
	MaterialManager manager = library.getMaterialManager();
	List<Material> output = manager.getUniqueMaterials(MaterialStatus.AVAILABLE);

	@Test
	public void test1() {
		int index = 0;
		String testOutput = output.get(index).toString();
		
		if(index < output.size()) {
		assertEquals("AVAILABLE|BOOK|25|ASTR-101|Astronomy 101 - 1st Edition|Mark Davis|1|-1|-1",testOutput);
		}
		else {
			fail("Index out of bounds");
		}
	}
	
	@Test
	public void test2() {
		int index = 1;
		
		String testOutput = output.get(index).toString();
		
		if(index < output.size()) {
		assertEquals("AVAILABLE|BOOK|30|ID|book|sohaib|1|-1|-1",testOutput);
		}
		else {
			fail("Index out of bounds");
		}
	}
	
	@Test
	public void test3() {
		int index = 2;
		
		String testOutput = output.get(index).toString();
		
		if(index < output.size()) {
		assertEquals("AVAILABLE|BOOK|47|ECON-101|Economy 101 - 2nd Edition|Liam Scott|2|-1|-1",testOutput);
		}
		else {
			fail("Index out of bounds");
		}
	}
	
	@Test
	public void test4() {
		int index = 3;
		
		String testOutput = output.get(index).toString();
		
		if(index < output.size()) {
		assertEquals("AVAILABLE|EBOOK|54|newebook|ebook|sohaib|1|-1|-1",testOutput);
		}
		else {
			fail("Index out of bounds");
		}
	}
	
	@Test
	public void test5() {
		int index = 4;
		
		String testOutput = output.get(index).toString();
		
		if(index < output.size()) {
		assertEquals("AVAILABLE|MAGAZINE|55|SICK-MAGAZINE|Super Sick Magazine|Magazine author|1|-1|-1",testOutput);
		}
		else {
			fail("Index out of bounds");
		}
	}
	
	public static void main(String args[]) {
		//GetUniqueMaterialsTest test = new GetUniqueMaterialsTest();
		//test.test2();
	}

}
