package jobs4u.infrastructure.auth.domain;

import eapli.framework.general.domain.model.EmailAddress;
import eapli.framework.infrastructure.authz.domain.model.Username;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Jobs4uUserTest {

    @Test
    void constructor_shouldCreateJobs4uUserObject() {
        Username username = Username.valueOf("test_user");
        Password password = new Password("test_password");
        EmailAddress email = EmailAddress.valueOf("test@example.com");
        Jobs4uUserRoles role = Jobs4uUserRoles.CANDIDATE;
        boolean isEnabled = true;

        Jobs4uUser user = new Jobs4uUser(username, password, email, role, isEnabled);

        assertNotNull(user);
        assertEquals(username, user.getUsername());
        assertEquals(password, user.getPassword());
        assertEquals(email, user.getEmail());
        assertEquals(role, user.getRole());
        assertEquals(isEnabled, user.isEnabled());
    }

    @Test
    void toggleStatus_shouldToggleUserStatus() {
        Username username = Username.valueOf("test_user");
        Password password = new Password("test_password");
        EmailAddress email = EmailAddress.valueOf("test@example.com");
        Jobs4uUserRoles role = Jobs4uUserRoles.CANDIDATE;
        boolean isEnabled = true;
        Jobs4uUser user = new Jobs4uUser(username, password, email, role, isEnabled);

        user.toggleStatus();

        assertFalse(user.isEnabled());
    }

    @Test
    void toString_shouldReturnStringRepresentation() {
        Username username = Username.valueOf("test_user");
        Password password = new Password("test_password");
        EmailAddress email = EmailAddress.valueOf("test@example.com");
        Jobs4uUserRoles role = Jobs4uUserRoles.CANDIDATE;
        boolean isEnabled = true;
        Jobs4uUser user = new Jobs4uUser(username, password, email, role, isEnabled);

        String result = user.toString();

        assertNotNull(result);
        assertTrue(result.contains("username=test_user"));
        assertTrue(result.contains("password="));
        assertTrue(result.contains("email=test@example.com"));
        assertTrue(result.contains("role=CANDIDATE"));
        assertTrue(result.contains("isEnabled=true"));
    }
}