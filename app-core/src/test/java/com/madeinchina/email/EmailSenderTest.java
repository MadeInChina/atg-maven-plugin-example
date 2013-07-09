
package com.madeinchina.email;

import atg.multisite.SiteManager;
import atg.nucleus.Nucleus;
import atg.nucleus.NucleusTestUtils;
import atg.servlet.ServletTestUtils;
import atg.test.AtgDustCase;
import atg.userprofiling.email.TemplateEmailInfo;

public class EmailSenderTest extends AtgDustCase {
    private EmailService emailService;

    public void setUp() throws Exception {
        setConfigurationLocation("src/test/resources/config");

        emailService = (EmailService) resolveNucleusComponent("/atg/dynamo/service/EmailService");

    }

    public void test_should_not_return_null_when_get_atg_email_sender() throws Exception {
        assertNotNull(resolveNucleusComponent("/atg/dynamo/service/SMTPEmail"));
    }

    public void test_should_not_return_null_when_get_email_service() throws Exception {
        assertNotNull(emailService);

    }

    public void test_should_not_return_null_when_get_email_sender() throws Exception {
        assertNotNull(emailService.getSmtpEmailSender());
    }

    public void test_should_return_true_when_send_email_success() throws Exception {
        // Test ok. ingore send email every time.
        // assertTrue(emailService.sendEmail("test", "from", "to"));
    }

    public void test_should_not_return_null_when_get_atg_template_email_sender() throws Exception {
        assertNotNull(resolveNucleusComponent("/atg/userprofiling/email/TestTemplateEmailSender"));
    }

    public void test_should_not_return_when_get_template_email_sender() {
        assertNotNull(emailService.getTemplateEmailSender());
    }

    public void test_should_not_return_null_when_get_atg_template_email_info() throws Exception {
        assertNotNull(resolveNucleusComponent("/atg/userprofiling/email/TestTemplateEmailInfo"));
    }

    public void test_should_not_return_when_get_template_email_info() {
        assertNotNull(emailService.getTemplateEmailInfo());
    }

//    public void test_should_return_true_when_send_email_by_template_success() throws Exception {
//        Nucleus mNucleus = NucleusTestUtils.startNucleusWithModules(new String[] {
//                "DAS", "DPS", "DSS", "DCS"
//        },
//                this.getClass(),// the prefix of config
//                "config",// the suffix of config
//                "/atg/dynamo/service/EmailService");
//        ServletTestUtils mServletTestUtils = new ServletTestUtils();
//        mServletTestUtils.createDynamoHttpServletRequestForSession(
//                mNucleus, "mySessionId", "new");
//        emailService = (EmailService) mNucleus.resolveName("/atg/dynamo/service/EmailService");
//        TemplateEmailInfo templateEmailInfo = (TemplateEmailInfo) mNucleus
//                .resolveName("/atg/userprofiling/email/TestTemplateEmailInfo");
//        SiteManager siteManager = (SiteManager) mNucleus.resolveName("/atg/multisite/SiteManager");
//        assertEquals(0, siteManager.getSiteCount());
//        assertNotNull(mNucleus.resolveName("/atg/multisite/SiteManager"));
//        assertTrue(emailService.sendEmailByTemplate(templateEmailInfo));
//
//    }
}
