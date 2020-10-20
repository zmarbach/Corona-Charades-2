<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
 <html>
     <link href="https://unpkg.com/bootstrap@4.3.1/dist/css/bootstrap.min.css" rel="stylesheet" />
       <body>
         <div class="container mt-3">
           <div class="card text-center border-dark m-3">
               <img class="card-img-top" src="/resources/corona.jpg" alt="Corona">
               <div class="card-body">
                  <div style="font-size:72px; font-weight: bold;"> COVID-19 Charades! </div>
               </div>

               <div>
                  <form:form action="/home" method="POST">
                      <input class="btn btn-lg btn-outline-primary m-3" type="submit" value="New Game!" />
                  </form:form>
               <div>
           </div>
  </body>
</html>
