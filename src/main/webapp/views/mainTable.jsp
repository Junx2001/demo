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
              
              <!-- Table with stripped rows -->
              <table class="table datatable">
                <thead>
                  <tr>
                    <th scope="col">ID</th>
                    <th scope="col">image</th>
                    <th scope="col">date signalement</th>
                    <th scope="col">description</th>
                    <th scope="col">Sous-categorie</th>
                    <th scope="col">auteur</th>
                    <th scope="col">longitude</th>
                    <th scope="col">latitude</th>
                    <th scope="col"></th>
                  </tr>
                </thead>
                <tbody>
                <c:forEach  items="${signalements}" var ="signalement">
                  <tr>
                    <th scope="row"><a href="${baseURL}/signalement/${signalement.idSignalement}">${signalement.idSignalement}</a></th>
                    <td>${signalement.nomImage}</td>
                    <td>${signalement.dateSignalement}</td>
                    <td>${signalement.description}</td>
                    <td>${signalement.idSousCategorie}</td>
                    <td>${signalement.idUtilisateur}</td>
                    <td>${signalement.longitude}</td>
                    <td>${signalement.latitude}</td>
                    <c:if test="${signalement.region == null}">
                    <td><a href="${baseURL}/signalement/${signalement.idSignalement}">Affecter à une région</a></td>
                    </c:if>
                    
                  </tr>
                  </c:forEach>
                  
                </tbody>
              </table>
              <!-- End Table with stripped rows -->

            </div>
          </div>

        </div>
      </div>
    </section>

</main><!-- End #main -->