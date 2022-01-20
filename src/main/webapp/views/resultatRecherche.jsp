<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<main id="main" class="main">

    <div class="pagetitle">
      <h1>Liste des Signalements</h1>
    </div><!-- End Page Title -->

    <section class="section">
      <div class="row">
        <div class="col-lg-12">

          <div class="card">
            <div class="card-body">
              
             <h5 class="card-title">Tous les signalements entre les dates ${param.d1} et ${param.d2}</h5>
              <!-- Table with stripped rows -->
              <div class="scrollme" style="overflow-x: auto;"> 
              <table class="table datatable">
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
                <c:forEach  items="${listeRecherche}" var ="signalement" varStatus="i">
                  <tr>
                    <th scope="row"><a href="${baseURL}/back/signalement/${signalement.idSignalement}">
                    <img src="${baseURL}/views/assets/img/imgCloud/${signalement.nomImage}" width="50"  alt="${signalement.description}">
                    </a></th>
                    <td>${signalement.nomImage}</td>
                    <td>${signalement.dateSignalement}</td>
                    <td>${signalement.description}</td>
                    <c:if test="${signalement.region == null}">
                    <td><a href="${baseURL}/back/signalement/${signalement.idSignalement}">Affecter a une region</a></td>
                    </c:if>
                    <c:if test="${signalement.region != null}">
                    <td>${signalement.region}</td>
                    </c:if>
                    
                  </tr>
                  </c:forEach>
                  
                </tbody>
              </table>
              </div>
              <!-- End Table with stripped rows -->

            </div>
          </div>

        </div>
      </div>
    </section>

</main><!-- End #main -->