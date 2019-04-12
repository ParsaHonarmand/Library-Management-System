package librarysystem.testing;

import static org.junit.Assert.*;

//import java.util.List;

import org.junit.Test;

import librarysystem.LibrarySystem;
import librarysystem.managers.MaterialManager;
import librarysystem.materials.Material;
import librarysystem.materials.MaterialStatus;

public class GetMaterialTest {
	
	LibrarySystem library = new LibrarySystem();
	MaterialManager manager = library.getMaterialManager();
	Material outputTest1 = manager.getMaterial(MaterialStatus.AVAILABLE,"ASTR-101");
	Material outputTest2 = manager.getMaterial(MaterialStatus.RETURNED,"ASTR-101");
	Material outputTest3 = manager.getMaterial(MaterialStatus.RESERVED,"ASTR-101");
	
	@Test
	public void test1() {
		String output = outputTest1.toString();
		assertEquals("AVAILABLE|BOOK|25|ASTR-101|Astronomy 101 - 1st Edition|Mark Davis|1|-1|-1",output);
	}
	
	@Test
	public void test2() {
		String output = outputTest2.toString();
		assertEquals("RETURNED|BOOK|1|ASTR-101|Astronomy 101 - 1st Edition|Mark Davis|1|-1|-1",output);
	}
	
	@Test
	public void test3() {
		//String output = outputTest3.toString();
		assertEquals(null,outputTest3);
	}
	
	public static void main(String args[]) {
		GetMaterialTest test = new GetMaterialTest();
		test.test2();
	}
}
