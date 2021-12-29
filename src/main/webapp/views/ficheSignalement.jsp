<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="scheme" value="${pageContext.request.scheme}"/>
<c:set var="serverName" value="${pageContext.request.serverName}"/>
<c:set var="serverPort" value="${pageContext.request.serverPort}"/>
<c:set var="baseURL" value="${scheme}://${serverName}:${serverPort}"/>

<main id="main" class="main">
    <div class="pagetitle">
      <h1>Affectation d'un signalement à une région</h1>
    </div><!-- End Page Title -->
    
		<div>

          <!-- Card with an image on top -->
          <div class="card">
	          <form>
	          	<img src="${baseURL}/views/assets/img/card.jpg" width="50" class="card-img-top" alt="...">
	            <div class="card-body">
	              <h5 class="card-title">Signalement ${signalement[0][0]} </h5>
	              <p class="card-text">Catégorie: ${signalement[0][11]}</p>
	              <p class="card-text">Sous-catégorie: ${signalement[0][10]}</p>
	              <p class="card-text">Description: ${signalement[0][2]}</p>
	              <p class="card-text">Date: ${signalement[0][1]}</p>
	              <hr>
	              <p class="card-text">Longitude: ${signalement[0][7]}</p>
	              <p class="card-text">Latitude: ${signalement[0][6]}</p>
	              <hr>
	              <p class="card-text">Auteur: ${signalement[0][5]}</p>
	              <p class="card-text">Email: ${signalement[0][12]}</p>
	              <hr>
	              <select name="idRegion">
	              <c:forEach  items="${regions}" var ="region">
	              	<option value="${region.idRegion}">${region.nom}</option>
	              </c:forEach>
	              </select>
	              <button type="submit">Affecter</button>
              	</div>
	          </form>
            
           
          </div><!-- End Card with an image on top -->


        </div>
      </main>