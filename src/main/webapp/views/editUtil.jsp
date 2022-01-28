<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="scheme" value="${pageContext.request.scheme}"/>
<c:set var="serverName" value="${pageContext.request.serverName}"/>
<c:set var="serverPort" value="${pageContext.request.serverPort}"/>
<c:set var="baseURL" value="${scheme}://${serverName}:${serverPort}"/>
<main id="main" class="main">

    <div class="pagetitle">
        <h1>Modifier Utilisateur</h1>
        <nav>
            <ol class="breadcrumb">
               <li class="breadcrumb-item"><a href="${baseURL}/back/utilisateurs">Liste utilisateurs</a></li>
               <li class="breadcrumb-item active">Modification</li>
             </ol>
         </nav>
    </div><!-- End Page Title -->

    <section class="section">
        <div class="row">
            <div class="col-lg-12">


                <div class="card">
                    <div class="card-body">


                        <!-- General Form Elements -->
                        <h5 class="card-title">Entrer les informations à modifier</h5>
 
                        <div  oninput='up2.setCustomValidity(up2.value != mdp.value ? "Passwords do not match." : "")' >
                            <div class="row mb-3">
                                <label for="email" class="col-sm-2 col-form-label">Nouvel Email </label>
                                <div class="col-sm-10">
                                    <input type="email" class="form-control" name="email" id="email" placeholder="${util.email}">
                                </div>
                            </div>
                            <div class="row mb-3">
                                <label for="mdp" class="col-sm-2 col-form-label">Nouveau Mot de Passe</label>
                                <div class="col-sm-10">
                                    <input type="password" class="form-control" id="mdp" name="mdp">
                                </div>
                            </div>
                            <div class="row mb-3">
                                <label for="mdp2" class="col-sm-2 col-form-label">Retaper votre Mot de Passe</label>
                                <div class="col-sm-10">
                                    <input type="password" class="form-control" id="up2" name="up2">
                                </div>
                            </div>
                             <div class="row mb-3">
                                <label for="mdp2" class="col-sm-2 col-form-label">Sa région</label>
                                <div class="col-sm-10">
                                   <select name="region" id="region" class="form-select">
                                   	<option value="">choisir...</option>
				                        <c:forEach  items="${regions}" var ="region" varStatus="loop">
				                            <option value="${regions[loop.index].idRegion}">${region.nom}</option>
				                        </c:forEach>
				                    </select>
                                </div>
                            </div>
                            <div class="row mb-3">
                                <label class="col-sm-2 col-form-label"></label>
                                <div class="col-sm-10">
                                    <button type="submit" id="bouton" class="btn btn-success">Valider</button>
                                </div>
                            </div>
                            
							<div class="alert alert-success" id="succes" role="alert" style="display:none;">
							
							</div>
							
							<div class="alert alert-danger" id="erreur" role="alert" style="display:none;">
							
							</div>
							
                        </div>
 
                    </div>
                </div>

            </div>
        </div>
    </section>
		
	<script>
		$('#bouton').click(function () {
			$('#succes').empty();
			$('#erreur').empty();
			
			var email = $('#email').val();
			var mdp = $('#mdp').val();
			var idRegion = $('#region').val();
			var idUtil = "${utilisateur.idUtilisateur}";
			
     	 
			$.ajax({
	            url: '${baseURL}/back/utilisateurs/'+idUtil,
	            method: 'put',
	            data: {email: email, mdp:mdp, idRegion:idRegion},
	            success: function (response) {
	            	console.log(response);
	            	if (response.includes("informations")){
	            		$('#succes').css("display","block");
	            		$('#succes').append(response);
	            	}else{
	            		$('#erreur').css("display","block");
	            		$('#erreur').append(response);
	            	}
	            	
	              
	            }
	        });
		})
	</script>

</main><!-- End #main -->