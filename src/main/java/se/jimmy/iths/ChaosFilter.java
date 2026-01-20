package se.jimmy.iths;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Random;

public class ChaosFilter implements Filter {

    private final Random random = new Random();

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse httpRes = (HttpServletResponse) response;
        int chance = random.nextInt(10); // 0-9

        System.out.println("Chaos Filter rullar tärning: " + chance);

        switch (chance) {
            case 0: // 10% chans: ROTATE
                System.out.println("MODE: Australian Internet");
                // Injicera CSS först i strömmen
                response.getWriter().println("<style>body { transform: rotate(180deg); transition: transform 2s; }</style>");
                chain.doFilter(request, response);
                break;

            case 1: // 10% chans: SLOW TEAPOT (Segt + Teapot)
                System.out.println("MODE: Slow Teapot");
                try {
                    // Vänta 5 sekunder för maximal irritation
                    Thread.sleep(5000); 
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                
                httpRes.setStatus(418);
                response.setContentType("text/plain");
                response.getWriter().println("418 I'm a teapot (Wait... what took so long?)");
                break;

            case 2: // 10% chans: SCREAM MODE (Versaler + !!!)
                System.out.println("MODE: SCREAMING");
                CharResponseWrapper wrapper = new CharResponseWrapper(httpRes);
                
                // Låt servleten göra sitt jobb mot vår wrapper
                chain.doFilter(request, wrapper);
                
                // Hämta resultatet, gör om till versaler och skriv till riktiga svaret
                String originalContent = wrapper.toString();
                String upperCaseContent = originalContent.toUpperCase().replace("</body>", "<h1>!!!</h1></body>");
                
                response.setContentLength(upperCaseContent.length());
                response.getWriter().write(upperCaseContent);
                break;

            case 3: // 10% chans: ALERT OVERLOAD
                System.out.println("MODE: Popups");
                response.getWriter().println("<script>");
                response.getWriter().println("for(let i=0; i<10; i++) { alert('CHAOS ALERT #' + (i+1) + '\\nDU KAN INTE FLY FRÅN SERVLET-LABBEN!'); }");
                response.getWriter().println("</script>");
                chain.doFilter(request, response);
                break;

            case 4: // 10% chans: FAKE SYSTEM PURGE (Virus-like)
                System.out.println("MODE: FAKE VIRUS");
                response.getWriter().println("<div id='term' style='position:fixed;top:0;left:0;width:100%;height:100%;background:black;color:#00ff00;font-family:monospace;font-size:18px;padding:20px;z-index:9999;'></div>");
                response.getWriter().println("<script>");
                response.getWriter().println("const logs = [");
                response.getWriter().println("  'CRITICAL ERROR: KERNEL PANIC',");
                response.getWriter().println("  'Initiating system recovery...',");
                response.getWriter().println("  'Recovery failed.',");
                response.getWriter().println("  'Format C: drive initialized...',");
                response.getWriter().println("  'Deleting System32...',");
                response.getWriter().println("  'Deleting User/Documents...',");
                response.getWriter().println("  'Uploading browser history to public server...',");
                response.getWriter().println("  'SYSTEM DESTROYED. HAVE A NICE DAY.'");
                response.getWriter().println("];");
                response.getWriter().println("let line = 0;");
                response.getWriter().println("const term = document.getElementById('term');");
                response.getWriter().println("setInterval(() => {");
                response.getWriter().println("  if(line < logs.length) {");
                response.getWriter().println("    term.innerHTML += logs[line] + '<br>';");
                response.getWriter().println("    line++;");
                response.getWriter().println("  }");
                response.getWriter().println("}, 1500);"); // Ny rad var 1.5 sekund
                response.getWriter().println("</script>");
                // Vi struntar i chain.doFilter, användaren "förlorar" sidan
                break;

            default: // 50% chans: Normalt beteende
                chain.doFilter(request, response);
                break;
        }
    }
}
