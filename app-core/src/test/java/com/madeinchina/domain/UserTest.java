
package com.madeinchina.domain;

import java.io.File;

import atg.test.AtgDustCase;

public class UserTest extends AtgDustCase {
    public void setUp() throws Exception {
        // copyConfigurationFiles(new String[] {
        // "src/test/resources/config".replace(
        // "/", File.separator)
        // }, "target/test-classes/config".replace("/",
        // File.separator), ".svn", "CVS");
        setConfigurationLocation("src/test/resources/config");
    }

    public void test_should_return_user_when_get_from_nucleus() throws Exception {
        // setConfigurationLocation("src/test/resources/config");
        User user = (User) resolveNucleusComponent("/test/User");
        assertNotNull(user);
        assertEquals("test", user.getUserId());
    }
}
