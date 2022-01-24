<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="scheme" value="${pageContext.request.scheme}"/>
<c:set var="serverName" value="${pageContext.request.serverName}"/>
<c:set var="serverPort" value="${pageContext.request.serverPort}"/>
<c:set var="baseURL" value="${scheme}://${serverName}:${serverPort}"/>
<main id="main" class="main">

    <div class="pagetitle">
        <h1>Liste des Utilisateurs du Gouvernement (Front-Office)</h1>
        <nav>
            <ol class="breadcrumb">
               <li class="breadcrumb-item"><a href="${baseURL}/back/signalements">Liste des signalements</a></li>
             </ol>
         </nav>
    </div><!-- End Page Title -->

    <section class="section">
      <div class="row">
        <div class="col-lg-12">

          <div class="card">
            <div class="card-body">
              
              <div class="scrollme" style="overflow: auto;"> 
              <!-- Table with stripped rows -->
              <table class="table datatable" >
                <thead>
                  <tr>
                    <th scope="col">ID</th>
                    <th scope="col">Email</th>
                    <th scope="col">Region</th>
                  </tr>
                </thead>
                <tbody>
                <c:forEach  items="${utilisateurs}" var ="utilisateur">
                  <tr>
                    <td>${utilisateur.idUtilisateur}</td>
                    <td>${utilisateur.email}</td>
                    <td>${utilisateur.nomRegion}</td>
                    <td><a class="btn btn-warning" href="${baseURL}/back/utilisateurs/formulaireUpdate?idUtil=${utilisateur.idUtilisateur}">Modifier <i class="ri-edit-2-line"></i></a></td>
                    <td><button class="btn btn-danger" onclick="deleteUtil('${utilisateur.idUtilisateur}')">Supprimer <i class="ri-delete-bin-5-line"></i></button></td>
                  </tr>
                  </c:forEach>
                  
                </tbody>
              </table>
              </div>
                </div>
        </div>
              <!-- End Table with stripped rows -->

              
        </div>
        </div>
    </section>
    <div class="container">
  <div class="row">
    <div class="col">
      <a class="btn btn-success" type="button" href="${baseURL}/back/utilisateurs/formulaireInsert">Ajouter un nouvel utilisateur <i class="ri-user-add-fill"></i></a>
    </div>
    <div class="col">
      
    </div>
    <div class="col">
      
    </div>
  </div>
</div>
    <script>
    	function deleteUtil(idUtil){
    		console.log(idUtil);
    		console.log("${baseURL}");
    		setTimeout(function(){
                location.reload(); 
           }, 1000); 
    		$.ajax({
	            url: '${baseURL}/back/utilisateurs/' + idUtil,
	            method: 'delete',
	            dataType: 'json',
	            success: function (response) {
	                console.log(response);
	                
	            }
	        });
    	}
    </script>

</main><!-- End #main -->