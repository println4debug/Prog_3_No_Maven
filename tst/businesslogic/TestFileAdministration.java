/*

    Author:         Simon Furitsch
    E-Mail:         Simon.Furitsch@Student.HTW-Berlin.de
    Martrikel Nr:   578153

 */
package businesslogic;

import mediaDB.Uploader;
import mediaDB.implementation.Audio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestFileAdministration {
    public FileAdministration testAdm;

    @BeforeEach
    public void setUp() {
        this.testAdm = new FileAdministration();
    }

    @Test
    public void test_createUploader_WithString() {
        boolean actual = testAdm.createUploader("Test");

        Assertions.assertTrue(actual);
    }

    @Test
    public void test_createUploader_WithString_Null() {

        boolean actual = testAdm.createUploader("");

        Assertions.assertFalse(actual);
    }
    @Test
    public void test_createUploader_WithString_WhileUploaderExists() {
        testAdm.createUploader("Test");

        boolean last = testAdm.createUploader("Test");

        Assertions.assertFalse(last);

    }

    @Test
    public void test_createUploader_WithObj() {
        Uploader u = new mediaDB.implementation.Uploader("Test");
        boolean actual = testAdm.createUploader(u);

        Assertions.assertTrue(actual);
    }

    @Test
    public void test_createUploader_WithObj_Null() {
        Uploader u = null;

        assertThrows(NullPointerException.class,()->{
            boolean actual = testAdm.createUploader(u);
        });

    }
    @Test
    public void test_createUploader_WithObj_WhileUploaderExists() {
        Uploader u = new mediaDB.implementation.Uploader("Test");
        testAdm.createUploader(u);

        boolean last = testAdm.createUploader(u);

        Assertions.assertFalse(last);

    }

    @Test
    public void test_deleteUploader_WithObj() {
        Uploader u = new mediaDB.implementation.Uploader("Test");
        testAdm.createUploader(u);

        boolean actual = testAdm.deleteUploader(u);

        Assertions.assertTrue(actual);
    }

    @Test
    public void test_deleteUploader_WithObj_NotExists() {
        Uploader u = new mediaDB.implementation.Uploader("Test");

        boolean actual = testAdm.deleteUploader(u);

        Assertions.assertFalse(actual);
    }

    @Test
    public void test_deleteUploader_WithObj_Null() {
        Uploader u = null;

        boolean actual = testAdm.deleteUploader(u);

        Assertions.assertFalse(actual);
    }

    @Test
    public void test_deleteUploader_WithString() {
        testAdm.createUploader("Test");

        boolean actual = testAdm.deleteUploader("Test");

        Assertions.assertTrue(actual);
    }

    @Test
    public void test_deleteUploader_WithString_NotExists() {
        testAdm.createUploader("Test");

        boolean actual = testAdm.deleteUploader("Test2");

        Assertions.assertFalse(actual);
    }

    @Test
    public void test_getAllUploadersWithUploadCount() {
        testAdm.createUploader("Test");
        String actual = testAdm.getAllUploadersWithUploadCount();
        System.out.println(actual);
        //assertTrue(actual.equals("Test Uploads: 0"));
        assertEquals(actual.length(),("Test Uploads: 0" + System.lineSeparator()).length());
    }

    @Test
    public void test_addUpload_WithType() {
        Uploader u = new mediaDB.implementation.Uploader("Test");
        testAdm.createUploader(u);

        boolean actual = testAdm.addUpload("Audio",u);

        assertTrue(actual);
    }

    @Test
    public void test_addUpload_WithType_TypeNotExists() {
        Uploader u = new mediaDB.implementation.Uploader("Test");
        testAdm.createUploader(u);

        boolean actual = testAdm.addUpload("NotExists",u);

        assertFalse(actual);
    }

    @Test
    public void test_addUpload_WithType_UploaderNotExists() {
       // assertThrows(IllegalArgumentException.class,()->{
        boolean actual = testAdm.addUpload("Audio",new mediaDB.implementation.Uploader("Test"));
        Assertions.assertEquals(false,actual);
        //});
    }

    @Test
    public void test_addUpload_WithObj() {
        Uploader u = new mediaDB.implementation.Uploader("Test");
        testAdm.createUploader(u);
        Audio a = new Audio(u,"ABC");

        boolean actual = testAdm.addUpload(a);

        assertTrue(actual);

    }

    @Test
    public void test_addUpload_WithObj_NotInitalized() {
        Audio a = new Audio();

        assertThrows(IllegalArgumentException.class,()->{
            testAdm.addUpload(a);
        });

    }

    @Test
    public void test_addUpload_WithObj_UploaderUnknown() {
        Uploader u = new mediaDB.implementation.Uploader("Test");
        Audio a = new Audio(u,"sss");


        assertThrows(IllegalArgumentException.class,()->{
            testAdm.addUpload(a);
        });

    }

    @Test
    public void test_removeUpload() {
        Uploader u = new mediaDB.implementation.Uploader("Test");
        Audio a = new Audio(u,"ss");
        testAdm.createUploader(u);
        testAdm.addUpload(a);

        boolean actual = testAdm.removeUpload(a);

        assertTrue(actual);
    }

    @Test
    public void test_removeUpload_FileNotExists() {
        Uploader u = new mediaDB.implementation.Uploader("Test");
        Audio a = new Audio(u,"ss");
        testAdm.createUploader(u);

        boolean actual = testAdm.removeUpload(a);

        assertFalse(actual);
    }

    @Test
    public void test_CreateUniqueAdress() {
        String actual = testAdm.createUniqueAddress();
        for(int i = 0; i<1000;i++) {
            String toCompare = testAdm.createUniqueAddress();
            assertNotEquals(actual,toCompare);
        }
    }

    @Test
    public void test_increaseAccesscount() {
        Uploader u = new mediaDB.implementation.Uploader("Test");
        Audio a = new Audio(u,"sss");
        testAdm.increaseAccessCount(a);
        assertEquals(1,a.getAccessCount());
    }

}
