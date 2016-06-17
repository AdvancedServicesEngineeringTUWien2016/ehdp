package at.ac.tuwien.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URLEncoder;

/**
 * url-encodes a path in order to access it using a single string
 * requested urls that start with a prefix will be forwarded to
 * a specific route, e.g. /api/foo/bar may be forwarded to
 * /data/foo%2Fbar
 * optionally the url encoding for a number of path variables
 * can be configured, i.e. after the n-th slash, the encoding
 * takes place
 */
public abstract class AbstractApiFilter implements Filter {
    private String prefix;
    private String forward;
    private int keepNPathVariables = 0;

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getForward() {
        return forward;
    }

    public void setForward(String forward) {
        this.forward = forward;
    }

    public int getKeepNPathVariables() {
        return keepNPathVariables;
    }

    public void setKeepNPathVariables(int keepNPathVariables) {
        this.keepNPathVariables = keepNPathVariables;
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;

        String s = request.getRequestURI();
        String ss = s.substring(Math.min(s.length(), prefix.length()));
        int slash = -1;

        if (s.startsWith(prefix)) {
            String fs = forward;
            for (int i = 0; i < keepNPathVariables && (slash = ss.indexOf("/")) >= 0; i++) {
                slash++;
                fs += ss.substring(0, slash);
                ss = ss.substring(slash);
            }
            fs += URLEncoder.encode(ss, "UTF-8");
            req.getRequestDispatcher(fs).forward(req, res);
        } else {
            chain.doFilter(req, res);
        }
    }

    @Override
    public void destroy() {}

    @Override
    public void init(FilterConfig arg0) throws ServletException {}

}
