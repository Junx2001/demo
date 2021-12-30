<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="scheme" value="${pageContext.request.scheme}"/>
<c:set var="serverName" value="${pageContext.request.serverName}"/>
<c:set var="serverPort" value="${pageContext.request.serverPort}"/>
<c:set var="baseURL" value="${scheme}://${serverName}:${serverPort}"/>

<main id="main" class="main">
    <div class="pagetitle">
        <h1>Affectation d'un signalement à une région</h1>
    </div><!-- End Page Title -->

    <div>

        <!-- Card with an image on top -->
        <div class="card">

            <img src="${baseURL}/views/assets/img/card.jpg" width="50" class="card-img-top" alt="...">
            <input type="hidden" id="url" value="${baseURL}">
            <div class="card-body">
                <h5 class="card-title">Signalement <span id="idSignalement">${signalement[0][0]}</span></h5>
                <p class="card-text">Catégorie: ${signalement[0][11]}</p>
                <p class="card-text">Sous-catégorie: ${signalement[0][10]}</p>
                <p class="card-text">Description: ${signalement[0][2]}</p>
                <p class="card-text">Date: ${signalement[0][1]}</p>
                <hr>
                <p class="card-text">Longitude: ${signalement[0][7]}</p>
                <p class="card-text">Latitude: ${signalement[0][6]}</p>
                <hr>
                <p class="card-text">Auteur: ${signalement[0][5]}</p>
                <p class="card-text">Email: ${signalement[0][12]}</p>
                <hr>


                <c:if test="${signalement[0][9] == null}">
                    <select name="idRegion" id="idRegion">
                        <c:forEach  items="${regions}" var ="region">
                            <option value="${region.idRegion}">${region.nom}</option>
                        </c:forEach>
                    </select>
                </c:if>
                <c:if test="${signalement[0][9] != null}">
                    <p class="card-text">Region : ${signalement[0][9]}</p>
                </c:if>

                <button id="bouton">Affecter</button>
            </div>


        </div><!-- End Card with an image on top -->


    </div>
</main>

<script>
    $('#bouton').click(function () {
        var baseUrl = $('#url').val();
        var sign = $('#idSignalement').html();
        var region = $('#idRegion').val();
        $.ajax({
            url: baseUrl + '/signalement/' + sign,
            method: 'put',
            data: {region: region},
            dataType: 'json',
            success: function (response) {
                console.log(response);
                console.log("put method successfully done");
            }
        });
    });
</script>