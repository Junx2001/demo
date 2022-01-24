<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="scheme" value="${pageContext.request.scheme}"/>
<c:set var="serverName" value="${pageContext.request.serverName}"/>
<c:set var="serverPort" value="${pageContext.request.serverPort}"/>
<c:set var="baseURL" value="${scheme}://${serverName}:${serverPort}"/>
<main id="main" class="main">

	
	
    <div class="pagetitle">
        <h1>Liste des Signalements </h1>
    </div><!-- End Page Title -->

    <section class="section">
      <div class="row">
        <div class="col-lg-12">
        
         	<div class="card">
                 <div class="card-body">
						<h5 class="card-title">Voir la carte des signalements non encore affectés à une région <i class="ri-map-line"></i></h5>
						<a class="btn btn-primary" href="${baseURL}/back/signalements/carte">Carte <i class="bi bi-geo-alt"></i></a>
                  </div>
             </div>
                
			<div class="card">
                    <div class="card-body">
                        <!-- General Form Elements -->
                        <h5 class="card-title">Filtrer les signalements entre 2 dates <i class="ri-calendar-event-fill"></i></h5>
                        
                        <form action="${baseURL}/back/signalements/recherche">
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
                                <div class="col-sm-10">
                                    <button type="submit" class="btn btn-success">Filtrer <i class="ri-filter-2-fill"></i></button>
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
                    <th scope="col">#</th>
                    <th scope="col">image</th>
                    <th scope="col">date signalement</th>
                    <th scope="col">description</th>
                    <th scope="col">region</th>
                  </tr>
                </thead>
                <tbody>
                <c:forEach  items="${signalements}" var ="signalement" varStatus="i">
                  <tr>
                    <th scope="row"><a href="${baseURL}/back/signalements/${signalement.idSignalement}">${i.index}</a></th>
                    <td>
	                    <a href="${baseURL}/back/signalements/${signalement.idSignalement}">
	                   	 <img src="${baseURL}/views/assets/img/imgCloud/${signalement.nomImage}" width="50"  alt="${signalement.description}">
	                    </a>
                    </td>
                    <td>${signalement.dateSignalement}</td>
                    <td>${signalement.description}</td>
                    <c:if test="${signalement.region == null}">
                    <td><a href="${baseURL}/back/signalements/${signalement.idSignalement}">Affecter a une region</a></td>
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