<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<link href="https://unpkg.com/bootstrap@4.3.1/dist/css/bootstrap.min.css" rel="stylesheet" />
  <body>
      <div class="container mt-3">
        <div class="row">
              <div class="col-4">
                  <div class="card text-white text-center bg-dark m-3" style="height: 10rem; width: 12rem;">
                    <h5 class="card-header">Current Player</h5>
                    <div class="card-body">
                        <div class="card-text" style="font-size:25px;"> ${gamePlayViewForm.currentPlayer.name} </div>
                        <div class="card-text" style="font-size:15px;"> ${gamePlayViewForm.currentPlayer.team.name} </div>
                    </div>
                  </div>
              </div>
              <div class="col-4">
                  <div class="card text-white text-center bg-primary m-3" style="height: 10rem; width: 12rem;">
                      <h5 class="card-header">Team One</h5>
                      <div class="card-body">
                          <div class="card-text" style="font-size:50px;"> ${gamePlayViewForm.teamOneScore} </div>
                      </div>
                  </div>
              </div>
              <div class="col-4">
                  <div class="card text-white text-center bg-danger m-3" style="height: 10rem; width: 12rem;">
                    <h5 class="card-header">Team Two</h5>
                    <div class="card-body">
                        <div class="card-text" style="font-size:50px;"> ${gamePlayViewForm.teamTwoScore} </div>
                    </div>
                  </div>
              </div>
        </div>

        <form:form action="/start-turn" method="POST">
            <input type="hidden" name="gameUUID" value="${gamePlayViewForm.gameUUID}" />

            <c:if test="${gamePlayViewForm.newTurn}">
                <input id="startTurnButton" class="btn btn-lg btn-outline-primary m-3" type="submit" ${empty gamePlayViewForm.activeWords ? 'disabled="disabled"' : ''} value="Start turn" />
            </c:if>

        </form:form>

          <div class="card text-center border-dark m-3" style="max-width: 40rem;">
              <div class="card-body">
                  <c:if test="${!gamePlayViewForm.newTurn}">
                    <c:if test= "${not empty gamePlayViewForm.activeWords}">
                    <div style="font-size:72px; font-weight: bold;"> ${gamePlayViewForm.currentWord}</div>
                    </c:if>
                  </c:if>
              </div>
          </div>

          <c:if test="${empty gamePlayViewForm.activeWords}">
                <h6 style="color: red; font-style: italic;">All words have been guessed. Click next round to continue.</h6>
            </c:if>

          <div class="row">
              <div class="col-6">
                  <form action="/correct" method="post">
                  <input type="hidden" name="gameUUID" value="${gamePlayViewForm.gameUUID}" />
                        <c:if test="${!gamePlayViewForm.newTurn}">
                            <input class="btn btn-lg btn-success m-3" type="submit" ${empty gamePlayViewForm.activeWords ? 'disabled="disabled"' : ''} value="Correct!" />
                        </c:if>
                  </form>
              </div>
              <div class="col-6">
                  <form action="/skip" method="post">
                  <input type="hidden" name="gameUUID" value="${gamePlayViewForm.gameUUID}" />
                        <c:if test="${!gamePlayViewForm.newTurn}">
                            <input class="btn btn-lg btn-danger m-3" type="submit" ${empty gamePlayViewForm.activeWords ? 'disabled="disabled"' : ''} value="Skip" />
                        </c:if>
                  </form>
              </div>
          </div>

          <form action="/next-player" method="post">
            <input type="hidden" name="gameUUID" value="${gamePlayViewForm.gameUUID}" />
            <c:if test="${!gamePlayViewForm.newTurn}" >
              <input id="nextPlayerButton" class="btn btn-lg btn-outline-secondary m-3" type="submit" ${empty gamePlayViewForm.activeWords ? 'disabled="disabled"' : ''} value="Next Player" />
            </c:if>
          </form>
          <form action="/next-round" method="post">
            <input type="hidden" name="gameUUID" value="${gamePlayViewForm.gameUUID}" />
            <c:if test="${empty gamePlayViewForm.activeWords}" >
                <input id="nextRoundButton" class="btn btn-lg btn-outline-secondary m-3" type="submit" value="Next Round" />
            </c:if>
          </form>

          <form action="/end-game" method="post">
            <input type="hidden" name="gameUUID" value="${gamePlayViewForm.gameUUID}" />
            <input class="btn btn-lg btn-outline-danger m-3" type="submit" value="End Game" />
          </form>
      </div>
  </body>
</html>
