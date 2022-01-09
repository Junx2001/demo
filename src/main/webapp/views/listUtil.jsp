<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="scheme" value="${pageContext.request.scheme}"/>
<c:set var="serverName" value="${pageContext.request.serverName}"/>
<c:set var="serverPort" value="${pageContext.request.serverPort}"/>
<c:set var="baseURL" value="${scheme}://${serverName}:${serverPort}"/>
<main id="main" class="main">

    <div class="pagetitle">
        <h1>Liste des Utilisateurs du Gouvernement (Front-Office)</h1>
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
                    <th scope="col">Email</th>
                    <th scope="col">Mot de Passe (Hashé en SHA2_256)</th>
                  </tr>
                </thead>
                <tbody>
                <c:forEach  items="${utilisateurs}" var ="utilisateur">
                  <tr>
                    <td>${utilisateur.idUtilisateur}</td>
                    <td>${utilisateur.email}</td>
                    <td>${utilisateur.mdp}</td>
                    <td><a class="btn btn-info" href="${baseURL}/utilisateur/updateUtil?email=${utilisateur.email}">Modifier</a></td>
                    <td><a class="btn btn-danger" href="${baseURL}//utilisateur/deleteUtil?email=${utilisateur.email}">Supprimer</a></td>
                  </tr>
                  </c:forEach>
                  
                </tbody>
              </table>
                </div>
        </div>
              <!-- End Table with stripped rows -->

              
        </div>
        </div>
    </section>

</main><!-- End #main -->