<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<main id="main" class="main">

    <div class="pagetitle">
      <h1>Liste de Signalements</h1>
      <nav>
        <ol class="breadcrumb">
          <li class="breadcrumb-item"><a href="index.html">Home</a></li>
          <li class="breadcrumb-item">Tables</li>
          <li class="breadcrumb-item active">Data</li>
        </ol>
      </nav>
    </div><!-- End Page Title -->

    <section class="section">
      <div class="row">
        <div class="col-lg-12">

          <div class="card">
            <div class="card-body">
              <h5 class="card-title">Datatables</h5>
              <p>Add lightweight datatables to your project with using the <a href="https://github.com/fiduswriter/Simple-DataTables" target="_blank">Simple DataTables</a> library. Just add <code>.datatable</code> class name to any table you wish to conver to a datatable</p>

              <!-- Table with stripped rows -->
              <table class="table datatable">
                <thead>
                  <tr>
                    <th scope="col">#</th>
                    <th scope="col">image</th>
                    <th scope="col">date signalement</th>
                    <th scope="col">description</th>
                    <th scope="col">Categorie</th>
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
                    <th scope="row">1</th>
                    <td>Designer</td>
                    <td>${signalement.dateSignalement}</td>
                    <td>${signalement.description}</td>
                    <td>${signalement.longitude}</td>
                    <td>${signalement.idSousCategorie}</td>
                    <td>${signalement.idUtilisateur}</td>
                    <td>${signalement.longitude}</td>
                    <td>${signalement.latitude}</td>
                    <c:if test="${signalement.region == null}">
                    <td><a href="signalement/${signalement.idSignalement}">Affecter à une région</a></td>
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