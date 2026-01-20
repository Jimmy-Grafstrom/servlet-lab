package se.jimmy.iths;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletResponseWrapper;
import java.io.CharArrayWriter;
import java.io.PrintWriter;

public class CharResponseWrapper extends HttpServletResponseWrapper {
    private final CharArrayWriter charArrayWriter;
    private final PrintWriter writer;

    public CharResponseWrapper(HttpServletResponse response) {
        super(response);
        this.charArrayWriter = new CharArrayWriter();
        this.writer = new PrintWriter(charArrayWriter);
    }

    @Override
    public PrintWriter getWriter() {
        return writer;
    }

    public String toString() {
        return charArrayWriter.toString();
    }
}
