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
        Stylist stylist = Stylist.find(Integer.parseInt(request.queryParams("stylistId")));
        String description = request.queryParams("description");
        Client newClient = new Client(description, stylist.getId());
        model.put("template", "templates/success.vtl");
        return new ModelAndView(model, layout);
      }, new VelocityTemplateEngine());

  //  adding new stylists
      get("/stylists/new", (request, response) -> {
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("template", "templates/stylist-form.vtl");
        return new ModelAndView(model, layout);
      }, new VelocityTemplateEngine());

      // indicating stylist has already  done the work
      post("/stylists", (request, response) -> {
        Map<String, Object> model = new HashMap<String, Object>();
        String description = request.queryParams("description");
        Stylist newStylist = new Stylist(description);
        newStylist.save();
        model.put("template", "templates/stylist-success.vtl");
        return new ModelAndView(model, layout);
      }, new VelocityTemplateEngine());

      // displaying stylists
      get("/stylists", (request, response) -> {
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("stylists", Stylist.all());
        model.put("template", "templates/stylists.vtl");
        return new ModelAndView(model, layout);
      }, new VelocityTemplateEngine());

      // routing and a basic template setup
      get("/stylists/:id", (request, response) -> {
        Map<String, Object> model = new HashMap<String, Object>();
        Stylist stylist = Stylist.find(Integer.parseInt(request.params(":id")));
        model.put("stylist", stylist);
        model.put("template", "templates/stylist.vtl");
        return new ModelAndView(model, layout);
      }, new VelocityTemplateEngine());

      // relating stylist to the clients
      get("stylists/:id/clients/new", (request, response) -> {
        Map<String, Object> model = new HashMap<String, Object>();
        Stylist stylist = Stylist.find(Integer.parseInt(request.params(":id")));
        model.put("stylist", stylist);
        model.put("template", "templates/stylist-clients-form.vtl");
        return new ModelAndView(model, layout);
      }, new VelocityTemplateEngine());

      // indicating stylist has already  done the work
      post("/stylists", (request, response) -> {
        Map<String, Object> model = new HashMap<String, Object>();
        String description = request.queryParams("description");
        Stylist newStylist = new Stylist(description);
        newStylist.save();
        model.put("template", "templates/stylist-success.vtl");
        return new ModelAndView(model, layout);
      }, new VelocityTemplateEngine());

  }
}
