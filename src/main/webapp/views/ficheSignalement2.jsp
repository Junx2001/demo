<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<main id="main" class="main">

    <div class="pagetitle">
        <h1>Fiche du signalement</h1>
        <nav>
            <ol class="breadcrumb">
               <li class="breadcrumb-item"><a href="${baseURL}/back/signalement">Liste</a></li>
               <li class="breadcrumb-item active">Fiche</li>
             </ol>
         </nav>
    </div>

    <section class="section profile">
      <div class="row">
        <div class="col-xl-4">
			<img src="${baseURL}/views/assets/img/imgCloud/${signalement.nomImage}" width="100%" alt="${signalement.description}">
        </div>

        <div class="col-xl-8">

          <div class="card">
            <div class="card-body pt-3">
              <!-- Bordered Tabs -->
              <ul class="nav nav-tabs nav-tabs-bordered">
                <li class="nav-item">
                  <button class="nav-link active" data-bs-toggle="tab" data-bs-target="#profile-overview">Fiche</button>
                </li>
              </ul>
              <div class="tab-content pt-2">

                <div class="tab-pane fade profile-overview active show" id="profile-overview">
                  <h5 class="card-title">Avant propos</h5>
                  <p class="small fst-italic">Ci-jointe est la fiche du signalement ID ${signalement.idSignalement} 
                  dans sa région ou éventuellement, veuillez affecter ce signalement à une région.
                  </p>
                  
                  <h5 class="card-title">Details</h5>

                  <div class="row">
                    <div class="col-lg-3 col-md-4 label ">ID</div>
                    <div class="col-lg-9 col-md-8"><span id="idSignalement">${signalement.idSignalement}</span> </div>
                  </div>
                  
                  <div class="row">
                    <div class="col-lg-3 col-md-4 label">Catégorie</div>
                    <div class="col-lg-9 col-md-8">${signalement.nomCat}</div>
                  </div>

                  <div class="row">
                    <div class="col-lg-3 col-md-4 label">Sous-catégorie</div>
                    <div class="col-lg-9 col-md-8">${signalement.nomSousCat}</div>
                  </div>

                  <div class="row">
                    <div class="col-lg-3 col-md-4 label">Description</div>
                    <div class="col-lg-9 col-md-8">${signalement.description}</div>
                  </div>

                  <div class="row">
                    <div class="col-lg-3 col-md-4 label">Date et heure</div>
                    <div class="col-lg-9 col-md-8">${signalement.dateHeureSignalement}</div>
                  </div>

                  <div class="row">
                    <div class="col-lg-3 col-md-4 label">Longitude</div>
                    <div class="col-lg-9 col-md-8"> ${signalement.longitude}</div>
                  </div>
					
				<div class="row">
                    <div class="col-lg-3 col-md-4 label">Latitude</div>
                    <div class="col-lg-9 col-md-8"> ${signalement.latitude}</div>
                  </div>
                  
                  <div class="row">
                    <div class="col-lg-3 col-md-4 label">Email</div>
                    <div class="col-lg-9 col-md-8"> ${signalement.email}</div>
                  </div>
                  
                 <c:if test="${signalement.region != null}">
                  <div class="row">
                    <div class="col-lg-3 col-md-4 label">Region</div>
                    <div class="col-lg-9 col-md-8"> ${signalement.region}</div>
                  </div>
                </c:if>
				<c:if test="${signalement.region == null}">
					<p> 
					<div id="spinner" class="spinner-border" role="status" style="display:none;">
	                <span class="visually-hidden">Chargement...</span>
	              	</div>
	              </p>
	              <div class="row">
                      <div class="col-lg-3 col-md-4 label">Region</div>
                      <div class="col-lg-7 col-md-6">
                        <select name="idRegion" id="idRegion" class="form-select">
	                        <c:forEach  items="${regions}" var ="region">
	                            <option value="${region.idRegion}">${region.nom}</option>
	                        </c:forEach>
	                    </select>
                      </div>
                      <div class="col-lg-2 col-md-2">
                      	<button  type="button" class="btn btn-info" id="bouton"><i class="bi bi-check-circle"></i></button>
                      </div>
                    </div>
                    <input type="hidden" id="url" value="${baseURL}">
                    
                </c:if>

                </div>

                </div>

              </div>

            </div>
          </div>

        </div>
    </section>

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
            url: baseUrl + '/back/signalement/' + sign,
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