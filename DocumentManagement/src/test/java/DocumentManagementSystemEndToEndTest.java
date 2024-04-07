import org.example.DocumentManagementSystem;
import org.example.exception.AccessManagementException;
import org.example.exception.DocumentManagementException;
import org.example.exception.UserManagementException;
import org.example.model.Document;
import org.example.model.User;
import org.example.service.AccessManagement;
import org.example.service.DocumentManagement;
import org.example.service.UserManagement;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class DocumentManagementSystemEndToEndTest {
    private DocumentManagementSystem dms;

    @Before
    public void setUp() {
        UserManagement userManagement = new UserManagement();
        AccessManagement accessManagement = new AccessManagement();
        DocumentManagement documentManagement = new DocumentManagement();

        dms = DocumentManagementSystem.getInstance(userManagement, accessManagement, documentManagement);
    }

    @Test
    public void testLoginUser() throws UserManagementException, DocumentManagementException {
        dms.createUser("user2", "password2", "User 2");
        User loggedInUser = dms.loginUser("user2", "password2");
        assertNotNull(loggedInUser);
        assertEquals("user2", loggedInUser.getUserId());
    }

    @Test
    public void testUpdateDocument() throws UserManagementException, DocumentManagementException, AccessManagementException {
        User user = new User("user4", "password4", "User 4");
        dms.createUser("user4", "password4", "User 4");
        dms.createDocument("doc2", user);
        dms.updateDocument("doc2", user, "Version 1");
        Document document = dms.getDocument("doc2");
        assertNotNull(document);
        assertEquals("Version 1", document.getLatestVersion());
    }

    @Test
    public void testRevertToVersion() throws UserManagementException, DocumentManagementException, AccessManagementException {
        User user = new User("user5", "password5", "User 5");
        dms.createUser("user5", "password5", "User 5");
        dms.createDocument("doc3", user);
        dms.updateDocument("doc3", user, "Version 1");
        dms.updateDocument("doc3", user, "Version 2");
        dms.revertToVersion("doc3", user, 0);
        Document document = dms.getDocument("doc3");
        assertNotNull(document);

        // We are using versionIndex so versionIndex set to 0 ie Version1
        assertEquals("Version 1", document.getLatestVersion());
    }

    @Test
    public void testGetDocument() throws UserManagementException, DocumentManagementException {
        User user = new User("user6", "password6", "User 6");
        dms.createUser("user6", "password6", "User 6");
        dms.createDocument("doc4", user);
        Document document = dms.getDocument("doc4");
        assertNotNull(document);
        assertEquals("doc4", document.getDocumentId());
    }

    @Test
    public void testEndToEnd() throws UserManagementException, DocumentManagementException, AccessManagementException {
        dms.createUser("user1", "password1", "User 1");
        User user = dms.loginUser("user1", "password1");
        dms.createDocument("doc1", user);

        dms.updateDocument("doc1", user, "Version 1");
        dms.updateDocument("doc1", user, "Version 2");

        Document document = dms.getDocument("doc1");

        assertNotNull(document);
        assertEquals("doc1", document.getDocumentId());
        assertEquals(user, document.getAuthor());
        assertEquals("Version 2", document.getLatestVersion());
    }
}
