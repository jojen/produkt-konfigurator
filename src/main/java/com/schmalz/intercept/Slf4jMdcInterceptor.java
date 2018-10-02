package com.schmalz.intercept;


import org.slf4j.MDC;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Spring MVC Interceptor to add message-id to ThreadLocal by the name 'mid' and refer to it in log4j log patterns with %X{mid}.
 * Uses Log4j Mapped Diagnostic Context (MDC).
 *
 * @see <a href="https://logging.apache.org/log4j/1.2/apidocs/org/apache/log4j/MDC.html">Log4j MDC</a>
 * @see <a href="https://logging.apache.org/log4j/1.2/apidocs/org/apache/log4j/PatternLayout.html">Log4j Log Patterns</a>
 * <p>
 * Created by Solcom-Schulten on 24.03.2017.
 */
public class Slf4jMdcInterceptor implements HandlerInterceptor {

    private static final String MID_KEY = "mid";
    private String mdcMessageFormat = "%s";
    private String messageIdFormat = "esb-%s";
    private String messageIdHeaderName = "Message-ID";

    /**
     * Allows to define a messageIdFormat String containing %s as placeholder for the message-id.
     * This can be used to define an application-specific message id. Default is "esb-%s".
     * @param messageIdFormat to use
     */
    public void setMessageIdFormat(String messageIdFormat) {
        this.messageIdFormat = messageIdFormat;
    }

    /**
     * Allows to define an mdcMessageFormat String containing %s as placeholder for the formatted message-id.
     * This can be used to decorate incoming message ids and the message id of this application alike.
     * Default is "%s".
     * @param mdcMessageFormat to apply to incoming and self-generated message ids
     */
    public void setMdcMessageFormat(String mdcMessageFormat) {
        this.mdcMessageFormat = mdcMessageFormat;
    }

    /**
     * Allows to define a custom message id http header name. Default is "Message-ID".
     * @param messageIdHeaderName to look for incoming message ids and to set in outgoing messages
     */
    public void setMessageIdHeaderName(String messageIdHeaderName) {
        this.messageIdHeaderName = messageIdHeaderName;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String messageId = request.getHeader(messageIdHeaderName);
        if (messageId == null) {
            messageId = getMessageId();
        }
        MDC.put(MID_KEY, String.format(mdcMessageFormat, messageId));
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        MDC.remove(MID_KEY);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // noop
    }


    private String getMessageId() {
        return String.format(messageIdFormat, String.valueOf(System.currentTimeMillis()));
    }
}
