/**
 * 
 */

package com.madeinchina.email;

import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import atg.core.i18n.LayeredResourceBundle;
import atg.core.net.HTTPConnection;
import atg.core.util.ResourceUtils;
import atg.service.dynamo.LangLicense;
import atg.servlet.sessiontracking.GenericHttpSession;
import atg.userprofiling.email.RequestResponseInitializer;
import atg.userprofiling.email.TemplateEmailException;
import atg.userprofiling.email.TemplateInvoker;

/**
 * Customized template invoker. http server to be used for email rendering is
 * based on the site id passed as part of the email template parameters.
 * 
 * @author Asoni
 */
public class TestTemplateInvoker extends TemplateInvoker {

    String defaultMultisiteHttpServer;

    private static final String SITE = "site";

    private static final String SERVER_NAME = "serverName";

    private static final String HOST = "Host";

    private static final String CONNECTION = "Connection";

    private String currentMultisiteHostName = "";

    /*
     * (non-Javadoc)
     * @see atg.userprofiling.email.TemplateInvoker#createTemplateEmailSession()
     */
    @Override
    protected HttpSession createTemplateEmailSession() throws TemplateEmailException {

        URL url = null;
        String dynSessionId = null;
        String cookieString = "";
        HTTPConnection conn = null;
        try {
            url = new URL("http", getSiteHttpServerName(), getSiteHttpServerPort(),
                    (new StringBuilder())
                            .append(getApplicationPrefix()).append(getInitSessionURL()).toString());
            if (isLoggingDebug())
                logDebug("createTemplateEmailSession() " + url);
            conn = new HTTPConnection(url);
            conn.setRequestProperty(HOST, getSiteHttpServerName());
            conn.setRequestProperty(CONNECTION, "close");
            Object cookies = conn.getHeaderFields("set-cookie");
            Vector values;
            if (cookies instanceof Vector) {
                values = (Vector) cookies;
            } else {
                values = new Vector(1);
                values.addElement(cookies);
            }
            if (values != null) {
                for (int i = 0; i < values.size(); i++) {
                    String value = (String) values.elementAt(i);
                    if (value == null)
                        continue;
                    value = value.trim();
                    int ix = value.indexOf("=");
                    if (ix == -1)
                        continue;
                    String cookieName = value.substring(0, ix);
                    cookieName = cookieName.trim();
                    if (cookieName.equalsIgnoreCase("dynsessionid")) {
                        value = value.substring(ix + 1);
                        ix = value.indexOf(";");
                        if (ix != -1)
                            value = value.substring(0, ix);
                        dynSessionId = value;
                        continue;
                    }
                    if (cookieName.equalsIgnoreCase("dynurlsessionid"))
                        continue;
                    value = value.substring(ix + 1);
                    ix = value.indexOf(";");
                    if (ix != -1)
                        value = value.substring(0, ix);
                    if (!cookieString.equals(""))
                        cookieString = (new StringBuilder()).append(cookieString).append(";")
                                .toString();
                    cookieString = (new StringBuilder()).append(cookieString).append(cookieName)
                            .append("=")
                            .append(value).toString();
                }

            }
        } catch (IOException e) {
            throw new TemplateEmailException(e.toString(), e);
        } finally {
            if (conn != null)
                try {
                    conn.close();
                } catch (IOException e) {
                    if (isLoggingError()) {
                        logError("An exception was thrown while close the HTTPConnection:", e);
                    }
                }
        }
        // if (dynSessionId == null) {
        // Object args[] = {
        // url, getAbsoluteName()
        // };
        // throw new
        // TemplateEmailException(ResourceUtils.getMsgResource("emailNoDynSessionId",
        // "atg.userprofiling.ProfileResources",
        // LayeredResourceBundle.getBundle("atg.userprofiling.ProfileResources",
        // LangLicense.getLicensedDefault()), args));
        // }
        HttpSession session = getSessionManager().getSession(dynSessionId);
        // if (session == null) {
        // Object args[] = {
        // dynSessionId, url
        // };
        // throw new TemplateEmailException(ResourceUtils.getMsgResource(
        // "emailNoSessionForDynSessionId",
        // "atg.userprofiling.ProfileResources",
        // LayeredResourceBundle.getBundle("atg.userprofiling.ProfileResources",
        // LangLicense.getLicensedDefault()), args));
        // }
        session = new GenericHttpSession("0D2893261648052F71FE77F6F97B02CE");
        session.setAttribute("atg.templateEmail.sessionCookies", "dynsessionid=0D2893261648052F71FE77F6F97B02CE");
        session.setAttribute("atg.parent.session.id", "0D2893261648052F71FE77F6F97B02CE");
        if (isLoggingDebug())
            logDebug((new StringBuilder()).append("created session ").append(session)
                    .append(" with id ")
                    .append(session.getId()).append(" parent session id = ").append(dynSessionId)
                    .append(
                            ", cookieString = ").append(cookieString).toString());

        return session;
    }
}
