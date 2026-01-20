package se.jimmy.iths;

public class Main {
    public static void main(String[] args) throws Exception {
//starta webbservern
        var server = new org.eclipse.jetty.server.Server(8080);
//i denna context kan man registrera servlets
        var context = new org.eclipse.jetty.servlet.ServletContextHandler();

        server.setHandler(context);

        context.addServlet(RootServlet.class, "/");
//starta servern

//starta servern
        context.addServlet(HelloServlet.class, "/fest321");

        context.addServlet(HelloServlet.class, "/hello/*");//lägg till /*


        server.start();
        server.join();


    }
}
