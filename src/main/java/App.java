import java.util.HashMap;
import java.util.Map;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;

public class App {
  public static void main(String[] args) {
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";

    // root route for rendering our home page
      get("/", (request, response) -> {
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("stylists", Stylist.all());
        model.put("template", "templates/index.vtl");
        return new ModelAndView(model, layout);
      }, new VelocityTemplateEngine());

   // route responsible for rendering the template with new-client form
      get("/clients/new", (request, response) -> {
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("template", "templates/client-form.vtl");
        return new ModelAndView(model, layout);
       }, new VelocityTemplateEngine());

   // route to display all clients
     get("/clients", (request, response) -> {
        Map<String, Object> model = new HashMap<String, Object>();
        // model.put("clients", Client.all());
        model.put("template", "templates/clients.vtl");
        return new ModelAndView(model, layout);
       }, new VelocityTemplateEngine());

   // route for gathering information and displaying success template
      post("/clients", (request,response) -> {
        Map<String, Object> model = new HashMap<String, Object>();
        String description = request.queryParams("description");
        Stylist stylist = Stylist.find(Integer.parseInt(request.queryParams("stylistId")));
        Client newClient = new Client(description, stylist.getId());
        model.put("template", "templates/success.vtl");
        return new ModelAndView(model, layout);
      }, new VelocityTemplateEngine());

  }
}
