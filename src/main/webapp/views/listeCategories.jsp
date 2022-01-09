 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 <c:set var="scheme" value="${pageContext.request.scheme}"/>
<c:set var="serverName" value="${pageContext.request.serverName}"/>
<c:set var="serverPort" value="${pageContext.request.serverPort}"/>
<c:set var="baseURL" value="${scheme}://${serverName}:${serverPort}"/>

<main id="main" class="main">
<input type="hidden" id="url" value="${baseURL}">



    <div class="pagetitle">
      <h1>Liste des categories et sous-categories</h1>
    </div><!-- End Page Title -->
    
		<div id="container"></div>
		<c:if test="${exception != null}">
	        <div class="alert alert-danger" role="alert">
				  ${exception}
			</div>
		</c:if>
		<div id="exception"></div>
		<div class="card-body">
              <h5 class="card-title">Ajouter une categorie</h5>
	              <form class="row g-3" action="${baseURL}/categorie" method="post">
	                <div class="col-md-6">
	                  <input type="text" class="form-control" placeholder="Designation" name="label">
	                </div>
	                <div class="col-md-6">
	                  <button type="submit" class="btn btn-primary">Ajouter</button>
	                </div>
	              </form>
	              <c:if test="${insertion != null}">
	              <div class="alert alert-success" role="alert">
					  ${insertion}
					</div>
				</c:if>
	         </div>
	         
	         <div class="card-body">
              <h5 class="card-title">Ajouter un sous-categorie</h5>

              <!-- No Labels Form -->
              <form class="row g-3" action="${baseURL}/sousCategorie" method="post">
                <div class="col-md-6">
                  <input type="text" class="form-control" placeholder="Designation" name="label">
                </div>
                <div class="col-md-4">
                  <select id="inputState" class="form-select" name="idCategorie">
                    <option selected="">Choisir...</option>
                  	<c:forEach  items="${categories}" var ="categorie" >
                    <option value="${categorie.idCategorie}" >${categorie.label}</option>
                    </c:forEach>
                  </select>
                </div>
                <div class="col-md-2">
                  <button type="submit" class="btn btn-primary">Ajouter</button>
                </div>
              </form><!-- End No Labels Form -->
				<c:if test="${insertionSousCat != null}">
	              <div class="alert alert-success" role="alert">
					  ${insertionSousCat}
					</div>
				</c:if>
            </div>
		
	 <script>
	 
		var root = new TreeNode("Categories");
		<c:forEach  items="${categories}" var ="categorie" varStatus="loop1">
		
		<c:if test="${sousCategories[loop1.index].size() ==0 }">
		 	n1 = new TreeNode("${categorie.label} <button onclick=\"supprimerCategorie('${categories[loop1.index].idCategorie}')\" class=\"btn-danger\" title=\"supprimer sous-categorie\" name=\"idSousCategorie\" ><i class=\"bi bi-trash\"></i></button>");
		</c:if>
		<c:if test="${sousCategories[loop1.index].size() > 0 }">
			n1 = new TreeNode("${categorie.label}");
		</c:if>
			 
				<c:forEach  items="${sousCategories[loop1.index]}" var ="sousCategorie" varStatus="loop">
				 n11 = new TreeNode(" ${sousCategorie.label} <button onclick=\"supprimer('${sousCategories[loop1.index][loop.index].idSousCategorie}')\" class=\"btn-danger\" title=\"supprimer sous-categorie\" name=\"idSousCategorie\" ><i class=\"bi bi-trash\"></i></button>");
				 n1.addChild(n11);
				</c:forEach>
				
			root.addChild(n1);
		</c:forEach>
			
		var view = new TreeView(root, "#container");
	</script>
	<script>
		function supprimerCategorie(idCat){
			var baseUrl = $('#url').val();
			setTimeout(function(){// wait for 5 secs(2)
                location.reload(); // then reload the page.(3)
           }, 100); 
			console.log(idCat);
			$.ajax({
	            url: baseUrl + '/categorie/' + idCat,
	            method: 'delete',
	            dataType: 'json',
	            success: function (response) {
	                console.log(response.includes('existe'));
	                if(data.success == true){ // if true (1)
	                    setTimeout(function(){// wait for 5 secs(2)
	                         location.reload(); // then reload the page.(3)
	                    }, 100); 
	                 }
	            }
	        });
		}
		
		function supprimer(idSousCat){
			var baseUrl = $('#url').val();
			setTimeout(function(){// wait for 5 secs(2)
                location.reload(); // then reload the page.(3)
           }, 100); 
			console.log(idSousCat);
			$.ajax({
	            url: baseUrl + '/sousCategorie/' + idSousCat,
	            method: 'delete',
	            dataType: 'json',
	            success: function (response) {
	                console.log(response);
	                if(data.success == true){ // if true (1)
	                    setTimeout(function(){// wait for 5 secs(2)
	                         location.reload(); // then reload the page.(3)
	                    }, 100); 
	                 }
	            }
	        });
		}
	</script>
	
</main>

	
    