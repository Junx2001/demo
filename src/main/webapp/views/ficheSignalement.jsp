<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

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
	              <h5 class="card-title">Signalement ${signalement.idSignalement} </h5>
	              <p class="card-text">Catégorie: ${signalement.nomCat}</p>
	              <p class="card-text">Sous-catégorie: ${signalement.nomSousCat}</p>
	              <p class="card-text">Description: ${signalement.description}</p>
	              <p class="card-text">Date: ${signalement.dateSignalement}</p>
	              <hr>
	              <p class="card-text">Longitude: ${signalement.longitude}</p>
	              <p class="card-text">Latitude: ${signalement.latitude}</p>
	              <hr>
	              <p class="card-text">Email auteur: ${signalement.email}</p>
	              <hr>
	              <select name="idRegion">
	              <c:forEach  items="${regions}" var ="region">
	              	<option value="${region.idRegion}">${region.nom}</option>
	              </c:forEach>
	              </select>
	              <p><button type="submit">Affecter</button></p>
              	</div>
	          </form>
            
           
          </div><!-- End Card with an image on top -->


        </div>
      </main>