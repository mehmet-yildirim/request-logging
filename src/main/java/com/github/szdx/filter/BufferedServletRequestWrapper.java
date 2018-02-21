package com.github.szdx.filter;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.NotImplementedException;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.*;

@Slf4j
public class BufferedServletRequestWrapper extends HttpServletRequestWrapper {
    private BufferedServletInputStream bufferedServletInputStream;

    private ByteArrayOutputStream cache;

    public BufferedServletRequestWrapper(HttpServletRequest request) throws IOException {
        super(request);
        this.cache = new ByteArrayOutputStream();

        InputStream is = request.getInputStream();
        IOUtils.copy(is, cache);
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        ByteArrayInputStream bis = new ByteArrayInputStream(cache.toByteArray());
        bufferedServletInputStream = new BufferedServletInputStream(bis);
        return bufferedServletInputStream;
    }

    @Override
    public BufferedReader getReader() throws IOException {
        return IOUtils.buffer(new InputStreamReader(getInputStream(), getCharacterEncoding()));
    }

    public String getBody() {
        return new String(cache.toByteArray());
    }

    private class BufferedServletInputStream extends ServletInputStream {

        private final ByteArrayInputStream bis;

        public BufferedServletInputStream(ByteArrayInputStream bis) {
            this.bis = bis;
        }

        @Override
        public boolean isFinished() {
            return bis.available() == 0;
        }

        @Override
        public boolean isReady() {
            return true;
        }

        @Override
        public void setReadListener(ReadListener readListener) {
            throw new NotImplementedException("Not implemented");
        }

        @Override
        public int read() throws IOException {
            int r = bis.read();

            cache.write(r);
            return r;
        }
    }
}
