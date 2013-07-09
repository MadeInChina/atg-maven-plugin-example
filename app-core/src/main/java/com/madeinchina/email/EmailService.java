
package com.madeinchina.email;

import javax.mail.Message;
import javax.mail.MessagingException;

import atg.nucleus.GenericService;
import atg.service.email.EmailException;
import atg.service.email.MimeMessageUtils;
import atg.service.email.SMTPEmailSender;
import atg.userprofiling.email.TemplateEmailException;
import atg.userprofiling.email.TemplateEmailInfo;
import atg.userprofiling.email.TemplateEmailSender;

public class EmailService extends GenericService {
    private SMTPEmailSender mSmtpEmailSender;
    private TemplateEmailSender mTemplateEmailSender;
    private TemplateEmailInfo mTemplateEmailInfo;

    public SMTPEmailSender getSmtpEmailSender() {
        return mSmtpEmailSender;
    }

    public void setSmtpEmailSender(SMTPEmailSender smtpEmailSender) {
        this.mSmtpEmailSender = smtpEmailSender;
    }

    public boolean sendEmail(String string, String string2, String string3) {

        try {
            Message msg = MimeMessageUtils.createMessage
                    ("dynamotester",
                            "I'm just testing the e-mail sender",
                            "test@example.com",
                            "Sorry to bother you, but I'm testing the e-mail sender");
            MimeMessageUtils.setRecipient(msg, Message.RecipientType.TO, "slamhan@aaxischina.com");
            getSmtpEmailSender().sendEmailMessage(msg);
            return true;
        } catch (EmailException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        } catch (MessagingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        }

    }

    public TemplateEmailSender getTemplateEmailSender() {
        return mTemplateEmailSender;
    }

    public void setTemplateEmailSender(TemplateEmailSender mTemplateEmailSender) {
        this.mTemplateEmailSender = mTemplateEmailSender;
    }

    public TemplateEmailInfo getTemplateEmailInfo() {
        return mTemplateEmailInfo;
    }

    public void setTemplateEmailInfo(TemplateEmailInfo mTemplateEmailInfo) {
        this.mTemplateEmailInfo = mTemplateEmailInfo;
    }

    public boolean sendEmailByTemplate(TemplateEmailInfo templateEmailInfo) {
        Object[] recipients = {
                "bill@example.com", "sam@example.com"
        };
        try {
            getTemplateEmailSender().sendEmailMessage(templateEmailInfo, recipients);
            return true;
        } catch (TemplateEmailException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        }

    }
}
