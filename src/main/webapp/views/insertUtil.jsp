<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="scheme" value="${pageContext.request.scheme}"/>
<c:set var="serverName" value="${pageContext.request.serverName}"/>
<c:set var="serverPort" value="${pageContext.request.serverPort}"/>
<c:set var="baseURL" value="${scheme}://${serverName}:${serverPort}"/>
<main id="main" class="main">

    <div class="pagetitle">
        <h1>Ins�rer Utilisateur</h1>
    </div><!-- End Page Title -->

    <section class="section">
        <div class="row">
            <div class="col-lg-12">


                <div class="card">
                    <div class="card-body">


                        <!-- General Form Elements -->
                        <h5 class="card-title">Entrer les Informations de l'Utilisateur Front Office � ins�rer</h5>

                        <form action="${baseURL}/utilisateur/insert" oninput='up2.setCustomValidity(up2.value != mdp.value ? "Passwords do not match." : "")' method="post">
                            <div class="row mb-3">
                                <label for="email" class="col-sm-2 col-form-label">Email </label>
                                <div class="col-sm-10">
                                    <input type="email" class="form-control" name="email">
                                </div>
                            </div>
                            <div class="row mb-3">
                                <label for="mdp" class="col-sm-2 col-form-label">Mot de Passe</label>
                                <div class="col-sm-10">
                                    <input type="password" class="form-control" required name="mdp">
                                </div>
                            </div>
                            <div class="row mb-3">
                                <label for="mdp2" class="col-sm-2 col-form-label">Retaper votre Mot de Passe</label>
                                <div class="col-sm-10">
                                    <input type="password" class="form-control"  name="up2">
                                </div>
                            </div>
                            <div class="row mb-3">
                                <label class="col-sm-2 col-form-label"></label>
                                <div class="col-sm-10">
                                    <button type="submit" class="btn btn-success">Valider</button>
                                </div>
                            </div>

                        </form><!-- End General Form Elements -->

                    </div>
                </div>

            </div>
        </div>
    </section>


</main><!-- End #main -->