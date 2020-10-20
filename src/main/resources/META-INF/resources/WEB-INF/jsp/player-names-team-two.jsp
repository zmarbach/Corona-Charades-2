<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<link href="https://unpkg.com/bootstrap@4.3.1/dist/css/bootstrap.min.css" rel="stylesheet" />
  <body>
    <div class="container mt-3">
        <div class="card">
              <h3 class="card-header">Team Two Player Names</h3>
              <div class="card-body">
                <div class="card-text">
                  <form:form action="/player-names-team-two" method="POST" modelAttribute="playerForm" autocomplete="off">
                        <c:forEach items="${playerForm.players}" var="player" varStatus="status">
                              <div class="form-group">
                                <input class="form-control" name="players[${status.index}].name" placeholder="${player.name}" autofocus="autofocus"/>
                              </div>
                          </c:forEach>
                          <div class="form-group">
                              <input type="hidden" class="form-control" id="gameUUID" name="gameUUID" value="${playerForm.gameUUID}" />
                          <div>
                        <input class="btn btn-outline-primary" type="submit" value="Save Names!" />
                  </form:form>
                </div>
              </div>
          </div>
      </div>
  </body>
</html>
