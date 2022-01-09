<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<main id="main" class="main">
    <div class="pagetitle">
        <h1>Affectation d'un signalement à une région</h1>
    </div><!-- End Page Title -->

    <div>

        <!-- Card with an image on top -->
        <div class="card">

            <img src="${baseURL}/views/assets/img/card.jpg" width="50" class="card-img-top" alt="...">
            <input type="hidden" id="url" value="${baseURL}">
            <div class="card-body">
                <h5 class="card-title">Signalement <span id="idSignalement">${signalement.idSignalement}</span></h5>
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
                
             
                <c:if test="${signalement.region != null}">
                    <p class="card-text">Region : ${signalement.region}</p>
                </c:if>
				<c:if test="${signalement.region == null}">
					<p> 
					<div id="spinner" class="spinner-border" role="status" style="display:none;">
	                <span class="visually-hidden">Loading...</span>
	              	</div>
	              </p>
                    <select name="idRegion" id="idRegion">
                        <c:forEach  items="${regions}" var ="region">
                            <option value="${region.idRegion}">${region.nom}</option>
                        </c:forEach>
                    </select>
                    <button id="bouton">Affecter</button>
                </c:if>
                
            </div>


        </div><!-- End Card with an image on top -->


    </div>
</main>

<script>
    $('#bouton').click(function () {
    	var spinner=$('#spinner');

    	  spinner.show();
    	  
        var baseUrl = $('#url').val();
        var sign = $('#idSignalement').html();
        var region = $('#idRegion').val();
        setTimeout(function(){
            location.reload(); 
            spinner.hide();
       }, 2000); 
        
        $.ajax({
            url: baseUrl + '/signalement/' + sign,
            method: 'put',
            data: {region: region},
            dataType: 'json',
            success: function (response) {
            	  
                console.log("put method successfully done");
                console.log(response);
            }
        });
    });
</script>
    
      </main>
