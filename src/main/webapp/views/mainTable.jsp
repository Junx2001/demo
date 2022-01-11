<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="scheme" value="${pageContext.request.scheme}"/>
<c:set var="serverName" value="${pageContext.request.serverName}"/>
<c:set var="serverPort" value="${pageContext.request.serverPort}"/>
<c:set var="baseURL" value="${scheme}://${serverName}:${serverPort}"/>
<main id="main" class="main">

    <div class="pagetitle">
        <h1>Liste des Signalements</h1>
    </div><!-- End Page Title -->

    <section class="section">
      <div class="row">
        <div class="col-lg-12">
			<div class="card">
                    <div class="card-body">


                        <!-- General Form Elements -->
                        <h5 class="card-title">Filtrer les signalements entre 2 dates</h5>
                        
                        <form action="${baseURL}/signalement/recherche">
                            <div class="row mb-3">
                                <label for="inputDate" class="col-sm-2 col-form-label">Date Debut </label>
                                <div class="col-sm-10">
                                    <input type="date" class="form-control" name="d1">
                                </div>
                            </div>
                            <div class="row mb-3">
                                <label for="inputDate" class="col-sm-2 col-form-label">Date Fin </label>
                                <div class="col-sm-10">
                                    <input type="date" class="form-control" name="d2">
                                </div>
                            </div>
                            <div class="row mb-3">
                                <label class="col-sm-2 col-form-label"></label>
                                <div class="col-sm-10">
                                    <button type="submit" class="btn btn-primary">Filtrer</button>
                                </div>
                            </div>

                        </form><!-- End General Form Elements -->

                    </div>
                </div>
                
                
          <div class="card">
            <div class="card-body">
            
              <div class="scrollme" style="overflow-x: auto;"> 
              <!-- Table with stripped rows -->
              <table class="table datatable" >
                <thead>
                  <tr>
                    <th scope="col">ID</th>
                    <th scope="col">image</th>
                    <th scope="col">date signalement</th>
                    <th scope="col">description</th>
                    <th scope="col">categorie</th>
                    <th scope="col">sous-categorie</th>
                    <th scope="col">auteur</th>
                    <th scope="col">longitude</th>
                    <th scope="col">latitude</th>
                    <th scope="col">region</th>
                  </tr>
                </thead>
                <tbody>
                <c:forEach  items="${signalements}" var ="signalement">
                  <tr>
                    <th scope="row"><a href="${baseURL}/signalement/${signalement.idSignalement}">${signalement.idSignalement}</a></th>
                    <td>${signalement.nomImage}</td>
                    <td>${signalement.dateSignalement}</td>
                    <td>${signalement.description}</td>
                    <td>${signalement.nomCat}</td>
                    <td>${signalement.nomSousCat}</td>
                    <td>${signalement.email}</td>
                    <td>${signalement.longitude}</td>
                    <td>${signalement.latitude}</td>
                    <c:if test="${signalement.region == null}">
                    <td><a href="${baseURL}/signalement/${signalement.idSignalement}">Affecter a une region</a></td>
                    </c:if>
                    <c:if test="${signalement.region != null}">
                    <td>${signalement.region}</td>
                    </c:if>
                    
                  </tr>
                  </c:forEach>
                  
                </tbody>
              </table>
              </div>

            </div>
        </div>
        </div>
        </div>
    </section>

</main><!-- End #main -->